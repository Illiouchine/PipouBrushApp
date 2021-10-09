package com.illiouchine.toothbrush.feature.brushing.controller

import com.illiouchine.mvi.core.UiAction
import com.illiouchine.mvi.core.UiEvent
import com.illiouchine.mvi.core.UiIntent
import com.illiouchine.mvi.core.UiPartialState
import com.illiouchine.mvi.core.UiState
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalTime
interface BrushContract {

    sealed class BrushIntent : UiIntent {
        object LaunchTimer: BrushIntent()
        object RestartTimer: BrushIntent()
    }

    sealed class BrushAction : UiAction {
        object LaunchTimer: BrushAction()
    }

    data class BrushState(
        val timerState: TimerState
    ): UiState

    sealed class TimerState {
        object Idle : TimerState()
        data class Running(
            val duration: Duration,
            val totalDuration: Duration
            ) : TimerState()
        object Finished : TimerState()
    }

    sealed class BrushPartialState: UiPartialState {
        object TimerFinished : BrushPartialState()
        data class TimerRunning(
            val duration: Duration,
            val totalDuration: Duration,
        ): BrushPartialState()
    }

    sealed class BrushEvent: UiEvent {
        object ShowToast: BrushEvent()
    }
}