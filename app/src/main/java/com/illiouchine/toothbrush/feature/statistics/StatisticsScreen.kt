package com.illiouchine.toothbrush.feature.statistics


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.composable.PipouBackground
import com.illiouchine.toothbrush.ui.composable.achievement.AchievementContent
import com.illiouchine.toothbrush.ui.composable.history.HistoryContent
import java.util.*
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsState.RawStatisticsState as StatisticsState

@Preview
@Composable
fun StatisticsScreen(
    statisticsState: StatisticsState = StatisticsState.Loaded(brushHistory = listOf(Date() to 1)),
) {
    PipouBackground(enableBlur = true){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(modifier = Modifier.fillMaxHeight(.5f)){
                AchievementContent()
            }
            Box(modifier = Modifier.fillMaxHeight(.5f)){
                HistoryContent(statisticsState)
            }
        }
    }
}