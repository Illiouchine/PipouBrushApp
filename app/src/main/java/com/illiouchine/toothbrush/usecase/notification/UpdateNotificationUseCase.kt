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

    sealed class DayPeriod {
        object Morning : DayPeriod()
        object Midday : DayPeriod()
        object Evening : DayPeriod()
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

        val data = Data.Builder().putInt(NotificationWorker.NOTIFICATION_ID, 0).build()
        val delay = triggerCalendar.timeInMillis - currentCalendar.timeInMillis

        scheduleNotification(delay, data)
    }

    private fun scheduleNotification(delay: Long, data: Data) {
        val notificationWork = PeriodicWorkRequest.Builder(
            NotificationWorker::class.java,
            1, TimeUnit.DAYS
        )
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .setInputData(data)
            .build()

        val instanceWorkManager = WorkManager.getInstance(context)
        instanceWorkManager.enqueueUniquePeriodicWork(NotificationWorker.NOTIFICATION_WORK, ExistingPeriodicWorkPolicy.REPLACE, notificationWork)
    }
}