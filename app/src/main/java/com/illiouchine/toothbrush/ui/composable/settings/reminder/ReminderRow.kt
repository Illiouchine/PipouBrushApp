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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import java.util.*

@Preview
@Composable
fun ReminderRow(
    reminderType: ReminderType = ReminderType.Morning,
    onNotificationCheckedChanged: (checked: Boolean, reminderType: ReminderType, hour: Int, min: Int) -> Unit = { _,_,_,_ -> },
) {
    // Declaring and initializing a calendar
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    // Value for storing time as a string
    val mTime = remember { mutableStateOf("$mHour:$mMinute") }

    val timePickerDialog = TimePickerDialog(
        /* context = */ LocalContext.current,
        /* listener = */ { _, mHour : Int, mMinute: Int ->
            mTime.value = "$mHour:$mMinute"
        },
        /* hourOfDay = */ mHour,
        /* minute = */ mMinute,
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
        ){
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
                    Text(
                        text = mTime.value,
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
                    ReminderSwitch("Notification") { checked ->
                        onNotificationCheckedChanged(checked, reminderType, mHour, mMinute)
                    }
                }
            }
        }
    }
}