package com.illiouchine.toothbrush.usecase

import com.illiouchine.toothbrush.usecase.datagateway.CountDownDurationDataGateway
import javax.inject.Inject
import kotlin.time.Duration

class SetCountDownDurationUseCase @Inject constructor(
    private val countDownDurationDataGateway: CountDownDurationDataGateway
) {
    suspend operator fun invoke(duration: Duration){
        countDownDurationDataGateway.setCountDownDuration(duration)
    }
}