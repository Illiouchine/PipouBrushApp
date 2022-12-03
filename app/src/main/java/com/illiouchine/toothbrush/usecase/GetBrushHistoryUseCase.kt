package com.illiouchine.toothbrush.usecase

import com.illiouchine.toothbrush.usecase.datagateway.BrushHistoryDataGateway
import com.illiouchine.toothbrush.usecase.utils.groupByDayAndCountOccurrence
import java.util.*
import javax.inject.Inject

class GetBrushHistoryUseCase @Inject constructor(
    private val brushHistoryDataGateway: BrushHistoryDataGateway,
) {
    suspend operator fun invoke(): List<BrushHistory> {
        val brushDates: List<Date> = brushHistoryDataGateway.getBrushHistory().brushDates
        return brushDates.groupByDayAndCountOccurrence()
            .map {
                BrushHistory(
                    date = it.first,
                    brushCount = it.second
                )
            }.toList()
    }

    data class BrushHistory(
        val date: Date,
        val brushCount: Int
    )
}

