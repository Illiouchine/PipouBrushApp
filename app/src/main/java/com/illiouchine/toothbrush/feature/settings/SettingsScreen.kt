package com.illiouchine.toothbrush.feature.settings

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.R
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsState.CountDownSettings
import com.illiouchine.toothbrush.feature.settings.SettingsContract.SettingsState.ReminderState
import com.illiouchine.toothbrush.ui.composable.PipouBackgroundV2
import com.illiouchine.toothbrush.ui.composable.settings.CountDownSettingsView
import com.illiouchine.toothbrush.ui.composable.settings.CountDownState
import com.illiouchine.toothbrush.ui.composable.settings.ThanksView
import com.illiouchine.toothbrush.ui.composable.settings.reminder.ReminderDayPeriod
import com.illiouchine.toothbrush.ui.composable.settings.reminder.ReminderView
import com.illiouchine.toothbrush.ui.composable.settings.reminder.ReminderViewState
import com.illiouchine.toothbrush.ui.typography
import kotlin.time.Duration

@Preview
@Composable
fun SettingsScreen(
    countDownSettings: CountDownSettings = CountDownSettings.Loading,
    morningReminder: ReminderState = ReminderState.Loading,
    middayReminder: ReminderState = ReminderState.Loading,
    eveningReminder: ReminderState = ReminderState.Loading,
    event: SettingsContract.SettingsState.SettingsEvent? = null,
    onCountDownDurationChanged: (Duration) -> Unit = {},
    onEventHandled: (SettingsContract.SettingsState.SettingsEvent) -> Unit = {},
    onNotificationCheckedChanged: ((checked: Boolean, reminderDayPeriod: ReminderDayPeriod, hour: Int, min: Int) -> Unit) = { _, _, _, _ -> },
) {
    PipouBackgroundV2 {
        val context = LocalContext.current
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(R.string.settings_title), style = typography.titleLarge)
            CountDownSettingsView(
                countDownState = countDownSettings.toCountDownState(),
                onCountDownDurationChanged = { onCountDownDurationChanged(it) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.settings_reminder_title),
                style = typography.titleLarge
            )
            ReminderView(
                morningReminder = morningReminder.toViewState(),
                middayReminder = middayReminder.toViewState(),
                eveningReminder = eveningReminder.toViewState(),
                onNotificationCheckedChanged = { activate, reminderType, hour, min ->
                    onNotificationCheckedChanged(activate, reminderType, hour, min)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.settings_thanks_title),
                style = typography.titleLarge
            )
            ThanksView()
        }

        if (event != null) {
            when (event) {
                is SettingsContract.SettingsState.SettingsEvent.CountDownSaved -> {
                    Toast.makeText(
                        context,
                        stringResource(R.string.setting_toast_countdown_saved),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is SettingsContract.SettingsState.SettingsEvent.ErrorLoadingCountDownDuration -> {
                    Toast.makeText(
                        context,
                        stringResource(R.string.setting_toast_countdown_loading_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is SettingsContract.SettingsState.SettingsEvent.ErrorSavingCountDownDuration -> {
                    Toast.makeText(
                        context,
                        stringResource(R.string.setting_toast_countdown_save_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is SettingsContract.SettingsState.SettingsEvent.ErrorLoadingReminder -> {
                    Toast.makeText(
                        context,
                        stringResource(R.string.setting_toast_reminder_loading_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            onEventHandled(event)
        }
    }
}

private fun ReminderState.toViewState(): ReminderViewState {
    return when (this) {
        is ReminderState.Loaded -> ReminderViewState.Loaded(
            checked = this.enabled,
            hour = this.hour,
            min = this.min
        )
        ReminderState.Loading -> ReminderViewState.Loading
    }
}

private fun CountDownSettings.toCountDownState(): CountDownState {
    return when (this) {
        CountDownSettings.Loading -> CountDownState.Loading
        is CountDownSettings.Loaded -> {
            CountDownState.Loaded(duration = countDownDuration)
        }
    }
}