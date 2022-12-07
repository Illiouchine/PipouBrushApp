package com.illiouchine.toothbrush.ui.composable.settings.reminder

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

sealed class ReminderType{
    object Morning : ReminderType()
    object Midday : ReminderType()
    object Evening : ReminderType()
}

@Preview
@Composable
fun ReminderView(
    onAlarmCheckedChanged: ((checked: Boolean, reminderType: ReminderType, hour: Int, min: Int) -> Unit) = { _,_,_,_ -> },
    onNotificationCheckedChanged: ((checked: Boolean, reminderType: ReminderType, hour: Int, min: Int) -> Unit) = { _,_,_,_ -> },
) {
    Column {
        ReminderRow(
            reminderType = ReminderType.Morning,
            onAlarmCheckedChanged = { checked: Boolean, reminderType: ReminderType, hour: Int, min: Int ->
                onAlarmCheckedChanged(checked, reminderType, hour, min)
            },
            onNotificationCheckedChanged = { checked: Boolean, reminderType: ReminderType, hour: Int, min: Int ->
                onNotificationCheckedChanged(checked, reminderType, hour, min)
            },
        )
        ReminderRow(
            reminderType = ReminderType.Midday,
            onAlarmCheckedChanged = { checked: Boolean, reminderType: ReminderType, hour: Int, min: Int ->
                onAlarmCheckedChanged(checked, reminderType, hour, min)
            },
            onNotificationCheckedChanged = { checked: Boolean, reminderType: ReminderType, hour: Int, min: Int ->
                onNotificationCheckedChanged(checked, reminderType, hour, min)
            },
        )
        ReminderRow(
            reminderType = ReminderType.Evening,
            onAlarmCheckedChanged = { checked: Boolean, reminderType: ReminderType, hour: Int, min: Int ->
                onAlarmCheckedChanged(checked, reminderType, hour, min)
            },
            onNotificationCheckedChanged = { checked: Boolean, reminderType: ReminderType, hour: Int, min: Int ->
                onNotificationCheckedChanged(checked, reminderType, hour, min)
            },
        )
    }
}


