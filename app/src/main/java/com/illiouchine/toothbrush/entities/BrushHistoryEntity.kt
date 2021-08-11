package com.illiouchine.toothbrush.entities

import java.util.*

data class BrushHistoryEntity(
    val date: Date,
    val dayPeriod: DayPeriod,
){
    sealed class DayPeriod{
        object Morning: DayPeriod()
        object MidDay: DayPeriod()
        object Evening: DayPeriod()
    }
}