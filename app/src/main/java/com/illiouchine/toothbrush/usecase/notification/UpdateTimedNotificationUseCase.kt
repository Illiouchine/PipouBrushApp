package com.illiouchine.toothbrush.usecase.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.work.*
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

class UpdateTimedNotificationUseCase @Inject constructor(
    @ApplicationContext
    private val context: Context
) {

    enum class DayPeriod(val workId: String, val notificationId: Int) {
        Morning("appName_notification_work_morning", 100),
        Midday("appName_notification_work_midday", 200),
        Evening("appName_notification_work_evening", 300),
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    operator fun invoke(
        activate: Boolean,
        dayPeriod: DayPeriod,
        hour: Int,
        min: Int
    ) {
        // Set the alarm to start at 8:30 a.m.
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, min)
        }

        scheduleAlarmManager(calendar, dayPeriod, activate)
    }

    private fun scheduleAlarmManager(calendar: Calendar, dayPeriod: DayPeriod, activate: Boolean) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java).let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.getBroadcast(context, 0, it, PendingIntent.FLAG_IMMUTABLE)
            } else {
                PendingIntent.getBroadcast(context, 0, it, 0)
            }

        }
        if (activate){
            alarmManager?.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                intent
            )
        } else {
            alarmManager?.cancel(intent)
        }
    }

    class AlarmReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            context?.let {
                val triggerNotification = TriggerNotificationUseCase(it)
                triggerNotification(1)
            }
        }
    }

    // Trigger when device boot completed
    class BootReceiver: BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == "android.intent.action.BOOT_COMPLETED") {
                // Set the alarm here.
                // TODO
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
}