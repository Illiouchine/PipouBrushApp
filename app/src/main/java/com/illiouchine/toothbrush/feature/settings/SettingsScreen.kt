package com.illiouchine.toothbrush.feature.settings

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsState.CountDownSettings
import com.illiouchine.toothbrush.ui.composable.settings.CountDownSettingsView
import com.illiouchine.toothbrush.ui.composable.settings.CountDownState
import kotlin.time.Duration

@Preview
@Composable
fun SettingsScreen(
    countDownSettings: CountDownSettings = CountDownSettings.Loading,
    event: SettingsContract.SettingsState.SettingsEvent? = null,
    onCountDownDurationChanged: (Duration) -> Unit = {},
) {

    val context = LocalContext.current
    Column {
        Row {
            CountDownSettingsView(
                countDownState = countDownSettings.toCountDownState(),
                onCountDownDurationChanged = { onCountDownDurationChanged(it) }
            )
        }
    }

    if (event != null){
        when(event){
            is SettingsContract.SettingsState.SettingsEvent.CountDownSaved -> {
                Toast.makeText(context, "CountDownSaved", Toast.LENGTH_LONG).show()
            }
            is SettingsContract.SettingsState.SettingsEvent.ErrorLoadingCountDownDuration -> {
                Toast.makeText(context, "ErrorLoadingCountDownDuration", Toast.LENGTH_LONG).show()
            }
            is SettingsContract.SettingsState.SettingsEvent.ErrorSavingCountDownDuration -> {
                Toast.makeText(context, "ErrorSavingCountDownDuration", Toast.LENGTH_LONG).show()
            }
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