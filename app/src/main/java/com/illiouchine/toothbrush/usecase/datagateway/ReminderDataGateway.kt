package com.illiouchine.toothbrush.usecase.datagateway

interface ReminderDataGateway {

    suspend fun getReminder(dayPeriod: ReminderEntity.DayPeriod): ReminderEntity
    suspend fun setReminder(reminder: ReminderEntity)

    data class ReminderEntity(
        val dayPeriod: DayPeriod,
        val hour: Int,
        val min: Int,
        val enabled: Boolean
    ){
        enum class DayPeriod(val id: Int){
            Morning(1), Midday(2), Evening(3)
        }
    }
}