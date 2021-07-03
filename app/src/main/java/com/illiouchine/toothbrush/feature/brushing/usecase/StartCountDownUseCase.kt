package com.illiouchine.toothbrush.feature.brushing.usecase

import com.illiouchine.toothbrush.feature.brushing.tick
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

class StartCountDownUseCase @Inject constructor() {

    @ExperimentalTime
    operator fun invoke(initialDuration: Duration): Flow<Double> = flow {
        var currentDuration: Duration = initialDuration
        while (currentDuration.inSeconds > 0) {
            emit(currentDuration.toDouble(DurationUnit.SECONDS))
            currentDuration = currentDuration.minus(tick)
            delay(tick)
        }
    }

}