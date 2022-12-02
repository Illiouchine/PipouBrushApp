package com.illiouchine.toothbrush.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.illiouchine.toothbrush.database.dataobject.AchievementObject
import com.illiouchine.toothbrush.database.dataobject.BrushHistoryObject
import com.illiouchine.toothbrush.database.datasource.achievement.AchievementDataSource
import com.illiouchine.toothbrush.database.datasource.brushhistory.BrushHistoryDataSource

@Database(
    entities = [BrushHistoryObject::class, AchievementObject::class],
    version = 2,
    exportSchema = false,
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun brushHistoryDao(): BrushHistoryDataSource
    abstract fun achievementDao(): AchievementDataSource
}