package com.illiouchine.toothbrush.usecase

import com.illiouchine.toothbrush.usecase.datagateway.StatisticsDataGateway
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*

class GetStatisticsUseCaseTest {

    private val d10: Date = Calendar.getInstance().apply { set(1999, 12, 29, 12, 0, 0) }.time
    private val d11: Date = Calendar.getInstance().apply { set(1999, 12, 29, 15, 0, 0) }.time
    private val d12: Date = Calendar.getInstance().apply { set(1999, 12, 29, 21, 0, 0) }.time
    private val d20: Date = Calendar.getInstance().apply { set(1999, 12, 31, 12, 0, 0) }.time
    private val d21: Date = Calendar.getInstance().apply { set(1999, 12, 31, 22, 0, 0) }.time
    private val d30: Date = Calendar.getInstance().apply { set(1999, 12, 12, 12, 0, 0) }.time

    private val initialData = listOf<Date>(
        d10, d11, d12, d20, d21, d30
    )

    private val expectedData = listOf(
        GetStatisticsUseCase.Statistics(d10, 3),
        GetStatisticsUseCase.Statistics(d20, 2),
        GetStatisticsUseCase.Statistics(d30, 1),
    )

    private val dataGateway: StatisticsDataGateway = mock()

    @Test
    fun test() = runBlocking {
        whenever(dataGateway.getStatistics())
            .thenReturn(StatisticsDataGateway.StatisticsEntity(initialData))

        val getBrushStatistic = GetStatisticsUseCase(dataGateway)

        val result = getBrushStatistic()
        assertEquals(expectedData, result)
    }
}
