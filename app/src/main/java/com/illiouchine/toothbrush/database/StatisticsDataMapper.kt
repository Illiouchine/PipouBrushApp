package com.illiouchine.toothbrush.database

import com.illiouchine.toothbrush.database.dataobject.StatisticObject
import com.illiouchine.toothbrush.database.datasource.statistics.StatisticsDataSource
import com.illiouchine.toothbrush.usecase.datagateway.StatisticsDataGateway
import java.util.*
import javax.inject.Inject

class StatisticsDataMapper @Inject constructor(
    private val statisticsDataSource: StatisticsDataSource
) : StatisticsDataGateway {

    override suspend fun getStatistics(): StatisticsDataGateway.StatisticsEntity {
        return StatisticsDataGateway.StatisticsEntity(
            brushDates = statisticsDataSource.getStatistics().map { it.date },
        )
    }

    override suspend fun saveBrushHistory() {
        statisticsDataSource.addBrushFinished(
            StatisticObject(
                date = Date()
            )
        )
    }
}

