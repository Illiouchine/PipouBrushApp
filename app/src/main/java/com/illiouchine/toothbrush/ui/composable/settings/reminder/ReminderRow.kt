package com.illiouchine.toothbrush.ui.composable.settings.reminder

import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                                        onClickLabel = "change time"
                                    )
                                    .semantics {
                                        contentDescription = getTimeTextAccessibilityLabel(
                                            reminderDayPeriod,
                                            getFormattedTime(hour = hour.value, min = min.value),
                                        )
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
                                        stateDescription = getSwitchAccessibilityLabel(
                                            reminderDayPeriod,
                                            reminderViewState.checked
                                        )
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

fun getTimeTextAccessibilityLabel(
    reminderDayPeriod: ReminderDayPeriod,
    formattedTime: String
): String {
    val dayPeriod = when(reminderDayPeriod){
        ReminderDayPeriod.Evening -> {
            "Evening"
        }
        ReminderDayPeriod.Midday -> {
            "Midday"
        }
        ReminderDayPeriod.Morning -> {
            "Morning"
        }
    }
    return "$dayPeriod notification set to $formattedTime"
}

private fun getSwitchAccessibilityLabel(
    reminderDayPeriod: ReminderDayPeriod = ReminderDayPeriod.Morning,
    checked: Boolean
): String {
    val dayPeriod = when(reminderDayPeriod){
        ReminderDayPeriod.Evening -> {
            "Evening"
        }
        ReminderDayPeriod.Midday -> {
            "Midday"
        }
        ReminderDayPeriod.Morning -> {
            "Morning"
        }
    }
    return if (checked){
        "$dayPeriod notification is activated"
    } else {
        "$dayPeriod notification is disabled"
    }
}

private fun getFormattedTime(hour: Int, min: Int): String {
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, min)
    }
    return SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)
}