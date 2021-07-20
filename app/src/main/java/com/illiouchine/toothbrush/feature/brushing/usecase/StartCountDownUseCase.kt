package com.illiouchine.toothbrush.feature.brushing.usecase

import com.illiouchine.toothbrush.feature.brushing.tick
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class StartCountDownUseCase @Inject constructor() {

    @ExperimentalTime
    operator fun invoke(initialDuration: Duration): Flow<Duration> = flow {
        var currentDuration: Duration = initialDuration
        while (currentDuration.inWholeSeconds > 0) {
            emit(currentDuration)
            currentDuration = currentDuration.minus(tick)
            delay(tick)
        }
    }

}