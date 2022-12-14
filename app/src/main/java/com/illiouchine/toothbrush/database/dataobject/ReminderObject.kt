package com.illiouchine.toothbrush.database.dataobject

data class ReminderObject(
    val dayPeriod: DayPeriod,
    val hour: Int,
    val min: Int,
    val enabled: Boolean
){
    enum class DayPeriod(val id: Int){
        Morning(1), Midday(2), Evening(3)
    }
}