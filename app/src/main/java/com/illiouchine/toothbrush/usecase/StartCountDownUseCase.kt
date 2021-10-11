package com.illiouchine.toothbrush.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalTime
val tick: Duration = 1.toDuration(DurationUnit.SECONDS)

@ExperimentalTime
val brushDuration: Duration = 3.toDuration(DurationUnit.SECONDS)

class StartCountDownUseCase @Inject constructor() {

    @ExperimentalTime
    operator fun invoke(initialDuration: Duration = brushDuration): Flow<CountDown> = flow {
        var currentDuration: Duration = initialDuration
        while (currentDuration.inWholeSeconds > 0) {
            emit(
                CountDown(
                    currentDuration = currentDuration,
                    totalDuration = initialDuration
                )
            )
            currentDuration = currentDuration.minus(tick)
            delay(tick)
        }
    }

    @ExperimentalTime
    data class CountDown(
        val currentDuration: Duration,
        val totalDuration: Duration
    )
}