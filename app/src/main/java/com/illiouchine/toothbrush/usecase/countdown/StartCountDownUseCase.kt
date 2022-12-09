package com.illiouchine.toothbrush.usecase.countdown

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

val tick: Duration = 1.seconds

class StartCountDownUseCase @Inject constructor() {

    operator fun invoke(
        initialDuration: Duration,
        totalDuration: Duration
    ): Flow<CountDown> = flow {

        var remainingTime: Long = initialDuration.inWholeSeconds
        while (remainingTime >= 0) {
            emit(
                CountDown(
                    currentDuration = remainingTime,
                    totalDuration = totalDuration.inWholeSeconds
                )
            )
            delay(tick.inWholeMilliseconds)
            remainingTime = remainingTime.minus(tick.inWholeSeconds)
        }
    }

    data class CountDown(
        val currentDuration: Long,
        val totalDuration: Long
    )
}