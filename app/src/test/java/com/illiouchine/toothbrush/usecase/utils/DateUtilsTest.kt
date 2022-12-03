package com.illiouchine.toothbrush.usecase.utils

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class DateUtilsTest {

    @Test
    fun testCount3DayInRow() {
        val d1: Date = Calendar.getInstance().apply {
            set(2021, 1, 1, 12, 0, 0)
        }.time
        val d2: Date = Calendar.getInstance().apply {
            set(2021, 1, 2, 15, 0, 0)
        }.time
        val d3: Date = Calendar.getInstance().apply {
            set(2021, 1, 3, 21, 0, 0)
        }.time
        val d4: Date = Calendar.getInstance().apply {
            set(2021, 1, 5, 12, 0, 0)
        }.time
        val d5: Date = Calendar.getInstance().apply {
            set(2021, 1, 6, 22, 0, 0)
        }.time
        val d6: Date = Calendar.getInstance().apply {
            set(2021, 1, 7, 12, 0, 0)
        }.time
        val d7: Date = Calendar.getInstance().apply {
            set(2021, 1, 8, 12, 0, 0)
        }.time
        val d8: Date = Calendar.getInstance().apply {
            set(2021, 1, 9, 15, 0, 0)
        }.time
        val d9: Date = Calendar.getInstance().apply {
            set(2021, 1, 11, 21, 0, 0)
        }.time
        val d10: Date = Calendar.getInstance().apply {
            set(2021, 1, 12, 12, 0, 0)
        }.time
        val d11: Date = Calendar.getInstance().apply {
            set(2021, 1, 14, 22, 0, 0)
        }.time
        val d12: Date = Calendar.getInstance().apply {
            set(2021, 1, 15, 12, 0, 0)
        }.time
        val d13: Date = Calendar.getInstance().apply {
            set(2021, 1, 16, 12, 0, 0)
        }.time
        val d14: Date = Calendar.getInstance().apply {
            set(2021, 1, 17, 22, 0, 0)
        }.time
        val d15: Date = Calendar.getInstance().apply {
            set(2021, 1, 18, 12, 0, 0)
        }.time
        val d16: Date = Calendar.getInstance().apply {
            set(2021, 1, 19, 12, 0, 0)
        }.time

        val d17: Date = Calendar.getInstance().apply {
            set(2021, 1, 1, 2, 0, 0)
        }.time
        val d18: Date = Calendar.getInstance().apply {
            set(2021, 1, 2, 3, 0, 0)
        }.time
        val d19: Date = Calendar.getInstance().apply {
            set(2021, 1, 3, 1, 0, 0)
        }.time
        val d20: Date = Calendar.getInstance().apply {
            set(2021, 1, 5, 2, 0, 0)
        }.time
        val d21: Date = Calendar.getInstance().apply {
            set(2021, 1, 6, 1, 0, 0)
        }.time
        val d22: Date = Calendar.getInstance().apply {
            set(2021, 1, 7, 3, 0, 0)
        }.time
        val d23: Date = Calendar.getInstance().apply {
            set(2021, 1, 8, 5, 0, 0)
        }.time
        val d24: Date = Calendar.getInstance().apply {
            set(2021, 1, 9, 4, 0, 0)
        }.time
        val d25: Date = Calendar.getInstance().apply {
            set(2021, 1, 11, 7, 0, 0)
        }.time
        val d26: Date = Calendar.getInstance().apply {
            set(2021, 1, 12, 6, 0, 0)
        }.time
        val d27: Date = Calendar.getInstance().apply {
            set(2021, 1, 14, 7, 0, 0)
        }.time
        val d28: Date = Calendar.getInstance().apply {
            set(2021, 1, 15, 8, 0, 0)
        }.time
        val d29: Date = Calendar.getInstance().apply {
            set(2021, 1, 16, 9, 0, 0)
        }.time
        val d30: Date = Calendar.getInstance().apply {
            set(2021, 1, 17, 7, 0, 0)
        }.time
        val d31: Date = Calendar.getInstance().apply {
            set(2021, 1, 18, 8, 0, 0)
        }.time
        val d32: Date = Calendar.getInstance().apply {
            set(2021, 1, 19, 9, 0, 0)
        }.time

        val initialData: List<Date> = listOf<Date>(
            d1, d2, d3, d4, d5, d6, d7, d8, d9, d10,
            d11, d12, d13, d14, d15, d16, d17, d18, d19, d20,
            d21, d22, d23, d24, d25, d26, d27, d28, d29, d30,
            d31, d32,
        )
        val result = initialData.countThreeDayInRow()
        assertEquals(4, result)
    }

    @Test
    fun testIsDameDay() {
        val result = isSameDay(
            calendar = Calendar.getInstance().apply { set(2021, 1, 1, 12, 0, 0) },
            anotherCalendar = Calendar.getInstance().apply { set(2021, 1, 2, 12, 0, 0) }
        )
        assertEquals(false, result)

        val result2 = isSameDay(
            calendar = Calendar.getInstance().apply { set(2021, 1, 1, 23, 59, 59) },
            anotherCalendar = Calendar.getInstance().apply { set(2021, 1, 2, 0, 0, 0) }
        )
        assertEquals(false, result2)
        val result3 = isSameDay(
            calendar = Calendar.getInstance().apply { set(2021, 1, 1, 23, 59, 59) },
            anotherCalendar = Calendar.getInstance().apply { set(2021, 1, 1, 0, 0, 0) }
        )
        assertEquals(true, result3)
    }

    @Test
    fun testGroupByDayAndCountOccurrence() {
        val d10: Date = Calendar.getInstance().apply { set(1999, 12, 29, 12, 0, 0) }.time
        val d11: Date = Calendar.getInstance().apply { set(1999, 12, 29, 15, 0, 0) }.time
        val d12: Date = Calendar.getInstance().apply { set(1999, 12, 29, 21, 0, 0) }.time
        val d20: Date = Calendar.getInstance().apply { set(1999, 12, 31, 12, 0, 0) }.time
        val d21: Date = Calendar.getInstance().apply { set(1999, 12, 31, 22, 0, 0) }.time
        val d30: Date = Calendar.getInstance().apply { set(1999, 12, 12, 12, 0, 0) }.time

        val initialData = listOf<Date>(
            d10, d11, d12, d20, d21, d30
        )

        val expectedData = listOf(
            Pair(d10, 3),
            Pair(d20, 2),
            Pair(d30, 1),
        )

        val result = initialData.groupByDayAndCountOccurrence()
        assertEquals(expectedData, result)
    }
}