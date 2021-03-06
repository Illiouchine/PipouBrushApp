package com.illiouchine.toothbrush.usecase.datagateway

import com.illiouchine.toothbrush.entities.BrushHistoryEntity

interface BrushHistoryDataGateway {
    suspend fun getBrushHistory(): List<BrushHistoryEntity>
    suspend fun saveBrushHistory()
}