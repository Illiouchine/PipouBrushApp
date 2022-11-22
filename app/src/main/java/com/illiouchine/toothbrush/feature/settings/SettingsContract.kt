package com.illiouchine.toothbrush.feature.settings

import com.illiouchine.mvi.core.*

interface SettingsContract {

    sealed class SettingsIntent : UiIntent {
        object LoadScreen: SettingsIntent()
    }

    sealed class SettingsAction : UiAction {
        object LoadSettings : SettingsAction()
    }

    sealed class SettingsState : UiState {
        object Loaded : SettingsState()
    }

    sealed class SettingsPartialState : UiPartialState {
        object Loaded: SettingsPartialState()
        object ErrorLoading: SettingsPartialState()
    }

    sealed class SettingsEvent() : UiEvent

}