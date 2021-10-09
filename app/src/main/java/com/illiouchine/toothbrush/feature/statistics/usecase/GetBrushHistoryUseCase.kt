package com.illiouchine.toothbrush.feature.statistics.usecase

import com.illiouchine.toothbrush.entities.BrushHistoryEntity
import com.illiouchine.toothbrush.feature.datagateway.BrushHistoryDataGateway
import javax.inject.Inject

class GetBrushHistoryUseCase @Inject constructor(
    private val brushHistoryDataGateway: BrushHistoryDataGateway,
) {
    suspend operator fun invoke(): List<BrushHistoryEntity> {
        return brushHistoryDataGateway.getBrushHistory()
    }
}

