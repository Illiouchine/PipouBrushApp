package com.illiouchine.toothbrush.feature.settings

import com.illiouchine.mvi.core.*
import com.illiouchine.toothbrush.usecase.datagateway.entities.Reminder
import kotlin.time.Duration

interface SettingsContract {

    sealed class SettingsIntent : UiIntent {
        data class UpdateCountDownDuration(val duration: Duration) : SettingsIntent()
        data class EventHandled(val settingsEvent: SettingsState.SettingsEvent) : SettingsIntent()

        class ReminderChanged(
            val checked: Boolean,
            val reminderDayPeriod: ReminderDayPeriod,
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

        data class ChangeReminder(
            val checked: Boolean,
            val reminderDayPeriod: ReminderDayPeriod,
            val hour: Int,
            val min: Int
        ) : SettingsAction()

        object LoadSettings : SettingsAction()
    }

    data class SettingsState(
        val countDownSettings: CountDownSettings,
        val morningReminderState: ReminderState,
        val middayReminderState: ReminderState,
        val eveningReminderState: ReminderState,
        override val event: SettingsEvent?,
    ) : UiState {
        sealed class CountDownSettings {
            object Loading : CountDownSettings()
            data class Loaded(
                val countDownDuration: Duration
            ) : CountDownSettings()
        }

        sealed class ReminderState {
            object Loading : ReminderState()
            data class Loaded(
                val hour: Int,
                val min: Int,
                val enabled: Boolean
            ) : ReminderState()
        }

        sealed class SettingsEvent : UiEvent {
            data class ErrorLoadingCountDownDuration(
                val exception: Exception
            ) : SettingsEvent()

            data class ErrorSavingCountDownDuration(val exception: Exception) : SettingsEvent()
            data class CountDownSaved(val duration: Duration) : SettingsEvent()
            data class ErrorLoadingReminder(
                val exception: Exception,
                val dailyPeriod: Reminder.DayPeriod
            ) : SettingsEvent()
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

        data class MorningReminderLoaded(val reminder: Reminder) : SettingsPartialState()
        data class MiddayReminderLoaded(val reminder: Reminder) : SettingsPartialState()
        data class EveningReminderLoaded(val reminder: Reminder) : SettingsPartialState()
        data class ErrorLoadingReminder(
            val exception: Exception,
            val dayPeriod: Reminder.DayPeriod
        ) : SettingsPartialState()

        data class ReminderSaved(val reminder: Reminder) : SettingsPartialState()
    }

    sealed class ReminderDayPeriod {
        object Morning : ReminderDayPeriod()
        object Midday : ReminderDayPeriod()
        object Evening : ReminderDayPeriod()
    }
}