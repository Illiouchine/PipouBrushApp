package com.illiouchine.toothbrush.usecase.countdown

import com.illiouchine.toothbrush.usecase.datagateway.CountDownDurationDataGateway
import javax.inject.Inject
import kotlin.time.Duration

class GetCountDownDurationUseCase @Inject constructor(
    private val countDownDurationDataGateway: CountDownDurationDataGateway
){
    suspend operator fun invoke(): Duration {
        return countDownDurationDataGateway.getCountDownDuration()
    }
}