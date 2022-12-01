package com.illiouchine.toothbrush.feature.settings

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsState.CountDownSettings
import com.illiouchine.toothbrush.ui.composable.PipouBackground
import com.illiouchine.toothbrush.ui.composable.settings.CountDownSettingsView
import com.illiouchine.toothbrush.ui.composable.settings.CountDownState
import com.illiouchine.toothbrush.ui.typography
import kotlin.time.Duration

@Preview
@Composable
fun SettingsScreen(
    countDownSettings: CountDownSettings = CountDownSettings.Loading,
    event: SettingsContract.SettingsState.SettingsEvent? = null,
    onCountDownDurationChanged: (Duration) -> Unit = {},
    onEventHandled: (SettingsContract.SettingsState.SettingsEvent) -> Unit = {}
) {
    PipouBackground(enableBlur = true){
        val context = LocalContext.current
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Settings", style = typography.titleLarge)
            Row {
                CountDownSettingsView(
                    countDownState = countDownSettings.toCountDownState(),
                    onCountDownDurationChanged = { onCountDownDurationChanged(it) }
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Reminder", style = typography.titleLarge)
            Text(text = "Alarme en construction",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
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
            onEventHandled(event)
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