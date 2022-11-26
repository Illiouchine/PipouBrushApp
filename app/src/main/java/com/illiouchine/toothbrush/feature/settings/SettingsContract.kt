package com.illiouchine.toothbrush.feature.settings

import com.illiouchine.mvi.core.*
import kotlin.time.Duration

interface SettingsContract {

    sealed class SettingsIntent : UiIntent {
        data class UpdateCountDownDuration(val duration: Duration) : SettingsIntent()
    }

    sealed class SettingsAction : UiAction {
        data class UpdateCountDownDuration(val duration: Duration) : SettingsAction()

        object LoadSettings : SettingsAction()
    }

    data class SettingsState(
        val countDownSettings: CountDownSettings,
    ) : UiState {
        sealed class CountDownSettings{
            object Loading : CountDownSettings()
            data class Loaded(
                val countDownDuration : Duration
            ): CountDownSettings()
        }
    }

    sealed class SettingsPartialState : UiPartialState {
        data class CountDownDurationLoaded(
            val countDownDuration: Duration
        ) : SettingsPartialState()
    }

    sealed class SettingsEvent : UiEvent {
        data class ErrorLoadingCountDownDuration(
            val exception: Exception
        ) : SettingsEvent()

        data class ErrorSavingCountDownDuration(val exception: Exception) : SettingsEvent()
        data class CountDownSaved(val duration: Duration) : SettingsEvent()
    }

}