package com.illiouchine.toothbrush.usecase.datagateway

interface BrushHistoryDataGateway {
    suspend fun getBrushHistory(): List<BrushHistoryEntity>
    suspend fun saveBrushHistory()
}