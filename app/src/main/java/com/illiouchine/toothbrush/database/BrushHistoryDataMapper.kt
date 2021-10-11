package com.illiouchine.toothbrush.database

import com.illiouchine.toothbrush.database.datasource.brushhistory.BrushHistoryDataSource
import com.illiouchine.toothbrush.entities.BrushHistoryEntity
import com.illiouchine.toothbrush.usecase.datagateway.BrushHistoryDataGateway
import javax.inject.Inject

class BrushHistoryDataMapper @Inject constructor(
    private val brushHistoryDataSource: BrushHistoryDataSource
) : BrushHistoryDataGateway {

    override suspend fun getBrushHistory(): List<BrushHistoryEntity> {
        return brushHistoryDataSource.getBrushHistory().map {
            BrushHistoryEntity(
                date = it.date,
                // Todo calculate here ?
                dayPeriod = BrushHistoryEntity.DayPeriod.Morning
            )
        }
    }

    override suspend fun saveBrushHistory() {
        brushHistoryDataSource.addBrushFinished()
    }
}

