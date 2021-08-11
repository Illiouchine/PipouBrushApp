package com.illiouchine.toothbrush.database.datasource.brushhistory

import com.illiouchine.toothbrush.database.`object`.BrushHistoryObject

interface BrushHistoryDataSource {
    suspend fun getBrushHistory(): List<BrushHistoryObject>
    suspend fun addBrushFinished()
}