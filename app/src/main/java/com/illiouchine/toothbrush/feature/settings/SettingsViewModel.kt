package com.illiouchine.toothbrush.feature.settings

import com.illiouchine.mvi.core.MviViewModel
import com.illiouchine.mvi.core.Reducer
import com.illiouchine.toothbrush.usecase.GetCountDownDurationUseCase
import com.illiouchine.toothbrush.usecase.SetCountDownDurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.time.Duration
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsAction as Action
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsEvent as Event
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsIntent as Intent
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsPartialState as PartialState
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsState as State

// TODO Manage Event
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getCountDownDurationUseCase: GetCountDownDurationUseCase,
    private val setCountDownDurationUseCase: SetCountDownDurationUseCase
) : MviViewModel<Intent, Action, PartialState, State, Event>() {

    init {
        setAction { Action.LoadSettings }
    }

    override fun createInitialState(): State {
        return State(
            countDownSettings = State.CountDownSettings.Loading
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
                }
            }

        }
    }

    override fun handleUserIntent(intent: Intent): Action {
        return when (intent) {
            is SettingsContract.SettingsIntent.UpdateCountDownDuration -> {
                Action.UpdateCountDownDuration(
                    intent.duration
                )
            }
        }
    }

    override suspend fun handleAction(action: Action) {
        when(action) {
            Action.LoadSettings -> loadSettings()
            is Action.UpdateCountDownDuration -> saveCountDownDuration(action.duration)
        }
    }

    private suspend fun saveCountDownDuration(duration: Duration) {
        try {
            setCountDownDurationUseCase(duration)
            setEvent {
                Event.CountDownSaved(duration)
            }
        } catch (e: Exception){
            setEvent {
                Event.ErrorSavingCountDownDuration(exception = e)
            }
        }
    }

    private suspend fun loadSettings() {
        try {
            val countDownDuration = getCountDownDurationUseCase()
            setPartialState {
                PartialState.CountDownDurationLoaded(countDownDuration = countDownDuration)
            }
        } catch (e: Exception){
            setEvent {
                Event.ErrorLoadingCountDownDuration(exception = e)
            }
        }
    }
}