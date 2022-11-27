package com.illiouchine.toothbrush.feature.brushing

import com.illiouchine.mvi.core.*

interface BrushContract {

    sealed class BrushIntent : UiIntent {
        object StartBrushing : BrushIntent()
        object ResetBrushing : BrushIntent()
    }

    sealed class BrushAction : UiAction {
        object StartTimer : BrushAction()
        object ResetTimer : BrushAction()
        object FinishTimer : BrushAction()
    }

    data class BrushState(
        val timer: Timer,
        override val event: BrushEvent?
    ) : UiState {
        sealed class Timer {
            object Idle: Timer()
            object Finished : Timer()

            data class Running(
                val current: Long,
                val total: Long
            ) : Timer()
        }
        sealed class BrushEvent : UiEvent {
            data class ShowToast(val message: String) : BrushEvent() // TODO : should pass a resId
        }
    }

    sealed class BrushPartialState : UiPartialState {
        object TimerFinished : BrushPartialState()
        data class TimerRunning(val current: Long, val total: Long) : BrushPartialState()
        object TimerIdle : BrushPartialState()

    }
}