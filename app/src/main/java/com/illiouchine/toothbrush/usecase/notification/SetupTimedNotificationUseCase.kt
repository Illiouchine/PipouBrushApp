package com.illiouchine.toothbrush.usecase.notification

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.*
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SetupTimedNotificationUseCase @Inject constructor(
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
        val currentCalendar = Calendar.getInstance()

        val triggerCalendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, min)
        }

        if (triggerCalendar.before(currentCalendar)) {
            triggerCalendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        val delay = triggerCalendar.timeInMillis - currentCalendar.timeInMillis

        scheduleNotificationWorker(delay, dayPeriod)
    }

    private fun scheduleNotificationWorker(delay: Long, dayPeriod: DayPeriod) {

        val data = Data.Builder()
            .putInt(
                NotificationWorker.WORKER_NOTIFICATION_ID_KEY,
                dayPeriod.notificationId
            )
            .build()

        val notificationWork = PeriodicWorkRequest.Builder(
            NotificationWorker::class.java,
            1,
            TimeUnit.DAYS
        )
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .setInputData(data)
            .build()

        val instanceWorkManager = WorkManager.getInstance(context)
        instanceWorkManager.enqueueUniquePeriodicWork(
            dayPeriod.workId,
            ExistingPeriodicWorkPolicy.REPLACE,
            notificationWork
        )
    }
}