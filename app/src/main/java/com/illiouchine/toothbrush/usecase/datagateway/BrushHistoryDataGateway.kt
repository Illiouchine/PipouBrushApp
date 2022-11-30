package com.illiouchine.toothbrush.usecase.datagateway

interface BrushHistoryDataGateway {
    suspend fun getBrushHistory(): BrushHistoryEntity
    suspend fun saveBrushHistory()
}