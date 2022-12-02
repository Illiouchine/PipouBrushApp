package com.illiouchine.toothbrush.usecase.datagateway

import java.util.*

interface AchievementDataGateway {

    val achievementsReference: List<AchievementRef>

    suspend fun getEarnedAchievements(): List<AchievementEntity>
    suspend fun saveAchievements(achievement: AchievementEntity)

    data class AchievementEntity(
        val code: Int,
        val unlockDate: Date,
    )

    data class AchievementRef(
        val code: Int,
        val nameResId: Int,
        val descriptionResId: Int,
    )
}