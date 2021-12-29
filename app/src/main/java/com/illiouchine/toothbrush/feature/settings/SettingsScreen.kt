package com.illiouchine.toothbrush.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsState.Timer
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel
) {
    val settingsState by viewModel.uiState.collectAsState()

    when (settingsState.timer) {
        Timer.Choosing -> {
            TODO()
        }
        Timer.Loading -> TODO()
        is Timer.TimerDuration -> TODO()
    }
}