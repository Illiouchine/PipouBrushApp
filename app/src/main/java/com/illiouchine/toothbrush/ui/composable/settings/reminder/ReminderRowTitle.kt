package com.illiouchine.toothbrush.ui.composable.settings.reminder

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ReminderRowTitle(reminderType: ReminderType = ReminderType.Morning) {
    when(reminderType){
        ReminderType.Evening -> {
            Text(
                text = "Evening reminder",
                style = MaterialTheme.typography.titleSmall,
            )
        }
        ReminderType.Midday -> {
            Text(
                text = "Midday reminder",
                style = MaterialTheme.typography.titleSmall,
            )
        }
        ReminderType.Morning -> {
            Text(
                text = "Morning reminder",
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}