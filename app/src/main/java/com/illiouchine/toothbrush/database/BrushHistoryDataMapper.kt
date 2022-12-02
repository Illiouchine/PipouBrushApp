package com.illiouchine.toothbrush.database

import com.illiouchine.toothbrush.database.dataobject.BrushHistoryObject
import com.illiouchine.toothbrush.database.datasource.brushhistory.BrushHistoryDataSource
import com.illiouchine.toothbrush.usecase.datagateway.BrushHistoryDataGateway
import java.util.*
import javax.inject.Inject

class BrushHistoryDataMapper @Inject constructor(
    private val brushHistoryDataSource: BrushHistoryDataSource
) : BrushHistoryDataGateway {

    override suspend fun getBrushHistory(): BrushHistoryDataGateway.BrushHistoryEntity {
        return BrushHistoryDataGateway.BrushHistoryEntity(
            brushDates = brushHistoryDataSource.getBrushHistory().map { it.date },
        )
    }

    override suspend fun saveBrushHistory() {
        brushHistoryDataSource.addBrushFinished(
            BrushHistoryObject(
                date = Date()
            )
        )
    }
}

