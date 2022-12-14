package com.illiouchine.toothbrush.ui.composable.settings.reminder

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ReminderRowTitle(reminderDayPeriod: ReminderDayPeriod = ReminderDayPeriod.Morning) {
    when(reminderDayPeriod){
        ReminderDayPeriod.Evening -> {
            Text(
                text = "Evening notification",
                style = MaterialTheme.typography.titleSmall,
            )
        }
        ReminderDayPeriod.Midday -> {
            Text(
                text = "Midday notification",
                style = MaterialTheme.typography.titleSmall,
            )
        }
        ReminderDayPeriod.Morning -> {
            Text(
                text = "Morning notification",
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}