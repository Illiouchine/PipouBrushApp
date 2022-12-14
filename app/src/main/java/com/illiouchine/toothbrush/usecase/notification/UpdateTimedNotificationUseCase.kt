package com.illiouchine.toothbrush.usecase.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.work.*
import com.illiouchine.toothbrush.usecase.notification.broadcastreceiver.AlarmBroadcastReceiver
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

class UpdateTimedNotificationUseCase @Inject constructor(
    @ApplicationContext
    private val context: Context
) {
    @SuppressLint("UnspecifiedImmutableFlag")
    operator fun invoke(
        activate: Boolean,
        notificationDayPeriod: NotificationDayPeriod,
        hour: Int,
        min: Int
    ) {
        // Set the alarm to start at 8:30 a.m.
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, min)
        }

        scheduleAlarmManager(calendar, notificationDayPeriod, activate)
    }

    private fun scheduleAlarmManager(
        calendar: Calendar,
        notificationDayPeriod: NotificationDayPeriod,
        activate: Boolean
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
            .apply {
                putExtra("dayPeriod", notificationDayPeriod.name)
                action = notificationDayPeriod.intentAction
            }
            .let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    PendingIntent.getBroadcast(context, 0, it, PendingIntent.FLAG_IMMUTABLE)
                } else {
                    PendingIntent.getBroadcast(context, 0, it, 0)
                }
            }
        if (activate) {
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
}