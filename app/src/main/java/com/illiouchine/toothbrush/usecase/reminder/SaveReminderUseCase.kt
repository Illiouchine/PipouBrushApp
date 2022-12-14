package com.illiouchine.toothbrush.usecase.reminder

import com.illiouchine.toothbrush.usecase.datagateway.ReminderDataGateway
import com.illiouchine.toothbrush.usecase.datagateway.entities.Reminder
import javax.inject.Inject

class SaveReminderUseCase @Inject constructor(
    private val reminderDataGateway: ReminderDataGateway
) {
    suspend operator fun invoke(reminder: Reminder) {
        reminderDataGateway.setReminder(reminder)
    }
}