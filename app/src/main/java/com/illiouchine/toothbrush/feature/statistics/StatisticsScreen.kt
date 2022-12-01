package com.illiouchine.toothbrush.feature.statistics


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.composable.PipouBackground
import com.illiouchine.toothbrush.ui.composable.achievement.AchievementContent
import com.illiouchine.toothbrush.ui.composable.achievement.AchievementState
import com.illiouchine.toothbrush.ui.composable.history.HistoryContent
import com.illiouchine.toothbrush.ui.composable.history.HistoryState
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsState.HistoryState as VMHistoryState
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsState.AchievementState as VMAchievementState

@Preview
@Composable
fun StatisticsScreen(
    historyState: VMHistoryState = VMHistoryState.Loaded(brushHistory = listOf()),
    achievementState: VMAchievementState = VMAchievementState.Loaded(achievements = listOf())
) {
    PipouBackground(enableBlur = true){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(modifier = Modifier.fillMaxHeight(.5f)){
                AchievementContent(achievementState = achievementState.toViewState())
            }
            Box(modifier = Modifier.fillMaxHeight(.5f)){
                HistoryContent(historyState.toViewState())
            }
        }
    }
}

private fun VMAchievementState.toViewState(): AchievementState {
    return when(this){
        VMAchievementState.Error -> {
            AchievementState.Error
        }
        is VMAchievementState.Loaded -> {
            AchievementState.Loaded(
                achievements = this.achievements.toViewModel()
            )
        }
        VMAchievementState.Loading -> {
            AchievementState.Loading
        }
    }
}

private fun List<StatisticsContract.Achievement>.toViewModel(): List<AchievementState.Achievement> {
    return this.map { vmAchievement ->
        AchievementState.Achievement(
            name = vmAchievement.name,
            description = vmAchievement.description,
            earned = vmAchievement.earned
        )
    }
}

private fun VMHistoryState.toViewState(): HistoryState {
    return when(this){
        VMHistoryState.Error -> { HistoryState.Error }
        is VMHistoryState.Loaded -> {
            HistoryState.Loaded(data = this.brushHistory.toViewData())
        }
        VMHistoryState.Loading -> {
            HistoryState.Loading
        }
    }
}

private fun List<StatisticsContract.History>.toViewData(): List<HistoryState.History> {
    return this.map {
        HistoryState.History(
            date = it.date,
            brushCount = it.brushCount
        )
    }
}