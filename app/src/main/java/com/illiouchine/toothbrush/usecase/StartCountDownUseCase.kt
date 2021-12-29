package com.illiouchine.toothbrush.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

const val tick: Long = 1L

const val brushDuration: Long = 3L

class StartCountDownUseCase @Inject constructor() {

    operator fun invoke(initialDuration: Long = brushDuration): Flow<CountDown> = flow {
        var currentDuration: Long = initialDuration
        while (currentDuration > 0) {
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

    data class CountDown(
        val currentDuration: Long,
        val totalDuration: Long
    )
}