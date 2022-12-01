package com.illiouchine.toothbrush.usecase

import com.illiouchine.toothbrush.usecase.datagateway.BrushHistoryDataGateway
import java.util.*
import javax.inject.Inject

class GetBrushStatisticUseCase @Inject constructor(
    private val brushHistoryDataGateway: BrushHistoryDataGateway,
) {
    suspend operator fun invoke(): List<Pair<Date, Int>> {
        val brushDates: List<Date> = brushHistoryDataGateway.getBrushHistory().brushDates
        return brushDates.groupByDayAndCountOccurrence()
    }

    private fun List<Date>.groupByDayAndCountOccurrence(): List<Pair<Date, Int>> {
        return this
            .map { date: Date ->
                date to date.getYearAndDay()
            }.groupBy {
                it.second
            }.mapKeys {
                it.value.first().first
            }.mapValues {
                it.value.count()
            }.toList()
    }

    private fun Date.getYearAndDay():Pair<Int,Int>{
        val dateCalendar = Calendar.getInstance().apply { time = this@getYearAndDay }
        return dateCalendar.get(Calendar.YEAR) to dateCalendar.get(Calendar.DAY_OF_YEAR)
    }
}

