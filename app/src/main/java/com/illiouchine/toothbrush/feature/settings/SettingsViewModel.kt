package com.illiouchine.toothbrush.feature.settings

import androidx.lifecycle.viewModelScope
import com.illiouchine.mvi.core.MviViewModel
import com.illiouchine.mvi.core.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsAction as Action
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsEvent as Event
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsIntent as Intent
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsPartialState as PartialState
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsState as State


@HiltViewModel
class SettingsViewModel @Inject constructor(

) : MviViewModel<Intent, Action, PartialState, State, Event>() {

    init {
        setAction { Action.LoadSettings }
    }

    override fun createInitialState(): State {
        return State.Loaded
    }

    override fun createReducer(): Reducer<State, PartialState> {
        return object : Reducer<State, PartialState>() {
            override fun reduce(
                currentState: State,
                partialState: PartialState
            ): State {
                return when (partialState) {
                    else -> {
                        //TODO("")
                        currentState
                    }
                }
            }

        }
    }

    override fun handleUserIntent(intent: Intent): Action {
        return when (intent) {
            Intent.LoadScreen -> Action.LoadSettings
        }
    }

    override suspend fun handleAction(action: Action) {
        when( action) {
            Action.LoadSettings -> loadSettings()
        }
    }
    private fun loadSettings() {
        viewModelScope.launch {
            try {
                setPartialState {
                    PartialState.Loaded
                }
            } catch (e: Exception) {
                setPartialState {
                    PartialState.ErrorLoading
                }
            }
        }
    }
}