package com.illiouchine.toothbrush.usecase

import com.illiouchine.toothbrush.usecase.datagateway.BrushHistoryEntity
import com.illiouchine.toothbrush.usecase.datagateway.BrushHistoryDataGateway
import javax.inject.Inject

class GetBrushHistoryUseCase @Inject constructor(
    private val brushHistoryDataGateway: BrushHistoryDataGateway,
) {
    suspend operator fun invoke(): List<BrushHistoryEntity> {
        return brushHistoryDataGateway.getBrushHistory()
    }
}

