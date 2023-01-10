package com.illiouchine.toothbrush.database.datasource.statistics

import com.illiouchine.toothbrush.database.dataobject.StatisticObject
import javax.inject.Inject

class StatisticsInMemory @Inject constructor() : StatisticsDataSource {

    private val brushHistory: MutableList<StatisticObject> = mutableListOf()

    override suspend fun getStatistics(): List<StatisticObject> {
        return brushHistory
    }

    override suspend fun addBrushFinished(date: StatisticObject) {
        brushHistory.add(date)
    }
}