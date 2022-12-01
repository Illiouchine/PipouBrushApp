package com.illiouchine.toothbrush.usecase

import com.illiouchine.toothbrush.usecase.datagateway.BrushHistoryDataGateway
import java.util.*
import javax.inject.Inject

class GetBrushHistoryUseCase @Inject constructor(
    private val brushHistoryDataGateway: BrushHistoryDataGateway,
) {
    suspend operator fun invoke(): List<BrushHistory> {
        val brushDates: List<Date> = brushHistoryDataGateway.getBrushHistory().brushDates
        return brushDates.groupByDayAndCountOccurrence()
    }

    private fun List<Date>.groupByDayAndCountOccurrence(): List<BrushHistory> {
        return this
            .map { date: Date ->
                date to date.getYearAndDay()
            }.groupBy {
                it.second
            }.map {
                BrushHistory(
                    date = it.value.first().first,
                    brushCount = it.value.count()
                )
            }.toList()
    }

    private fun Date.getYearAndDay():Pair<Int,Int>{
        val dateCalendar = Calendar.getInstance().apply { time = this@getYearAndDay }
        return dateCalendar.get(Calendar.YEAR) to dateCalendar.get(Calendar.DAY_OF_YEAR)
    }

    data class BrushHistory(
        val date: Date,
        val brushCount: Int
    )
}

