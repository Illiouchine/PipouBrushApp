package com.illiouchine.toothbrush.usecase.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.job.JobInfo
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.*
import com.illiouchine.toothbrush.R
import com.illiouchine.toothbrush.feature.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.System.currentTimeMillis
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class UpdateNotificationUseCase @Inject constructor(
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
    ){
        val currentCalendar = Calendar.getInstance()

        val triggerCalendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, min)
        }

        if (triggerCalendar.before(currentCalendar)){
            triggerCalendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        val data = Data.Builder().putInt(NotificationWorker.NOTIFICATION_ID, dayPeriod.notificationId).build()
        val delay = triggerCalendar.timeInMillis - currentCalendar.timeInMillis

        scheduleNotification(delay, data, dayPeriod.workId)
    }

    private fun scheduleNotification(delay: Long, data: Data, workerId: String) {
        val notificationWork = PeriodicWorkRequest.Builder(
            NotificationWorker::class.java,
            1,
            TimeUnit.DAYS
        )
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .setInputData(data)
            .build()

        val instanceWorkManager = WorkManager.getInstance(context)
        instanceWorkManager.enqueueUniquePeriodicWork(workerId, ExistingPeriodicWorkPolicy.REPLACE, notificationWork)
    }
}