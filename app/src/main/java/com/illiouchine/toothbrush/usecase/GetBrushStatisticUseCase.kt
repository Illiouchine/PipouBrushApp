package com.illiouchine.toothbrush.usecase

import com.illiouchine.toothbrush.usecase.datagateway.BrushHistoryDataGateway
import java.util.*
import javax.inject.Inject

class GetBrushStatisticUseCase @Inject constructor(
    private val brushHistoryDataGateway: BrushHistoryDataGateway,
) {
    suspend operator fun invoke(): List<Pair<Date, Int>> {
        val brushDates: List<Date> = brushHistoryDataGateway.getBrushHistory().brushDates
        return brushDates.performGrouping()
    }

    //Should return Map<Date, Int>
    private fun List<Date>.performGrouping(): List<Pair<Date, Int>> {
        val dateAndYD: List<Pair<Date, Pair<Int, Int>>> = this.map { date: Date ->
            date to date.getYearAndDay()
        }

        val groupedByYD: Map<Pair<Int, Int>, List<Pair<Date, Pair<Int, Int>>>> = dateAndYD.groupBy { it.second }

        val counted: List<Pair<Date, Int>> = groupedByYD
            .mapKeys {
                it.value.first().first
            }.mapValues {
                it.value.count()
            }.toList()
        return counted
    }

    private fun Date.getYearAndDay():Pair<Int,Int>{
        val dateCalendar = Calendar.getInstance().apply { time = this@getYearAndDay }
        return dateCalendar.get(Calendar.YEAR) to dateCalendar.get(Calendar.DAY_OF_YEAR)
    }
}

