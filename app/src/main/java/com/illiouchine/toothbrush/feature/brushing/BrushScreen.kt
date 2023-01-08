package com.illiouchine.toothbrush.feature.brushing

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.illiouchine.toothbrush.ui.composable.PipouBackgroundV2
import com.illiouchine.toothbrush.ui.composable.achievement.AchievementAlertDialog
import com.illiouchine.toothbrush.ui.composable.achievement.AlertDialogAchievement
import com.illiouchine.toothbrush.ui.composable.brushtimer.BrushTimer
import com.illiouchine.toothbrush.ui.composable.brushtimer.BrushTimerState

@Preview
@Composable
fun BrushScreen(
    timerState: BrushContract.BrushState.Timer = BrushContract.BrushState.Timer.Idle,
    achievements: List<BrushContract.Achievement> = emptyList(),
    onRestartClick: () -> Unit = {},
    onStartClick: () -> Unit = {},
    onAchievementsHandled: () -> Unit = {}
) {
    PipouBackgroundV2(
        mirrorContent = {
            BrushTimer(
                timerState = timerState.toBrushTimerState(),
                onRestartClick = { onRestartClick() },
                onStartClick = { onStartClick() },
                onRunningClick = { }
            )
            if (achievements.isNotEmpty()) {
                AchievementAlertDialog(
                    achievements = achievements.toAlertDialogAchievement(),
                    onAchievementsHandled = { onAchievementsHandled() }
                )
            }
        }
    )
}

private fun List<BrushContract.Achievement>.toAlertDialogAchievement(): List<AlertDialogAchievement> {
    return this.map {
        AlertDialogAchievement(
            nameResId = it.nameResId,
            descriptionResId = it.descriptionResId
        )
    }
}

fun BrushContract.BrushState.Timer.toBrushTimerState(): BrushTimerState {
    return when (this) {
        BrushContract.BrushState.Timer.Finished -> BrushTimerState.Finished
        BrushContract.BrushState.Timer.Idle -> BrushTimerState.Idle
        is BrushContract.BrushState.Timer.Running -> BrushTimerState.Running(
            current = current,
            total = total
        )
    }
}