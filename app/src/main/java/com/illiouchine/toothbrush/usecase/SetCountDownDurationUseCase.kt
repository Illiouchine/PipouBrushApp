package com.illiouchine.toothbrush.usecase

import com.illiouchine.toothbrush.usecase.datagateway.CountDownDurationDataGateway
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

class SetCountDownDurationUseCase @Inject constructor(
    private val countDownDurationDataGateway: CountDownDurationDataGateway
) {
    suspend operator fun invoke(duration: Duration){
        if (duration < 1.minutes && duration > 6.minutes){
            throw IllegalArgumentException("the duration : ${duration.inWholeSeconds} s should be between 60s et 360s")
        }else{
            countDownDurationDataGateway.setCountDownDuration(duration)
        }
    }
}