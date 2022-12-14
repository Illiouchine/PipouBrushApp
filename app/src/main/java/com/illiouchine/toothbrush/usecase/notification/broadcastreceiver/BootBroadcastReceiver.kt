package com.illiouchine.toothbrush.usecase.notification.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.illiouchine.toothbrush.database.dataobject.ReminderObject
import com.illiouchine.toothbrush.database.datasource.reminder.ReminderDataSourceSharedPref
import com.illiouchine.toothbrush.usecase.notification.NotificationDayPeriod
import com.illiouchine.toothbrush.usecase.notification.TriggerNotificationUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

// Trigger when device boot completed
class BootBroadcastReceiver : BroadcastReceiver() {

    private val scope = CoroutineScope(SupervisorJob())

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {

            val pendingResult: PendingResult = goAsync()


            // Set the alarm here.
            val reminderSharedPref = ReminderDataSourceSharedPref(context)
            val triggerNotification = TriggerNotificationUseCase(context)

            scope.launch(Dispatchers.Default) {
                try {
                    val morningReminder =
                        reminderSharedPref.getReminder(dayPeriod = ReminderObject.DayPeriod.Morning)
                    val middayReminder =
                        reminderSharedPref.getReminder(dayPeriod = ReminderObject.DayPeriod.Midday)
                    val eveningReminder =
                        reminderSharedPref.getReminder(dayPeriod = ReminderObject.DayPeriod.Evening)

                    if (morningReminder.enabled) {
                        triggerNotification(NotificationDayPeriod.Morning.notificationId)
                    }
                    if (middayReminder.enabled) {
                        triggerNotification(NotificationDayPeriod.Midday.notificationId)
                    }
                    if (eveningReminder.enabled) {
                        triggerNotification(NotificationDayPeriod.Evening.notificationId)
                    }
                } finally {
                    pendingResult.finish()
                }
            }
        }
    }
}

/**
Notice that in the manifest, the boot receiver is set to android:enabled="false". This means that the receiver will not be called unless the application explicitly enables it. This prevents the boot receiver from being called unnecessarily. You can enable a receiver (for example, if the user sets an alarm) as follows:
Kotlin
Java

val receiver = ComponentName(context, SampleBootReceiver::class.java)

context.packageManager.setComponentEnabledSetting(
receiver,
PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
PackageManager.DONT_KILL_APP
)

Once you enable the receiver this way, it will stay enabled, even if the user reboots the device. In other words, programmatically enabling the receiver overrides the manifest setting, even across reboots. The receiver will stay enabled until your app disables it. You can disable a receiver (for example, if the user cancels an alarm) as follows:
Kotlin
Java

val receiver = ComponentName(context, SampleBootReceiver::class.java)

context.packageManager.setComponentEnabledSetting(
receiver,
PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
PackageManager.DONT_KILL_APP
)
 **/