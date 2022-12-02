package com.illiouchine.toothbrush.database

import com.illiouchine.toothbrush.R
import com.illiouchine.toothbrush.database.dataobject.AchievementObject
import com.illiouchine.toothbrush.database.datasource.achievement.AchievementDataSource
import com.illiouchine.toothbrush.usecase.datagateway.AchievementDataGateway
import javax.inject.Inject

class AchievementDataMapper @Inject constructor(
    private val achievementDataSource: AchievementDataSource
) : AchievementDataGateway {

    override val achievementsReference: List<AchievementDataGateway.AchievementRef>
        get() = listOf(
            // First brush : Earned when completing one brush
            AchievementDataGateway.AchievementRef(code = 100, nameResId = R.string.achievement_100_name, descriptionResId = R.string.achievement_100_desc),
            // 10 brush : Earned when completing ten brush
            AchievementDataGateway.AchievementRef(code = 110, nameResId = R.string.achievement_110_name, descriptionResId = R.string.achievement_110_desc),
            //  --------------------
            // first Full Day : Earned when brushing three time on a single day
            AchievementDataGateway.AchievementRef(code = 200, nameResId = R.string.achievement_200_name, descriptionResId = R.string.achievement_200_desc),
            // 10 Full Day : Earned when brushing three time on a ten day
            AchievementDataGateway.AchievementRef(code = 210, nameResId = R.string.achievement_210_name, descriptionResId = R.string.achievement_210_desc),
            //  --------------------
            // Three Day in a row : Earned when brushing three day in a row
            AchievementDataGateway.AchievementRef(code = 300, nameResId = R.string.achievement_300_name, descriptionResId = R.string.achievement_300_desc),
            // Ten Day in a row : Earned when brushing ten day in a row
            AchievementDataGateway.AchievementRef(code = 310, nameResId = R.string.achievement_310_name, descriptionResId = R.string.achievement_310_desc),
        )

    override suspend fun getEarnedAchievements(): List<AchievementDataGateway.AchievementEntity> {
        return achievementDataSource.getAchievements().map {
            AchievementDataGateway.AchievementEntity(
                code = it.code,
                unlockDate = it.unlockDate
            )
        }
    }

    override suspend fun saveAchievements(achievement: AchievementDataGateway.AchievementEntity) {
        achievementDataSource.addAchievement(
            AchievementObject(
                code = achievement.code,
                unlockDate = achievement.unlockDate
            )
        )
    }
}