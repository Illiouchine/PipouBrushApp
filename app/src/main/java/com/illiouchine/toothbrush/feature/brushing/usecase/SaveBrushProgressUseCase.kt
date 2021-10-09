package com.illiouchine.toothbrush.feature.brushing.usecase

import com.illiouchine.toothbrush.feature.datagateway.BrushHistoryDataGateway
import javax.inject.Inject

class SaveBrushProgressUseCase @Inject constructor(
    private val brushHistoryDataGateway: BrushHistoryDataGateway
) {
    suspend operator fun invoke() {
        brushHistoryDataGateway.saveBrushHistory()
    }
}