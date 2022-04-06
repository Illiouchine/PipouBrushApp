package com.illiouchine.toothbrush.feature.settings

import com.illiouchine.mvi.core.*
import java.time.DayOfWeek
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalTime
interface SettingsContract {

    sealed class SettingsIntent : UiIntent {
        data class UpdateTimerDuration(
            val duration: Duration
        ) : SettingsIntent()

        // TODO rename
        data class AddReminder(
            val dayOfWeek: Int,
            val hourOfWeek: Int
        ) : SettingsIntent()
    }

    sealed class SettingsAction : UiAction {
        data class SaveTimerDuration(val duration: Duration) : SettingsAction()
        data class SaveReminder(val dayOfWeek: Int, val hourOfWeek: Int) : SettingsAction()

        object LoadSettings : SettingsAction()
    }

    data class SettingsState(
        val timer: Timer
    ) : UiState {
        sealed class Timer {
            data class TimerDuration(val duration: Duration) : Timer()
            object Choosing : Timer()
            object Loading : Timer()
        }
    }

    sealed class SettingsPartialState() : UiPartialState

    sealed class SettingsEvent() : UiEvent

}