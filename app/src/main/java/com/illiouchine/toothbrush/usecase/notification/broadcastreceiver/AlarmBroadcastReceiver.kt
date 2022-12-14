package com.illiouchine.toothbrush.usecase.notification.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.illiouchine.toothbrush.usecase.notification.NotificationDayPeriod
import com.illiouchine.toothbrush.usecase.notification.TriggerNotificationUseCase

class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val triggerNotification = TriggerNotificationUseCase(it)
            when (intent?.action) {
                NotificationDayPeriod.Morning.intentAction -> {
                    triggerNotification(NotificationDayPeriod.Morning.notificationId)
                }
                NotificationDayPeriod.Midday.intentAction -> {
                    triggerNotification(NotificationDayPeriod.Midday.notificationId)
                }
                NotificationDayPeriod.Evening.intentAction -> {
                    triggerNotification(NotificationDayPeriod.Evening.notificationId)
                }
            }
        }
    }
}