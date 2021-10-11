package com.illiouchine.toothbrush.feature.statistics.ui


import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.RawStatisticsState
import com.illiouchine.toothbrush.feature.statistics.StatisticsViewModel
import com.illiouchine.toothbrush.feature.statistics.ui.composable.BrushHistoryContent

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel
) {
    val statisticsState by viewModel.uiState.collectAsState()

    when (statisticsState.rawStatisticsState) {
        RawStatisticsState.Error -> {
            StatisticsContent("error")
        }
        is RawStatisticsState.Loaded -> {
            BrushHistoryContent(
                brushHistory = (statisticsState.rawStatisticsState as RawStatisticsState.Loaded).data
            )
        }
        RawStatisticsState.Loading -> {
            StatisticsContent("loading")
        }
    }
}

@Composable
fun StatisticsContent(
    text: String
) {
    Surface {
        Text(
            text = text,
            fontSize = 32.sp
        )
    }
}
