package com.illiouchine.toothbrush.ui.composable.history

import java.util.*

sealed class HistoryState {
    object Loading : HistoryState()
    object Error : HistoryState()
    data class Loaded(
        val data: List<History>
    ) : HistoryState()

    data class History(
        val date: Date,
        val brushCount: Int
    )
}

fun previewDataHistoryList(): List<HistoryState.History> {
    return listOf(
        HistoryState.History(date = d10, brushCount = 3),
        HistoryState.History(date = d20, brushCount = 1),
        HistoryState.History(date = d30, brushCount = 2),
    )
}

fun previewDataHistory(
    date: Date = d10,
    brushCount: Int = 1
): HistoryState.History {
    return HistoryState.History(
        date = date,
        brushCount = brushCount,
    )
}

private val d10: Date = Calendar.getInstance().apply { set(1999, 12, 29, 12, 0,0) }.time
private val d20: Date = Calendar.getInstance().apply { set(1999, 12, 31, 12, 0,0) }.time
private val d30: Date = Calendar.getInstance().apply { set(1999, 12, 12, 12, 0,0) }.time
