package com.illiouchine.toothbrush.ui.composable.settings.reminder

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.R
import java.text.SimpleDateFormat
import java.util.*

@Preview
@Composable
fun ReminderRow(
    reminderDayPeriod: ReminderDayPeriod = ReminderDayPeriod.Morning,
    reminderViewState: ReminderViewState = ReminderViewState.Loading,
    enabledSwitch: Boolean = true,
    onNotificationCheckedChanged: (checked: Boolean, reminderDayPeriod: ReminderDayPeriod, hour: Int, min: Int) -> Unit = { _, _, _, _ -> },
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            Row {
                ReminderRowTitle(reminderDayPeriod = reminderDayPeriod)
            }
            when (reminderViewState) {
                is ReminderViewState.Loaded -> {

                    val hour = remember { mutableStateOf(reminderViewState.hour) }
                    val min = remember { mutableStateOf(reminderViewState.min) }

                    val timePickerDialog = TimePickerDialog(
                        /* context = */ LocalContext.current,
                        /* listener = */ { _, selectedHour: Int, selectedMinute: Int ->
                            hour.value = selectedHour
                            min.value = selectedMinute
                        },
                        /* hourOfDay = */ hour.value,
                        /* minute = */ min.value,
                        /* is24HourView = */ true
                    )

                    val timeTextAccessibilityLabel = getTimeTextAccessibilityLabel(
                        context = LocalContext.current,
                        reminderDayPeriod = reminderDayPeriod,
                        formattedTime = getFormattedTime(hour = hour.value, min = min.value),
                    )

                    val switchAccessibilityLabel = getSwitchAccessibilityLabel(
                        context = LocalContext.current,
                        reminderDayPeriod = reminderDayPeriod,
                        checked = enabledSwitch
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(.6f),
                            verticalArrangement = Arrangement.Center
                        ) {
                            val formattedTime = getFormattedTime(hour = hour.value, min = min.value)
                            Text(
                                text = formattedTime,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable(
                                        onClick = { timePickerDialog.show() },
                                        onClickLabel = stringResource(R.string.setting_notification_change_time_accessibility_label)
                                    )
                                    .semantics {
                                        contentDescription = timeTextAccessibilityLabel
                                    }
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(.4f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Switch(
                                enabled = enabledSwitch,
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .semantics {
                                        stateDescription = switchAccessibilityLabel
                                    },
                                checked = reminderViewState.checked,
                                onCheckedChange = {
                                    onNotificationCheckedChanged(
                                        it,
                                        reminderDayPeriod,
                                        hour.value,
                                        min.value
                                    )
                                },
                                colors = SwitchDefaults.colors(
                                    checkedTrackColor = MaterialTheme.colorScheme.secondary
                                )
                            )
                        }
                    }
                }
                ReminderViewState.Loading -> {
                    Row {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}


private fun getTimeTextAccessibilityLabel(
    context: Context,
    reminderDayPeriod: ReminderDayPeriod,
    formattedTime: String
): String {
    val formattedDayPeriod =
        getFormattedDayPeriod(context = context, reminderDayPeriod = reminderDayPeriod)
    return context.getString(
        R.string.setting_notification_full_time_accessibility_label,
        formattedDayPeriod,
        formattedTime
    )
}

private fun getFormattedDayPeriod(
    context: Context,
    reminderDayPeriod: ReminderDayPeriod,
): String {
    return when (reminderDayPeriod) {
        ReminderDayPeriod.Evening -> {
            context.getString(R.string.setting_notification_evening_time_accessibility_label)
        }
        ReminderDayPeriod.Midday -> {
            context.getString(R.string.setting_notification_midday_time_accessibility_label)
        }
        ReminderDayPeriod.Morning -> {
            context.getString(R.string.setting_notification_morning_time_accessibility_label)
        }
    }
}

private fun getSwitchAccessibilityLabel(
    context: Context,
    reminderDayPeriod: ReminderDayPeriod = ReminderDayPeriod.Morning,
    checked: Boolean
): String {
    val dayPeriod = getFormattedDayPeriod(
        context = context,
        reminderDayPeriod = reminderDayPeriod
    )
    return if (checked) {
        context.getString(
            R.string.setting_notification_switch_activated_accessibility_label,
            dayPeriod
        )
    } else {
        context.getString(
            R.string.setting_notification_switch_disabled_accessibility_label,
            dayPeriod
        )
    }
}

private fun getFormattedTime(hour: Int, min: Int): String {
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, min)
    }
    return SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)
}