package com.illiouchine.toothbrush.feature.achievements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.composable.PipouBackground
import com.illiouchine.toothbrush.ui.composable.achievement.AchievementContent
import com.illiouchine.toothbrush.ui.composable.achievement.AchievementState


@Preview
@Composable
fun AchievementsScreen(
    achievementState: AchievementsContract.AchievementsState.AchievementState = AchievementsContract.AchievementsState.AchievementState.Loaded(
        achievements = listOf()
    )
) {
    PipouBackground(blurOrAlphaBackground = true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AchievementContent(
                modifier = Modifier,
                achievementState = achievementState.toViewState()
            )
        }
    }
}

@Composable // I don't know if this is a code smell ?
private fun AchievementsContract.AchievementsState.AchievementState.toViewState(): AchievementState {
    return when (this) {
        AchievementsContract.AchievementsState.AchievementState.Error -> {
            AchievementState.Error
        }
        is AchievementsContract.AchievementsState.AchievementState.Loaded -> {
            AchievementState.Loaded(
                achievements = this.achievements.toViewModel()
            )
        }
        AchievementsContract.AchievementsState.AchievementState.Loading -> {
            AchievementState.Loading
        }
    }
}

@Composable
private fun List<AchievementsContract.Achievement>.toViewModel(): List<AchievementState.Achievement> {
    return this.map { vmAchievement ->
        AchievementState.Achievement(
            name = stringResource(id = vmAchievement.nameResId),
            description = stringResource(vmAchievement.descriptionResId),
            earned = vmAchievement.earned
        )
    }
}