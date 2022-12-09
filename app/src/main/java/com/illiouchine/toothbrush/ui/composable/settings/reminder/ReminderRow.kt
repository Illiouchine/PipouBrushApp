package com.illiouchine.toothbrush.ui.composable.settings.reminder

import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@Preview
@Composable
fun ReminderRow(
    reminderType: ReminderType = ReminderType.Morning,
    enabledSwitch: Boolean = true,
    onNotificationCheckedChanged: (checked: Boolean, reminderType: ReminderType, hour: Int, min: Int) -> Unit = { _, _, _, _ -> },
) {

    val initialHour = when(reminderType){
        ReminderType.Evening -> 19
        ReminderType.Midday -> 12
        ReminderType.Morning -> 8
    }
    val initialMin = 0

    val hour = remember { mutableStateOf(initialHour) }
    val min = remember { mutableStateOf(initialMin) }

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
            .wrapContentHeight()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            Row {
                ReminderRowTitle()
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(.6f)
                ) {
                    val calendar = Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, hour.value)
                        set(Calendar.MINUTE, min.value)
                    }
                    val formattedTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)
                    Text(
                        text = formattedTime,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                            .clickable { timePickerDialog.show() }
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(.4f)
                ) {
                    ReminderSwitch(
                        text = "Notification",
                        enabled = enabledSwitch
                    ) { checked ->
                        onNotificationCheckedChanged(checked, reminderType, hour.value, min.value)
                    }
                }
            }
        }
    }
}