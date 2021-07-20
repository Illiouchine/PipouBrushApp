package com.illiouchine.toothbrush.feature.brushing

import com.illiouchine.toothbrush.core.mvi.UiEvent
import com.illiouchine.toothbrush.core.mvi.UiIntent
import com.illiouchine.toothbrush.core.mvi.UiState
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalTime
class BrushContract {

    sealed class BrushIntent : UiIntent{
        object LaunchTimer: BrushIntent()
        object RestartTimer: BrushIntent()
    }

    data class BrushState(
        val timerState: TimerState
    ): UiState

    sealed class TimerState {
        object Idle : TimerState()
        data class Running(val duration: Duration) : TimerState()
        object Finished : TimerState()
    }

    sealed class BrushEvent: UiEvent{
        object ShowToast: BrushEvent()
    }
}