package com.illiouchine.toothbrush.ui.composable.settings.reminder

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

sealed class ReminderType{
    object Morning : ReminderType()
    object Midday : ReminderType()
    object Evening : ReminderType()
}

@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
fun ReminderView(
    onNotificationCheckedChanged: ((checked: Boolean, reminderType: ReminderType, hour: Int, min: Int) -> Unit) = { _,_,_,_ -> },
) {

    // Manage SCHEDULE_EXACT_ALARM permission too
    val notificationPermissionState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(
            permission = Manifest.permission.POST_NOTIFICATIONS
        )
    } else {
        rememberPermissionState(permission ="android.permission.POST_NOTIFICATIONS")
    }

    Column {
        if (!notificationPermissionState.status.isGranted) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ) {
                if (notificationPermissionState.status.shouldShowRationale) {
                    // If the user has denied the permission but the rationale can be shown,
                    // then gently explain why the app requires this permission
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "The Notification permission is important for this app. Please grant the permission."
                        )
                        Button(
                            onClick = { notificationPermissionState.launchPermissionRequest() },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text("Request Permission")
                        }
                    }
                } else {
                    // If it's the first time the user lands on this feature, or the user
                    // doesn't want to be asked again for this permission, explain that the
                    // permission is required
                    Text(
                        text = "The Notification permission required for this feature to be available. " +
                                "Please grant the permission in phone settings."
                    )
                }
            }
        }
        ReminderRow(
            reminderType = ReminderType.Morning,
            enabledSwitch = notificationPermissionState.status.isGranted,
            onNotificationCheckedChanged = { checked: Boolean, reminderType: ReminderType, hour: Int, min: Int ->
                onNotificationCheckedChanged(checked, reminderType, hour, min)
            },
        )
        ReminderRow(
            reminderType = ReminderType.Midday,
            enabledSwitch = notificationPermissionState.status.isGranted,
            onNotificationCheckedChanged = { checked: Boolean, reminderType: ReminderType, hour: Int, min: Int ->
                onNotificationCheckedChanged(checked, reminderType, hour, min)
            },
        )
        ReminderRow(
            reminderType = ReminderType.Evening,
            enabledSwitch = notificationPermissionState.status.isGranted,
            onNotificationCheckedChanged = { checked: Boolean, reminderType: ReminderType, hour: Int, min: Int ->
                onNotificationCheckedChanged(checked, reminderType, hour, min)
            },
        )
    }
}


