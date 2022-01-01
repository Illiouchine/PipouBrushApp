package com.illiouchine.toothbrush.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

const val tick: Long = 1*1000L
const val brushDuration: Long = 30L

class StartCountDownUseCase @Inject constructor() {

    operator fun invoke(
        initialDuration: Long = brushDuration,
        totalDuration: Long = brushDuration
    ): Flow<CountDown> = flow {

        var currentDuration: Long = initialDuration
        while (currentDuration >= 0) {
            emit(
                CountDown(
                    currentDuration = currentDuration,
                    totalDuration = totalDuration
                )
            )
            delay(tick)
            currentDuration = currentDuration.minus(1)
        }
    }

    data class CountDown(
        val currentDuration: Long,
        val totalDuration: Long
    )
}