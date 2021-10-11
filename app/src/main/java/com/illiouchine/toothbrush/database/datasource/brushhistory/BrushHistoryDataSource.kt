package com.illiouchine.toothbrush.database.datasource.brushhistory

import com.illiouchine.toothbrush.database.dataobject.BrushHistoryObject

interface BrushHistoryDataSource {
    suspend fun getBrushHistory(): List<BrushHistoryObject>
    suspend fun addBrushFinished()
}