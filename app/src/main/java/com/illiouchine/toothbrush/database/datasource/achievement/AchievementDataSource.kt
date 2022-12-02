package com.illiouchine.toothbrush.database.datasource.achievement

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.illiouchine.toothbrush.database.dataobject.AchievementObject

@Dao
interface AchievementDataSource {
    @Query("SELECT * FROM achievement")
    suspend fun getAchievements(): List<AchievementObject>
    @Insert
    suspend fun addAchievement(date: AchievementObject)
}