package com.illiouchine.toothbrush.database.datasource.brushhistory

import com.illiouchine.toothbrush.database.dataobject.BrushHistoryObject
import javax.inject.Inject

class BrushHistoryInMemory @Inject constructor() : BrushHistoryDataSource {

    private val brushHistory: MutableList<BrushHistoryObject> = mutableListOf()

    override suspend fun getBrushHistory(): List<BrushHistoryObject> {
        return brushHistory
    }

    override suspend fun addBrushFinished(date: BrushHistoryObject) {
        brushHistory.add(date)
    }
}