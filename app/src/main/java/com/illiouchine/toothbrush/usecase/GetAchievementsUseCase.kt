package com.illiouchine.toothbrush.usecase

import com.illiouchine.toothbrush.usecase.datagateway.AchievementDataGateway
import javax.inject.Inject

class GetAchievementsUseCase @Inject constructor(
    private val achievementDataGateway: AchievementDataGateway
) {

    suspend operator fun invoke(): List<Achievement> {
        val earnedAchievement = achievementDataGateway.getEarnedAchievements()
        val achievementsRef = achievementDataGateway.achievementsReference

        return achievementsRef
            .sortedBy { it.code }
            .map { achievementRef ->
                val isEarned = earnedAchievement.any { earnedAchievement ->
                    achievementRef.code == earnedAchievement.code
                }
                Achievement(
                    nameResId = achievementRef.nameResId,
                    descriptionResId = achievementRef.descriptionResId,
                    earned = isEarned
                )
            }
    }

    data class Achievement(
        val nameResId: Int,
        val descriptionResId: Int,
        val earned: Boolean
    )
}
