package com.illiouchine.toothbrush.feature.brushing

import com.illiouchine.mvi.core.*

interface BrushContract {

    sealed class BrushIntent : UiIntent {
        object StartBrushing : BrushIntent()
        object ResetBrushing : BrushIntent()
        object AchievementHandled : BrushIntent()
    }

    sealed class BrushAction : UiAction {
        object StartTimer : BrushAction()
        object ResetTimer : BrushAction()
        object FinishTimer : BrushAction()
        object ClearAchievements : BrushAction()
    }

    data class BrushState(
        val timer: Timer,
        override val event: BrushEvent?,
        val achievement: List<Achievement> = emptyList()
    ) : UiState {
        sealed class Timer {
            object Idle : Timer()
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
        data class AchievementEarned(
            val achievements: List<Achievement>
        ) : BrushPartialState()

        object TimerIdle : BrushPartialState()
        object ClearAchievements : BrushPartialState()
    }

    data class Achievement(
        val nameResId: Int,
        val descriptionResId: Int,
        val earned: Boolean
    )
}