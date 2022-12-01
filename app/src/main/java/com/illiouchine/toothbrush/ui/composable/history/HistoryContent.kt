package com.illiouchine.toothbrush.ui.composable.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract
import java.util.*

@Composable
fun HistoryContent(
    statisticsState: StatisticsContract.StatisticsState.RawStatisticsState = StatisticsContract.StatisticsState.RawStatisticsState.Loaded(
        brushHistory = listOf(Date() to 1)
    ),
    ) {
    Column {
        Text(
            text = "Historique",
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(2.dp))

        when (statisticsState) {
            StatisticsContract.StatisticsState.RawStatisticsState.Error -> {
                Text("error")
            }
            is StatisticsContract.StatisticsState.RawStatisticsState.Loaded -> {
                HistoryRow(
                    brushHistory = statisticsState.brushHistory
                )
            }
            StatisticsContract.StatisticsState.RawStatisticsState.Loading -> {
                Text("loading")
            }
        }
    }
}