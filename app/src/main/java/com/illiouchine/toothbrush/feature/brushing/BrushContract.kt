package com.illiouchine.toothbrush.feature.brushing

import com.illiouchine.mvi.core.*

interface BrushContract {

    sealed class BrushIntent : UiIntent {
        object StartBrushing : BrushIntent()
        object PauseBrushing : BrushIntent()
        object ResumeBrushing : BrushIntent()
        object ResetBrushing : BrushIntent()
    }

    sealed class BrushAction : UiAction {
        object StartTimer : BrushAction()
        object PauseTimer : BrushAction()
        object ResetTimer : BrushAction()
        object ResumeTimer : BrushAction()
        object FinishTimer : BrushAction()
    }

    data class BrushState(
        val timer: Timer
    ) : UiState {
        sealed class Timer {
            data class Idle(
                val current: Long,
                val total: Long
            ) : Timer()

            data class Running(
                val current: Long,
                val total: Long
            ) : Timer()

            object Finished : Timer()
        }
    }

    sealed class BrushPartialState : UiPartialState {
        object TimerFinished : BrushPartialState()
        data class TimerRunning(val current: Long, val total: Long) : BrushPartialState()
        data class TimerPaused(val current: Long, val total: Long) : BrushPartialState()
    }

    sealed class BrushEvent : UiEvent {
        data class ShowToast(val message: String) : BrushEvent() // TODO : should pass a resId
    }
}