package com.illiouchine.toothbrush.feature.statistics.ui


import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import com.illiouchine.toothbrush.feature.statistics.controller.StatisticsContract
import com.illiouchine.toothbrush.feature.statistics.controller.StatisticsViewModel
import com.illiouchine.toothbrush.feature.statistics.ui.composable.BrushHistoryContent

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel
) {
    val statisticsState by viewModel.uiState.collectAsState()

    when (statisticsState.rawStatisticsState) {
        StatisticsContract.RawStatisticsState.Error -> {
            StatisticsContent("error")
        }
        is StatisticsContract.RawStatisticsState.Loaded -> {
            BrushHistoryContent(
                brushHistory = (statisticsState.rawStatisticsState as StatisticsContract.RawStatisticsState.Loaded).data
            )
        }
        StatisticsContract.RawStatisticsState.Loading -> {
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
