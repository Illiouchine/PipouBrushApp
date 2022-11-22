package com.illiouchine.toothbrush.feature.settings

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue


@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel
) {
    val settingsState by viewModel.uiState.collectAsState()

    when (settingsState) {
        SettingsContract.SettingsState.Loaded -> {
            Surface {

            }
        }
    }
}