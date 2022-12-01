package com.illiouchine.toothbrush.ui.composable.history

import java.util.*

sealed class HistoryState {
    object Loading : HistoryState()
    object Error : HistoryState()
    data class Loaded(
        val data: List<Pair<Date, Int>>
    ) : HistoryState()
}