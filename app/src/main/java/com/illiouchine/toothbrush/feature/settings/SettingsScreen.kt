package com.illiouchine.toothbrush.feature.settings

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.R
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
            Text(text = stringResource(R.string.settings_title), style = typography.titleLarge)
            Row {
                CountDownSettingsView(
                    countDownState = countDownSettings.toCountDownState(),
                    onCountDownDurationChanged = { onCountDownDurationChanged(it) }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.settings_reminder_title), style = typography.titleLarge)
            Text(text = stringResource(R.string.misc_in_progress),
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.settings_thanks_title), style = typography.titleLarge)
            Text(text = stringResource(R.string.settings_thanks_krog),
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        if (event != null){
            when(event){
                is SettingsContract.SettingsState.SettingsEvent.CountDownSaved -> {
                    Toast.makeText(context, stringResource(R.string.setting_toast_countdown_saved), Toast.LENGTH_LONG).show()
                }
                is SettingsContract.SettingsState.SettingsEvent.ErrorLoadingCountDownDuration -> {
                    Toast.makeText(context, stringResource(R.string.setting_toast_countdown_loading_error), Toast.LENGTH_LONG).show()
                }
                is SettingsContract.SettingsState.SettingsEvent.ErrorSavingCountDownDuration -> {
                    Toast.makeText(context, stringResource(R.string.setting_toast_countdown_save_error), Toast.LENGTH_LONG).show()
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