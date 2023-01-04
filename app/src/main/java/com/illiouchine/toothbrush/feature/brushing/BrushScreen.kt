package com.illiouchine.toothbrush.feature.brushing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.R
import com.illiouchine.toothbrush.ui.composable.PipouBackgroundV2
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        when (timerState) {
                            BrushContract.BrushState.Timer.Finished -> onRestartClick()
                            BrushContract.BrushState.Timer.Idle -> onStartClick()
                            is BrushContract.BrushState.Timer.Running -> {}
                        }
                    },
            ) {
                BrushTimer(
                    modifier = Modifier
                        .align(Alignment.Center),
                    timerState.toBrushTimerState(),
                )
            }
            if (achievements.isNotEmpty()) {
                AlertDialog(
                    onDismissRequest = {
                        onAchievementsHandled()
                    },
                    icon = {
                        Icon(
                            modifier = Modifier.size(50.dp),
                            painter = painterResource(id = R.drawable.ic_achievement_trophy),
                            contentDescription = null
                        )
                    },
                    title = {
                        Text(text = stringResource(R.string.brush_achievement_dialog_title))
                    },
                    text = {
                        LazyColumn{
                            items(achievements){ achievement ->
                                Row {
                                    Text(text = stringResource(id = achievement.nameResId))
                                }
                            }
                        }
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                onAchievementsHandled()
                            }
                        ) {
                            Text(text = stringResource(R.string.brush_achievement_dialog_confirm_button))
                        }
                    },
                    dismissButton = {}
                )

            }
        }
    )
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