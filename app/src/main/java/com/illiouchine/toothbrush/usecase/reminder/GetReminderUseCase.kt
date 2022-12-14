package com.illiouchine.toothbrush.usecase.reminder

import com.illiouchine.toothbrush.usecase.datagateway.ReminderDataGateway
import com.illiouchine.toothbrush.usecase.datagateway.entities.Reminder
import javax.inject.Inject

class GetReminderUseCase @Inject constructor(
    private val reminderDataSource: ReminderDataGateway
) {
    suspend operator fun invoke(dayPeriod: Reminder.DayPeriod): Reminder {
        return reminderDataSource.getReminder(dayPeriod)
    }
}