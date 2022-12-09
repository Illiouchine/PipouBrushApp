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
        val triggerCalendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, min)
        }
        val currentCalendar = Calendar.getInstance()
        if (triggerCalendar.before(currentCalendar)){
            triggerCalendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        val data = Data.Builder().putInt(NotificationWorker.NOTIFICATION_ID, 0).build()
        val delay = triggerCalendar.timeInMillis - currentTimeMillis()

        //scheduleNotification(delay, data)
        sendNotification(1)
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


    // TEMPORAIRE
    private fun sendNotification(id: Int) {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra(NotificationWorker.NOTIFICATION_ID, id)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val titleNotification = "Time to brush your tooth" //applicationContext.getString(R.string.notification_title)
        val subtitleNotification = "subtitleNotification" //applicationContext.getString(R.string.notification_subtitle)
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getActivity(context, 0, intent, 0)
        }
        val notification = NotificationCompat.Builder(context,
            NotificationWorker.NOTIFICATION_CHANNEL
        )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(titleNotification).setContentText(subtitleNotification)
            .setDefaults(Notification.DEFAULT_ALL)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        notification.priority = JobInfo.PRIORITY_MAX

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification.setChannelId(NotificationWorker.NOTIFICATION_CHANNEL)

            val ringtoneManager = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()

            val channel =
                NotificationChannel(NotificationWorker.NOTIFICATION_CHANNEL, NotificationWorker.NOTIFICATION_NAME, NotificationManager.IMPORTANCE_HIGH)

            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            channel.setSound(ringtoneManager, audioAttributes)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(id, notification.build())
    }
}