package com.illiouchine.toothbrush.database.datasource.reminder

import com.illiouchine.toothbrush.database.dataobject.ReminderObject

interface ReminderDataSource {
    suspend fun getReminder(dayPeriod: ReminderObject.DayPeriod): ReminderObject
    suspend fun setReminder(reminderObject: ReminderObject)
}

