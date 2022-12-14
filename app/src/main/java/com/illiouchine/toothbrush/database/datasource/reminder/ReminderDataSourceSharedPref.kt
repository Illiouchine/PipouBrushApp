package com.illiouchine.toothbrush.database.datasource.reminder

import android.content.Context
import com.illiouchine.toothbrush.database.dataobject.ReminderObject
import javax.inject.Inject

class ReminderDataSourceSharedPref @Inject constructor(
    context: Context
) : ReminderDataSource {

    private val preferencesFileKey = "ReminderPreferencesFileKey"

    private val getPref = context.getSharedPreferences(preferencesFileKey, Context.MODE_PRIVATE)

    private val morningHourKey = "morningHourKey"
    private val morningMinKey = "morningMinKey"
    private val morningEnabledKey = "morningEnabledKey"

    private val middayHourKey = "middayHourKey"
    private val middayMinKey = "middayMinKey"
    private val middayEnabledKey = "middayEnabledKey"

    private val eveningHourKey = "eveningHourKey"
    private val eveningMinKey = "eveningMinKey"
    private val eveningEnabledKey = "eveningEnabledKey"

    override suspend fun getReminder(dayPeriod: ReminderObject.DayPeriod): ReminderObject {
        return when(dayPeriod){
            ReminderObject.DayPeriod.Morning -> getMorningReminder()
            ReminderObject.DayPeriod.Midday -> getMiddayReminder()
            ReminderObject.DayPeriod.Evening -> getEveningReminder()
        }
    }

    override suspend fun setReminder(reminderObject: ReminderObject) {
        when(reminderObject.dayPeriod){
            ReminderObject.DayPeriod.Morning -> setMorningReminder(reminderObject)
            ReminderObject.DayPeriod.Midday -> setMiddayReminder(reminderObject)
            ReminderObject.DayPeriod.Evening -> setEveningReminder(reminderObject)
        }
    }

    private fun getMorningReminder(): ReminderObject {
        val hour = getPref
            .getInt(morningHourKey, 8)
        val min = getPref
            .getInt(morningMinKey, 0)
        val enabled = getPref
            .getBoolean(morningEnabledKey, false)
        return ReminderObject(
            dayPeriod = ReminderObject.DayPeriod.Morning,
            hour = hour,
            min = min,
            enabled = enabled
        )
    }

    private fun getMiddayReminder(): ReminderObject {
        val hour = getPref
            .getInt(middayHourKey, 12)
        val min = getPref
            .getInt(middayMinKey, 0)
        val enabled = getPref
            .getBoolean(middayEnabledKey, false)
        return ReminderObject(
            dayPeriod = ReminderObject.DayPeriod.Midday,
            hour = hour,
            min = min,
            enabled = enabled
        )
    }

    private fun getEveningReminder(): ReminderObject {
        val hour = getPref
            .getInt(eveningHourKey, 18)
        val min = getPref
            .getInt(eveningMinKey, 0)
        val enabled = getPref
            .getBoolean(eveningEnabledKey, false)
        return ReminderObject(
            dayPeriod = ReminderObject.DayPeriod.Evening,
            hour = hour,
            min = min,
            enabled = enabled
        )
    }

    private fun setMorningReminder(reminderObject: ReminderObject) {
        getPref.edit().apply {
            putInt(morningHourKey, reminderObject.hour)
            putInt(morningMinKey, reminderObject.min)
            putBoolean(morningEnabledKey, reminderObject.enabled)
            apply()
        }
    }

    private fun setMiddayReminder(reminderObject: ReminderObject) {
        getPref.edit().apply {
            putInt(middayHourKey, reminderObject.hour)
            putInt(middayMinKey, reminderObject.min)
            putBoolean(middayEnabledKey, reminderObject.enabled)
            apply()
        }
    }

    private fun setEveningReminder(reminderObject: ReminderObject) {
        getPref.edit().apply {
            putInt(eveningHourKey, reminderObject.hour)
            putInt(eveningMinKey, reminderObject.min)
            putBoolean(eveningEnabledKey, reminderObject.enabled)
            apply()
        }
    }
}