package com.illiouchine.toothbrush.feature.brushing

import com.illiouchine.toothbrush.core.mvi.*
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalTime
interface BrushContract {

    sealed class BrushIntent : UiIntent{
        object LaunchTimer: BrushIntent()
        object RestartTimer: BrushIntent()
    }

    sealed class BrushAction : UiAction{
        object LaunchTimer: BrushAction()
    }

    data class BrushState(
        val timerState: TimerState
    ): UiState

    sealed class TimerState {
        object Idle : TimerState()
        data class Running(val duration: Duration) : TimerState()
        object Finished : TimerState()
    }

    sealed class BrushPartialState: UiPartialState{
        object TimerFinished : BrushPartialState()
        data class TimerRunning(val duration: Duration): BrushPartialState()
    }

    sealed class BrushEvent: UiEvent{
        object ShowToast: BrushEvent()
    }
}