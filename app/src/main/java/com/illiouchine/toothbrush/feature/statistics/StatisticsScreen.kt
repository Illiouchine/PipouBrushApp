package com.illiouchine.toothbrush.feature.statistics


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsState.StatisticsState
import com.illiouchine.toothbrush.ui.composable.PipouBackground
import com.illiouchine.toothbrush.ui.composable.history.HistoryContent
import com.illiouchine.toothbrush.ui.composable.history.HistoryState

@Preview
@Composable
fun StatisticsScreen(
    statisticsState: StatisticsState = StatisticsState.Loaded(brushHistory = listOf()),
) {
    PipouBackground(blurOrAlphaBackground = true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            HistoryContent(
                modifier = Modifier,
                historyState = statisticsState.toViewState()
            )
        }
    }
}

private fun StatisticsState.toViewState(): HistoryState {
    return when (this) {
        StatisticsState.Error -> {
            HistoryState.Error
        }
        is StatisticsState.Loaded -> {
            HistoryState.Loaded(data = this.brushHistory.toViewData())
        }
        StatisticsState.Loading -> {
            HistoryState.Loading
        }
    }
}

private fun List<StatisticsContract.Statistics>.toViewData(): List<HistoryState.History> {
    return this.map {
        HistoryState.History(
            date = it.date,
            brushCount = it.brushCount
        )
    }
}