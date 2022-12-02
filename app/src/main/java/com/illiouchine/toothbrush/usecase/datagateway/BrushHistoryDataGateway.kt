package com.illiouchine.toothbrush.usecase.datagateway

import java.util.*

interface BrushHistoryDataGateway {
    suspend fun getBrushHistory(): BrushHistoryEntity
    suspend fun saveBrushHistory()

    data class BrushHistoryEntity(
        val brushDates: List<Date>,
    )
}