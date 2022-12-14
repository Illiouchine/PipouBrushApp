package com.illiouchine.toothbrush.usecase.datagateway

import com.illiouchine.toothbrush.usecase.datagateway.entities.Reminder

interface ReminderDataGateway {

    suspend fun getReminder(dayPeriod: Reminder.DayPeriod): Reminder
    suspend fun setReminder(reminder: Reminder)
}