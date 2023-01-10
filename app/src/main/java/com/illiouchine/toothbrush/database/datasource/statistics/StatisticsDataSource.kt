package com.illiouchine.toothbrush.database.datasource.statistics

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.illiouchine.toothbrush.database.dataobject.StatisticObject

@Dao
interface StatisticsDataSource {
    @Query("SELECT * FROM brush_history")
    suspend fun getStatistics(): List<StatisticObject>

    @Insert
    suspend fun addBrushFinished(date: StatisticObject)
}