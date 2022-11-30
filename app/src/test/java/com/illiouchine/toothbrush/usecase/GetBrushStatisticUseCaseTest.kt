package com.illiouchine.toothbrush.usecase

import com.illiouchine.toothbrush.usecase.datagateway.BrushHistoryDataGateway
import com.illiouchine.toothbrush.usecase.datagateway.BrushHistoryEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*

class GetBrushStatisticUseCaseTest{

    private val d10: Date = Calendar.getInstance().apply { set(1999, 12, 29, 12, 0,0) }.time
    private val d11: Date = Calendar.getInstance().apply { set(1999, 12, 29,15, 0,0) }.time
    private val d12: Date = Calendar.getInstance().apply { set(1999, 12, 29, 21, 0,0) }.time
    private val d20: Date = Calendar.getInstance().apply { set(1999, 12, 31, 12, 0,0) }.time
    private val d21: Date = Calendar.getInstance().apply { set(1999, 12, 31,22, 0,0) }.time
    private val d30: Date = Calendar.getInstance().apply { set(1999, 12, 12, 12, 0,0) }.time

    private val initalData = listOf<Date>(
        d10, d11, d12, d20, d21, d30
    )

    private val expectedData = listOf(
        d10 to 3,
        d20 to 2,
        d30 to 1
    )

    private val dataGateway: BrushHistoryDataGateway = mock()

    @Test
    fun test() = runBlocking{
        whenever(dataGateway.getBrushHistory())
            .thenReturn(BrushHistoryEntity(initalData))

        val getBrushStatistic = GetBrushStatisticUseCase(dataGateway)

        val result = getBrushStatistic()
        assertEquals(expectedData, result)
    }
}
