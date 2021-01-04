package com.illiouchine.toothbrush.feature.brushing

import com.illiouchine.toothbrush.core.mvi.UiEvent
import com.illiouchine.toothbrush.core.mvi.UiIntent
import com.illiouchine.toothbrush.core.mvi.UiState

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
        data class CountDown(val duration: Double) : TimerState()
        data class Running(val duration: Double) : TimerState()
        object Finished : TimerState()
    }

    sealed class BrushEvent: UiEvent{
        object ShowToast: BrushEvent()
    }
}