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
    onNotificationCheckedChanged: ((checked: Boolean, reminderType: ReminderType, hour: Int, min: Int) -> Unit) = { _,_,_,_ -> },
) {
    Column {
        ReminderRow(
            reminderType = ReminderType.Morning,
            onNotificationCheckedChanged = { checked: Boolean, reminderType: ReminderType, hour: Int, min: Int ->
                onNotificationCheckedChanged(checked, reminderType, hour, min)
            },
        )
        ReminderRow(
            reminderType = ReminderType.Midday,
            onNotificationCheckedChanged = { checked: Boolean, reminderType: ReminderType, hour: Int, min: Int ->
                onNotificationCheckedChanged(checked, reminderType, hour, min)
            },
        )
        ReminderRow(
            reminderType = ReminderType.Evening,
            onNotificationCheckedChanged = { checked: Boolean, reminderType: ReminderType, hour: Int, min: Int ->
                onNotificationCheckedChanged(checked, reminderType, hour, min)
            },
        )
    }
}


