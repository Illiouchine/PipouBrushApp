package com.illiouchine.toothbrush.feature.brushing

import com.illiouchine.mvi.core.*
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalTime
interface BrushContract {

    sealed class BrushIntent : UiIntent {
        object LaunchTimer : BrushIntent()
        object RestartTimer : BrushIntent()
    }

    sealed class BrushAction : UiAction {
        object LaunchTimer : BrushAction()
    }

    data class BrushState(
        val timer: Timer
    ) : UiState {
        sealed class Timer {
            object Idle : Timer()
            data class Running(
                val duration: Duration,
                val totalDuration: Duration
            ) : Timer()
            object Finished : Timer()
        }
    }


    sealed class BrushPartialState : UiPartialState {
        object TimerFinished : BrushPartialState()
        data class TimerRunning(
            val duration: Duration,
            val totalDuration: Duration,
        ) : BrushPartialState()
    }

    sealed class BrushEvent : UiEvent {
        object ShowToast : BrushEvent()
    }
}