package com.illiouchine.toothbrush.feature.settings

import android.net.Uri
import com.illiouchine.mvi.core.MviViewModel
import com.illiouchine.mvi.core.Reducer
import com.illiouchine.toothbrush.usecase.countdown.GetCountDownDurationUseCase
import com.illiouchine.toothbrush.usecase.countdown.SetCountDownDurationUseCase
import com.illiouchine.toothbrush.usecase.datagateway.entities.Reminder
import com.illiouchine.toothbrush.usecase.notification.NotificationDayPeriod
import com.illiouchine.toothbrush.usecase.notification.UpdateTimedNotificationUseCase
import com.illiouchine.toothbrush.usecase.reminder.GetReminderUseCase
import com.illiouchine.toothbrush.usecase.reminder.SaveReminderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.time.Duration
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsAction as Action
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsIntent as Intent
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsPartialState as PartialState
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsState as State

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getCountDownDurationUseCase: GetCountDownDurationUseCase,
    private val setCountDownDurationUseCase: SetCountDownDurationUseCase,
    private val updateTimedNotificationUseCase: UpdateTimedNotificationUseCase,
    private val getReminderUseCase: GetReminderUseCase,
    private val saveReminderUseCase: SaveReminderUseCase
) : MviViewModel<Intent, Action, PartialState, State>() {

    init {
        setAction { Action.LoadSettings }
    }

    override fun createInitialState(): State {
        return State(
            countDownSettings = State.CountDownSettings.Loading,
            event = null,
            morningReminderState = State.ReminderState.Loading,
            middayReminderState = State.ReminderState.Loading,
            eveningReminderState = State.ReminderState.Loading
        )
    }

    override fun createReducer(): Reducer<State, PartialState> {
        return object : Reducer<State, PartialState>() {
            override fun reduce(
                currentState: State,
                partialState: PartialState
            ): State {
                return when (partialState) {
                    is PartialState.CountDownDurationLoaded -> {
                        currentState.copy(
                            countDownSettings = State.CountDownSettings.Loaded(
                                countDownDuration = partialState.countDownDuration
                            )
                        )
                    }
                    is PartialState.CountDownSaved -> {
                        currentState.copy(
                            event = State.SettingsEvent.CountDownSaved(duration = partialState.duration)
                        )
                    }
                    is PartialState.ErrorLoadingCountDownDuration -> {
                        currentState.copy(
                            event = State.SettingsEvent.ErrorLoadingCountDownDuration(exception = partialState.exception)
                        )
                    }
                    is PartialState.ErrorSavingCountDownDuration -> {
                        currentState.copy(
                            event = State.SettingsEvent.ErrorLoadingCountDownDuration(exception = partialState.exception)
                        )
                    }
                    is SettingsContract.SettingsPartialState.EventHandled -> {
                        if (currentState.event == partialState.settingsEvent) {
                            currentState.copy(
                                event = null
                            )
                        } else {
                            currentState
                        }
                    }
                    is PartialState.ErrorLoadingReminder -> currentState.copy(
                        event = State.SettingsEvent.ErrorLoadingReminder(
                            exception = partialState.exception,
                            dailyPeriod = partialState.dayPeriod
                        )
                    )
                    is PartialState.MorningReminderLoaded -> {
                        currentState.copy(
                            morningReminderState = State.ReminderState.Loaded(
                                hour = partialState.reminder.hour,
                                min = partialState.reminder.min,
                                enabled = partialState.reminder.enabled
                            )
                        )
                    }
                    is PartialState.MiddayReminderLoaded -> {
                        currentState.copy(
                            middayReminderState = State.ReminderState.Loaded(
                                hour = partialState.reminder.hour,
                                min = partialState.reminder.min,
                                enabled = partialState.reminder.enabled
                            )
                        )
                    }
                    is PartialState.EveningReminderLoaded -> {
                        currentState.copy(
                            eveningReminderState = State.ReminderState.Loaded(
                                hour = partialState.reminder.hour,
                                min = partialState.reminder.min,
                                enabled = partialState.reminder.enabled
                            )
                        )
                    }
                    is PartialState.ReminderSaved -> {
                        when (partialState.reminder.dayPeriod) {
                            Reminder.DayPeriod.Morning -> {
                                currentState.copy(
                                    morningReminderState = State.ReminderState.Loaded(
                                        hour = partialState.reminder.hour,
                                        min = partialState.reminder.min,
                                        enabled = partialState.reminder.enabled
                                    )
                                )
                            }
                            Reminder.DayPeriod.Midday -> {
                                currentState.copy(
                                    middayReminderState = State.ReminderState.Loaded(
                                        hour = partialState.reminder.hour,
                                        min = partialState.reminder.min,
                                        enabled = partialState.reminder.enabled
                                    )
                                )

                            }
                            Reminder.DayPeriod.Evening -> {
                                currentState.copy(
                                    eveningReminderState = State.ReminderState.Loaded(
                                        hour = partialState.reminder.hour,
                                        min = partialState.reminder.min,
                                        enabled = partialState.reminder.enabled
                                    )
                                )
                            }
                        }
                    }
                    is PartialState.GoToExternalLink -> {
                        currentState.copy(
                            event = SettingsContract.SettingsState.SettingsEvent.GoToExternalLink(intent = partialState.intent)
                        )
                    }
                }
            }
        }
    }

    override fun handleUserIntent(intent: Intent): Action {
        return when (intent) {
            is Intent.UpdateCountDownDuration -> {
                Action.UpdateCountDownDuration(
                    intent.duration
                )
            }
            is Intent.EventHandled -> {
                Action.EventHandled(intent.settingsEvent)
            }
            is Intent.ReminderChanged -> {
                Action.ChangeReminder(
                    checked = intent.checked,
                    reminderDayPeriod = intent.reminderDayPeriod,
                    hour = intent.hour,
                    min = intent.min
                )
            }
            Intent.ThanksClicked -> {
                Action.GoToExternalLink
            }
        }
    }

    override suspend fun handleAction(action: Action) {
        when (action) {
            Action.LoadSettings -> loadSettings()
            is Action.UpdateCountDownDuration -> saveCountDownDuration(action.duration)
            is SettingsContract.SettingsAction.EventHandled -> {
                setPartialState {
                    PartialState.EventHandled(action.settingsEvent)
                }
            }
            is Action.ChangeReminder -> {
                updateReminder(
                    checked = action.checked,
                    reminderDayPeriod = action.reminderDayPeriod,
                    hour = action.hour,
                    min = action.min
                )
            }
            Action.GoToExternalLink -> {
                val intent = android.content.Intent(android.content.Intent.ACTION_VIEW).setData(
                    Uri.parse("https://www.instagram.com/krogogo/")
                )
                setPartialState {
                    PartialState.GoToExternalLink(intent = intent)
                }
            }
        }
    }

    private suspend fun updateReminder(
        checked: Boolean,
        reminderDayPeriod: SettingsContract.ReminderDayPeriod,
        hour: Int,
        min: Int
    ) {
        val dayPeriod = when (reminderDayPeriod) {
            SettingsContract.ReminderDayPeriod.Evening -> Reminder.DayPeriod.Evening
            SettingsContract.ReminderDayPeriod.Midday -> Reminder.DayPeriod.Midday
            SettingsContract.ReminderDayPeriod.Morning -> Reminder.DayPeriod.Morning
        }
        val reminder = Reminder(
            dayPeriod = dayPeriod,
            hour = hour,
            min = min,
            enabled = checked
        )
        saveReminderUseCase(
            reminder = reminder
        )
        setPartialState {
            PartialState.ReminderSaved(reminder)
        }
        updateTimedNotificationUseCase(checked, reminderDayPeriod.toDayPeriod(), hour, min)

        //https://developer.android.com/develop/ui/views/notifications/time-sensitive
    }

    private suspend fun saveCountDownDuration(duration: Duration) {
        try {
            setCountDownDurationUseCase(duration)
            setPartialState {
                PartialState.CountDownSaved(duration)
            }
        } catch (e: Exception) {
            setPartialState {
                PartialState.ErrorSavingCountDownDuration(exception = e)
            }
        }
    }

    private suspend fun loadSettings() {
        loadCountdownDuration()
        loadReminder(Reminder.DayPeriod.Morning)
        loadReminder(Reminder.DayPeriod.Midday)
        loadReminder(Reminder.DayPeriod.Evening)
    }

    private suspend fun loadReminder(dayPeriod: Reminder.DayPeriod) {
        try {
            val reminder = getReminderUseCase(dayPeriod)
            setPartialState {
                when (dayPeriod) {
                    Reminder.DayPeriod.Morning -> PartialState.MorningReminderLoaded(reminder = reminder)
                    Reminder.DayPeriod.Midday -> PartialState.MiddayReminderLoaded(reminder = reminder)
                    Reminder.DayPeriod.Evening -> PartialState.EveningReminderLoaded(reminder = reminder)
                }

            }
        } catch (e: Exception) {
            setPartialState {
                PartialState.ErrorLoadingReminder(exception = e, dayPeriod = dayPeriod)
            }
        }
    }

    private suspend fun loadCountdownDuration() {
        try {
            val countDownDuration = getCountDownDurationUseCase()
            setPartialState {
                PartialState.CountDownDurationLoaded(countDownDuration = countDownDuration)
            }
        } catch (e: Exception) {
            setPartialState {
                PartialState.ErrorLoadingCountDownDuration(exception = e)
            }
        }
    }
}

private fun SettingsContract.ReminderDayPeriod.toDayPeriod(): NotificationDayPeriod {
    return when (this) {
        SettingsContract.ReminderDayPeriod.Evening -> NotificationDayPeriod.Evening
        SettingsContract.ReminderDayPeriod.Midday -> NotificationDayPeriod.Midday
        SettingsContract.ReminderDayPeriod.Morning -> NotificationDayPeriod.Morning
    }
}
