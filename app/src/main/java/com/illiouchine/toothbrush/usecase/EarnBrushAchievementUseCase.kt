package com.illiouchine.toothbrush.usecase

import com.illiouchine.toothbrush.usecase.datagateway.Achievement
import com.illiouchine.toothbrush.usecase.datagateway.AchievementDataGateway
import com.illiouchine.toothbrush.usecase.datagateway.AchievementReference
import com.illiouchine.toothbrush.usecase.datagateway.BrushHistoryDataGateway
import java.util.*
import javax.inject.Inject

class EarnBrushAchievementUseCase @Inject constructor(
    private val achievementDataGateway: AchievementDataGateway,
    private val brushHistoryDataGateway: BrushHistoryDataGateway,
    private val getBrushHistoryUseCase: GetBrushHistoryUseCase
) {

    private val justEarnedAchievements : MutableList<Achievement> = mutableListOf()

    // Should be lunched after brush saved in history
    // Iterate over achievement list and check if new achievements are earned
    // return this new achievement or empty list
    suspend operator fun invoke(): List<Achievement> {

        // Filter not earned achievement
        val achievementsRef = achievementDataGateway.achievementsReference
        val achievementRefNotEarned = achievementsRef.filterNot { achievementRef ->
            achievementDataGateway.getEarnedAchievements().any { earnedAchievement ->
                achievementRef.code == earnedAchievement.code
            }
        }

        //  Iterate over not earned achievement and check with brush history if the user unlock a new achievement
        achievementRefNotEarned.onEach { notEarnedAchievement ->
            when(notEarnedAchievement.code){
                100 -> { // First Brush
                    // check if we should give this achievement
                    shouldGive(notEarnedAchievement){
                        brushHistoryDataGateway.getBrushHistory().brushDates.any()
                    }
                }
                110 -> { // 10 Brush
                    shouldGive(notEarnedAchievement){
                        brushHistoryDataGateway.getBrushHistory().brushDates.size >= 10
                    }
                }
                200 -> { // Fullday
                    shouldGive(notEarnedAchievement) {
                        getBrushHistoryUseCase().any { it.brushCount >= 3 }
                    }
                }
                220 -> { // 10 Fullday
                    shouldGive(notEarnedAchievement) {
                        getBrushHistoryUseCase().count { it.brushCount >= 3 } >= 10
                    }
                }
                300 -> { // Three Day in a row
                    shouldGive(notEarnedAchievement) {
                        // TODO
                        getBrushHistoryUseCase().count3DayInRowBrushing() >= 1
                    }
                }
                310 -> { // 10 Three Day in a row
                    shouldGive(notEarnedAchievement) {
                        // TODO
                        getBrushHistoryUseCase().count3DayInRowBrushing() >= 10
                    }
                }
                else -> {
                    /* Do nothing */
                }
            }
        }
        return justEarnedAchievements.toList()
    }

    private suspend fun List<GetBrushHistoryUseCase.BrushHistory>.count3DayInRowBrushing(): Int {
        return 0
    }

    private suspend fun shouldGive(
        achievement: AchievementReference,
        predicate: suspend ()-> Boolean
    ){
        if (predicate()){
            // Save it
            achievementDataGateway.saveAchievements(
                achievement = AchievementDataGateway.AchievementEntity(
                    achievement.code, Date()
                )
            )
            // give it back
            justEarnedAchievements.add(
                Achievement(
                    nameResId = achievement.nameResId,
                    descriptionResId = achievement.descriptionResId,
                    earned = true
                )
            )
        }
    }
}