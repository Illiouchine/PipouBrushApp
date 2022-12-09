package com.illiouchine.toothbrush.feature.settings

import com.illiouchine.mvi.core.*
import kotlin.time.Duration

interface SettingsContract {

    sealed class SettingsIntent : UiIntent {
        data class UpdateCountDownDuration(val duration: Duration) : SettingsIntent()
        data class EventHandled(val settingsEvent: SettingsState.SettingsEvent) : SettingsIntent()

        class NotificationChanged(
            val checked: Boolean,
            val reminderType: ReminderType,
            val hour: Int,
            val min: Int
        ) : SettingsIntent()
    }

    sealed class SettingsAction : UiAction {
        data class UpdateCountDownDuration(
            val duration: Duration
        ) : SettingsAction()

        data class EventHandled(
            val settingsEvent: SettingsState.SettingsEvent
        ) : SettingsAction()

        data class ChangeNotification(
            val checked: Boolean,
            val reminderType: ReminderType,
            val hour: Int,
            val min: Int
        ) : SettingsAction()

        object LoadSettings : SettingsAction()
    }

    data class SettingsState(
        val countDownSettings: CountDownSettings,
        override val event: SettingsEvent?,
    ) : UiState {
        sealed class CountDownSettings {
            object Loading : CountDownSettings()
            data class Loaded(
                val countDownDuration: Duration
            ) : CountDownSettings()
        }

        sealed class SettingsEvent : UiEvent {
            data class ErrorLoadingCountDownDuration(
                val exception: Exception
            ) : SettingsEvent()

            data class ErrorSavingCountDownDuration(val exception: Exception) : SettingsEvent()
            data class CountDownSaved(val duration: Duration) : SettingsEvent()
        }
    }

    sealed class SettingsPartialState : UiPartialState {
        data class CountDownDurationLoaded(
            val countDownDuration: Duration
        ) : SettingsPartialState()

        data class CountDownSaved(val duration: Duration) : SettingsPartialState()
        data class ErrorSavingCountDownDuration(val exception: Exception) : SettingsPartialState()
        data class ErrorLoadingCountDownDuration(val exception: Exception) : SettingsPartialState()
        data class EventHandled(val settingsEvent: SettingsState.SettingsEvent) :
            SettingsPartialState()
    }

    sealed class ReminderType {
        object Morning : ReminderType()
        object Midday : ReminderType()
        object Evening : ReminderType()
    }
}