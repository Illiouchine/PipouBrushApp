package com.illiouchine.toothbrush.usecase

import com.illiouchine.toothbrush.usecase.datagateway.BrushHistoryDataGateway
import javax.inject.Inject

class SaveBrushProgressUseCase @Inject constructor(
    private val brushHistoryDataGateway: BrushHistoryDataGateway
) {
    suspend operator fun invoke() {
        brushHistoryDataGateway.saveBrushHistory()
    }
}