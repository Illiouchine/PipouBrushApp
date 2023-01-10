package com.illiouchine.toothbrush.usecase

import com.illiouchine.toothbrush.usecase.datagateway.StatisticsDataGateway
import com.illiouchine.toothbrush.usecase.utils.groupByDayAndCountOccurrence
import java.util.*
import javax.inject.Inject

class GetStatisticsUseCase @Inject constructor(
    private val statisticsDataGateway: StatisticsDataGateway,
) {
    suspend operator fun invoke(): List<Statistics> {
        val brushDates: List<Date> = statisticsDataGateway.getStatistics().brushDates
        return brushDates.groupByDayAndCountOccurrence()
            .map {
                Statistics(
                    date = it.first,
                    brushCount = it.second
                )
            }.toList()
    }

    data class Statistics(
        val date: Date,
        val brushCount: Int
    )
}

