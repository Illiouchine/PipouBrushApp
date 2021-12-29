package com.illiouchine.toothbrush.feature.settings

import com.illiouchine.mvi.core.MviViewModel
import com.illiouchine.mvi.core.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.DayOfWeek
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsAction as Action
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsEvent as Event
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsIntent as Intent
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsPartialState as PartialState
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsState as State


@ExperimentalTime
@HiltViewModel
class SettingsViewModel @Inject constructor(

) : MviViewModel<Intent, Action, PartialState, State, Event>() {

    init {
        setAction { Action.LoadSettings }
    }

    override fun createInitialState(): State {
        return State(
            timer = State.Timer.Loading
        )
    }

    override fun createReducer(): Reducer<State, PartialState> {
        return object : Reducer<State, PartialState>() {
            override fun reduce(
                currentState: State,
                partialState: PartialState
            ): State {
                return when (partialState) {
                    else -> {
                        TODO("")
                    }
                }
            }

        }
    }

    override fun handleUserIntent(intent: Intent): Action {
        return when (intent) {
            is Intent.UpdateTimerDuration -> {
                Action.SaveTimerDuration(intent.duration)
            }
            is Intent.AddReminder -> {
                Action.SaveReminder(
                    dayOfWeek = DayOfWeek.MONDAY,
                    hourOfWeek = 1
                )
            }
        }
    }

    override suspend fun handleAction(action: SettingsContract.SettingsAction) {
        TODO("Not yet implemented")
    }

}