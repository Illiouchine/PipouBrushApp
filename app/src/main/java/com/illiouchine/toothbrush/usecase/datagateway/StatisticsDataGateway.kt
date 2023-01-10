package com.illiouchine.toothbrush.usecase.datagateway

import java.util.*

interface StatisticsDataGateway {
    suspend fun getStatistics(): StatisticsEntity
    suspend fun saveBrushHistory()

    data class StatisticsEntity(
        val brushDates: List<Date>,
    )
}