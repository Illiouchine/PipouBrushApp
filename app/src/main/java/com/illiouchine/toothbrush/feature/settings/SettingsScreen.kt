package com.illiouchine.toothbrush.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.illiouchine.toothbrush.ui.composable.settings.CountDownSettingsView
import com.illiouchine.toothbrush.ui.composable.settings.CountDownState
import kotlin.time.Duration
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsState.CountDownSettings as CountDownSettings

@Preview
@Composable
fun SettingsScreen(
    countDownSettings: CountDownSettings = CountDownSettings.Loading,
    onCountDownDurationChanged: (Duration) -> Unit = {}
) {
    Column {
        Row {
            CountDownSettingsView(
                countDownState = countDownSettings.toCountDownState(),
                onCountDownDurationChanged = { onCountDownDurationChanged(it) }
            )
        }
    }
}

fun CountDownSettings.toCountDownState(): CountDownState {
    return when (this){
        CountDownSettings.Loading -> CountDownState.Loading
        is CountDownSettings.Loaded -> {
            CountDownState.Loaded(duration = countDownDuration)
        }
    }
}