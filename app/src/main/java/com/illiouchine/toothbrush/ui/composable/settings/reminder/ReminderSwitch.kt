package com.illiouchine.toothbrush.ui.composable.settings.reminder

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
fun ReminderSwitch(
    text: String = "Alarm",
    initialCheckedState : Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
){

    // TODO Manage < Tiramisu
    val notificationPermissionState = rememberPermissionState(
        permission = android.Manifest.permission.POST_NOTIFICATIONS
    )

    if (notificationPermissionState.status.isGranted){
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
    } else {
        Row {
            Button(
                onClick = { notificationPermissionState.launchPermissionRequest() }
            ) {
                Text("notification permission required for this feature")
            }
        }
    }

}