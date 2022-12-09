package com.illiouchine.toothbrush.feature.settings

import com.illiouchine.mvi.core.MviViewModel
import com.illiouchine.mvi.core.Reducer
import com.illiouchine.toothbrush.usecase.countdown.GetCountDownDurationUseCase
import com.illiouchine.toothbrush.usecase.notification.UpdateNotificationUseCase
import com.illiouchine.toothbrush.usecase.countdown.SetCountDownDurationUseCase
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
    private val updateNotificationUseCase: UpdateNotificationUseCase,
) : MviViewModel<Intent, Action, PartialState, State>() {

    init {
        setAction { Action.LoadSettings }
    }

    override fun createInitialState(): State {
        return State(
            countDownSettings = State.CountDownSettings.Loading,
            event = null
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
            is Intent.NotificationChanged -> {
                Action.ChangeNotification(
                    checked = intent.checked,
                    reminderType = intent.reminderType,
                    hour = intent.hour,
                    min = intent.min
                )
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
            is Action.ChangeNotification -> {
                updateNotification(
                    checked = action.checked,
                    reminderType = action.reminderType,
                    hour = action.hour,
                    min = action.min
                )
            }
        }
    }

    private fun updateNotification(
        checked: Boolean,
        reminderType: SettingsContract.ReminderType,
        hour: Int,
        min: Int
    ) {
        updateNotificationUseCase(checked, reminderType.toDayPeriod(), hour, min)
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

private fun SettingsContract.ReminderType.toDayPeriod(): UpdateNotificationUseCase.DayPeriod {
    return when(this){
        SettingsContract.ReminderType.Evening -> UpdateNotificationUseCase.DayPeriod.Evening
        SettingsContract.ReminderType.Midday -> UpdateNotificationUseCase.DayPeriod.Midday
        SettingsContract.ReminderType.Morning -> UpdateNotificationUseCase.DayPeriod.Morning
    }
}
