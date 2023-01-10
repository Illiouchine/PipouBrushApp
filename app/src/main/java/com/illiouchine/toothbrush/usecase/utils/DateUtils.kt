package com.illiouchine.toothbrush.usecase.utils

import java.util.*

fun isSameDay(calendar: Calendar, anotherCalendar: Calendar): Boolean {
    return calendar.get(Calendar.DAY_OF_YEAR) == anotherCalendar.get(Calendar.DAY_OF_YEAR) &&
            calendar.get(Calendar.YEAR) == anotherCalendar.get(Calendar.YEAR)
}

fun <T : Date> List<T>.countThreeDayInRow(): Int {
    val calendarList: List<Calendar> = this.map {
        Calendar.getInstance().apply { time = it }
    }
    val calendarMutableList: MutableList<Calendar> = calendarList.toMutableList()

    var result = 0

    calendarList.map { currentDay ->
        val dayOfYear = currentDay.get(Calendar.DAY_OF_YEAR)
        val dayPlusOne: Calendar = Calendar.getInstance().apply { time = currentDay.time }
            .apply { set(Calendar.DAY_OF_YEAR, dayOfYear + 1) }
        val dayPlusTwo: Calendar = Calendar.getInstance().apply { time = currentDay.time }
            .apply { set(Calendar.DAY_OF_YEAR, dayOfYear + 2) }

        val findCurrentDay = calendarMutableList.any {
            isSameDay(it, currentDay)
        }
        val findDayPlusOne = calendarMutableList.any {
            isSameDay(it, dayPlusOne)
        }
        val findDayPlusTwo = calendarMutableList.any {
            isSameDay(it, dayPlusTwo)
        }

        if (findCurrentDay && findDayPlusOne && findDayPlusTwo) {
            result++
            val calendarElementsToRemove: MutableList<Calendar> = mutableListOf()
            calendarElementsToRemove.addAll(
                calendarMutableList.filter { isSameDay(it, currentDay) }
            )
            calendarElementsToRemove.addAll(
                calendarMutableList.filter { isSameDay(it, dayPlusOne) }
            )
            calendarElementsToRemove.addAll(
                calendarMutableList.filter { isSameDay(it, dayPlusTwo) }
            )
            calendarMutableList.removeAll(calendarElementsToRemove)
        }
    }
    return result
}

fun <T : Date> List<T>.groupByDayAndCountOccurrence(): List<Pair<T, Int>> {
    return this
        .map { date: T ->
            date to date.getYearAndDay()
        }.groupBy {
            it.second
        }.map {
            Pair(
                it.value.first().first,
                it.value.count()
            )
        }.toList()
}

fun Date.getYearAndDay(): Pair<Int, Int> {
    val dateCalendar = Calendar.getInstance().apply { time = this@getYearAndDay }
    return dateCalendar.get(Calendar.YEAR) to dateCalendar.get(Calendar.DAY_OF_YEAR)
}


fun <T : Date> List<T>.countTenDayInRow(): Int {
    val calendarList: List<Calendar> = this.map {
        Calendar.getInstance().apply { time = it }
    }
    val calendarMutableList: MutableList<Calendar> = calendarList.toMutableList()

    var result = 0

    calendarList.map { currentDay ->
        val dayOfYear = currentDay.get(Calendar.DAY_OF_YEAR)
        val dayPlusOne: Calendar = Calendar.getInstance().apply { time = currentDay.time }
            .apply { set(Calendar.DAY_OF_YEAR, dayOfYear + 1) }
        val dayPlusTwo: Calendar = Calendar.getInstance().apply { time = currentDay.time }
            .apply { set(Calendar.DAY_OF_YEAR, dayOfYear + 2) }
        val dayPlusThree: Calendar = Calendar.getInstance().apply { time = currentDay.time }
            .apply { set(Calendar.DAY_OF_YEAR, dayOfYear + 3) }
        val dayPlusFour: Calendar = Calendar.getInstance().apply { time = currentDay.time }
            .apply { set(Calendar.DAY_OF_YEAR, dayOfYear + 4) }
        val dayPlusFive: Calendar = Calendar.getInstance().apply { time = currentDay.time }
            .apply { set(Calendar.DAY_OF_YEAR, dayOfYear + 5) }
        val dayPlusSix: Calendar = Calendar.getInstance().apply { time = currentDay.time }
            .apply { set(Calendar.DAY_OF_YEAR, dayOfYear + 6) }
        val dayPlusSeven: Calendar = Calendar.getInstance().apply { time = currentDay.time }
            .apply { set(Calendar.DAY_OF_YEAR, dayOfYear + 7) }
        val dayPlusHeight: Calendar = Calendar.getInstance().apply { time = currentDay.time }
            .apply { set(Calendar.DAY_OF_YEAR, dayOfYear + 8) }
        val dayPlusNine: Calendar = Calendar.getInstance().apply { time = currentDay.time }
            .apply { set(Calendar.DAY_OF_YEAR, dayOfYear + 9) }
        val dayPlusTen: Calendar = Calendar.getInstance().apply { time = currentDay.time }
            .apply { set(Calendar.DAY_OF_YEAR, dayOfYear + 10) }


        val findCurrentDay = calendarMutableList.any { isSameDay(it, currentDay) }
        val findDayPlusOne = calendarMutableList.any { isSameDay(it, dayPlusOne) }
        val findDayPlusTwo = calendarMutableList.any { isSameDay(it, dayPlusTwo) }
        val findDayPlusThree = calendarMutableList.any { isSameDay(it, dayPlusThree) }
        val findDayPlusFour = calendarMutableList.any { isSameDay(it, dayPlusFour) }
        val findDayPlusFive = calendarMutableList.any { isSameDay(it, dayPlusFive) }
        val findDayPlusSix = calendarMutableList.any { isSameDay(it, dayPlusSix) }
        val findDayPlusSeven = calendarMutableList.any { isSameDay(it, dayPlusSeven) }
        val findDayPlusHeight = calendarMutableList.any { isSameDay(it, dayPlusHeight) }
        val findDayPlusNine = calendarMutableList.any { isSameDay(it, dayPlusNine) }
        val findDayPlusTen = calendarMutableList.any { isSameDay(it, dayPlusTen) }

        if (findCurrentDay &&
            findDayPlusOne &&
            findDayPlusTwo &&
            findDayPlusThree &&
            findDayPlusFour &&
            findDayPlusFive &&
            findDayPlusSix &&
            findDayPlusSeven &&
            findDayPlusHeight &&
            findDayPlusNine &&
            findDayPlusTen
        ) {
            result++
            val calendarElementsToRemove: MutableList<Calendar> = mutableListOf()
            calendarElementsToRemove.addAll(calendarMutableList.filter {
                isSameDay(
                    it,
                    currentDay
                )
            })
            calendarElementsToRemove.addAll(calendarMutableList.filter {
                isSameDay(
                    it,
                    dayPlusOne
                )
            })
            calendarElementsToRemove.addAll(calendarMutableList.filter {
                isSameDay(
                    it,
                    dayPlusTwo
                )
            })
            calendarElementsToRemove.addAll(calendarMutableList.filter {
                isSameDay(
                    it,
                    dayPlusThree
                )
            })
            calendarElementsToRemove.addAll(calendarMutableList.filter {
                isSameDay(
                    it,
                    dayPlusFour
                )
            })
            calendarElementsToRemove.addAll(calendarMutableList.filter {
                isSameDay(
                    it,
                    dayPlusFive
                )
            })
            calendarElementsToRemove.addAll(calendarMutableList.filter {
                isSameDay(
                    it,
                    dayPlusSix
                )
            })
            calendarElementsToRemove.addAll(calendarMutableList.filter {
                isSameDay(
                    it,
                    dayPlusSeven
                )
            })
            calendarElementsToRemove.addAll(calendarMutableList.filter {
                isSameDay(
                    it,
                    dayPlusHeight
                )
            })
            calendarElementsToRemove.addAll(calendarMutableList.filter {
                isSameDay(
                    it,
                    dayPlusNine
                )
            })
            calendarElementsToRemove.addAll(calendarMutableList.filter {
                isSameDay(
                    it,
                    dayPlusTen
                )
            })
            calendarMutableList.removeAll(calendarElementsToRemove)
        }
    }
    return result
}
