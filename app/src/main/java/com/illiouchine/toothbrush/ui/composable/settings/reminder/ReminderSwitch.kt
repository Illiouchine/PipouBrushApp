package com.illiouchine.toothbrush.ui.composable.settings.reminder

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ReminderSwitch(
    text: String = "Alarm",
    initialCheckedState : Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
){

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            modifier = Modifier.padding(end = 8.dp),
            checked = initialCheckedState,
            onCheckedChange = { onCheckedChange(it) },
            colors = SwitchDefaults.colors(
                checkedTrackColor = MaterialTheme.colorScheme.secondary
            )
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}