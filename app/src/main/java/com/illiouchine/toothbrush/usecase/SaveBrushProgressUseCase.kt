package com.illiouchine.toothbrush.usecase

import com.illiouchine.toothbrush.usecase.datagateway.StatisticsDataGateway
import javax.inject.Inject

class SaveBrushProgressUseCase @Inject constructor(
    private val statisticsDataGateway: StatisticsDataGateway
) {
    suspend operator fun invoke() {
        statisticsDataGateway.saveBrushHistory()
    }
}