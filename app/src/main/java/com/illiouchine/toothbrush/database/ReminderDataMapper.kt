package com.illiouchine.toothbrush.database

import com.illiouchine.toothbrush.database.dataobject.ReminderObject
import com.illiouchine.toothbrush.database.datasource.reminder.ReminderDataSource
import com.illiouchine.toothbrush.usecase.datagateway.ReminderDataGateway
import com.illiouchine.toothbrush.usecase.datagateway.entities.Reminder
import javax.inject.Inject

class ReminderDataMapper @Inject constructor(
    private val reminderDataSource: ReminderDataSource,
) : ReminderDataGateway {

    override suspend fun getReminder(dayPeriod: Reminder.DayPeriod): Reminder {
        return reminderDataSource.getReminder(dayPeriod = dayPeriod.toObjectDayPeriod())
            .toReminderEntity()
    }

    override suspend fun setReminder(reminder: Reminder) {
        reminderDataSource.setReminder(reminder.toReminderObject())
    }
}

private fun Reminder.toReminderObject(): ReminderObject {
    return ReminderObject(
        dayPeriod = this.dayPeriod.toObjectDayPeriod(),
        hour = this.hour,
        min = this.min,
        enabled = this.enabled
    )
}

private fun Reminder.DayPeriod.toObjectDayPeriod(): ReminderObject.DayPeriod {
    return when (this){
        Reminder.DayPeriod.Morning -> ReminderObject.DayPeriod.Morning
        Reminder.DayPeriod.Midday -> ReminderObject.DayPeriod.Midday
        Reminder.DayPeriod.Evening -> ReminderObject.DayPeriod.Evening
    }
}

private fun ReminderObject.toReminderEntity(): Reminder {
    return Reminder(
        dayPeriod = this.dayPeriod.toEntityDayPeriod(),
        hour = this.hour,
        min = this.min,
        enabled = this.enabled
    )
}

private fun ReminderObject.DayPeriod.toEntityDayPeriod(): Reminder.DayPeriod {
    return when(this){
        ReminderObject.DayPeriod.Morning -> Reminder.DayPeriod.Morning
        ReminderObject.DayPeriod.Midday -> Reminder.DayPeriod.Midday
        ReminderObject.DayPeriod.Evening -> Reminder.DayPeriod.Evening
    }
}
