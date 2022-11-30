package com.illiouchine.toothbrush.feature.statistics


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.illiouchine.toothbrush.ui.composable.PipouBackground
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsState.RawStatisticsState as StatisticsState
import com.illiouchine.toothbrush.ui.composable.achievement.BrushHistoryContent

@Preview
@Composable
fun StatisticsScreen(
    statisticsState: StatisticsState = StatisticsState.Loaded(data = listOf("One", "Two"))
) {

    PipouBackground(enableBlur = true){
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Achievement")

            when (statisticsState) {
                StatisticsState.Error -> {
                    StatisticsContent("error")
                }
                is StatisticsState.Loaded -> {
                    BrushHistoryContent(
                        brushHistory = statisticsState.data
                    )
                }
                StatisticsState.Loading -> {
                    StatisticsContent("loading")
                }
            }
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
