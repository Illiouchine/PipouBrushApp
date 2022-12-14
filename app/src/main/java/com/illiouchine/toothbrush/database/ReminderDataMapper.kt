package com.illiouchine.toothbrush.database

import com.illiouchine.toothbrush.database.dataobject.ReminderObject
import com.illiouchine.toothbrush.database.datasource.reminder.ReminderDataSource
import com.illiouchine.toothbrush.usecase.datagateway.ReminderDataGateway
import javax.inject.Inject

class ReminderDataMapper @Inject constructor(
    private val reminderDataSource: ReminderDataSource,
) : ReminderDataGateway {

    override suspend fun getReminder(dayPeriod: ReminderDataGateway.ReminderEntity.DayPeriod): ReminderDataGateway.ReminderEntity {
        return reminderDataSource.getReminder(dayPeriod = dayPeriod.toObjectDayPeriod())
            .toReminderEntity()
    }

    override suspend fun setReminder(reminder: ReminderDataGateway.ReminderEntity) {
        reminderDataSource.setReminder(reminder.toReminderObject())
    }
}

private fun ReminderDataGateway.ReminderEntity.toReminderObject(): ReminderObject {
    return ReminderObject(
        dayPeriod = this.dayPeriod.toObjectDayPeriod(),
        hour = this.hour,
        min = this.min,
        enabled = this.enabled
    )
}

private fun ReminderDataGateway.ReminderEntity.DayPeriod.toObjectDayPeriod(): ReminderObject.DayPeriod {
    return when (this){
        ReminderDataGateway.ReminderEntity.DayPeriod.Morning -> ReminderObject.DayPeriod.Morning
        ReminderDataGateway.ReminderEntity.DayPeriod.Midday -> ReminderObject.DayPeriod.Midday
        ReminderDataGateway.ReminderEntity.DayPeriod.Evening -> ReminderObject.DayPeriod.Evening
    }
}

private fun ReminderObject.toReminderEntity(): ReminderDataGateway.ReminderEntity {
    return ReminderDataGateway.ReminderEntity(
        dayPeriod = this.dayPeriod.toEntityDayPeriod(),
        hour = this.hour,
        min = this.min,
        enabled = this.enabled
    )
}

private fun ReminderObject.DayPeriod.toEntityDayPeriod(): ReminderDataGateway.ReminderEntity.DayPeriod {
    return when(this){
        ReminderObject.DayPeriod.Morning -> ReminderDataGateway.ReminderEntity.DayPeriod.Morning
        ReminderObject.DayPeriod.Midday -> ReminderDataGateway.ReminderEntity.DayPeriod.Midday
        ReminderObject.DayPeriod.Evening -> ReminderDataGateway.ReminderEntity.DayPeriod.Evening
    }
}
