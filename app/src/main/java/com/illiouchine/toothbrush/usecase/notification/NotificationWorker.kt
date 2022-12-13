package com.illiouchine.toothbrush.usecase.notification

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(
    val context: Context,
    params: WorkerParameters
): Worker(context, params){

    private val triggerNotification = TriggerNotificationUseCase(context = context)

    override fun doWork(): Result {
        val id = inputData.getLong(WORKER_NOTIFICATION_ID_KEY, 0).toInt()

        triggerNotification(id)

        return Result.success()
    }

    companion object {
        const val WORKER_NOTIFICATION_ID_KEY = "PipouBrushApp_notification_id"
    }
}