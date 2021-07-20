package com.illiouchine.toothbrush.feature.brushing.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.illiouchine.toothbrush.feature.brushing.BrushViewModel
import com.illiouchine.toothbrush.feature.brushing.BrushContract
import com.illiouchine.toothbrush.feature.brushing.ui.composable.*
import kotlin.time.ExperimentalTime

@ExperimentalMaterialApi
@ExperimentalTime
@Composable
fun BrushScreen(
    viewModel: BrushViewModel
) {
    val brushState by viewModel.uiState.collectAsState()

    when (brushState.timerState) {
        BrushContract.TimerState.Finished -> {
            RestartContent(
                onRestartClick = {
                    viewModel.dispatchIntent(BrushContract.BrushIntent.RestartTimer)
                },
            )
        }
        BrushContract.TimerState.Idle -> {
            WaitingContent(
                onStartTimerClick = {
                    viewModel.dispatchIntent(BrushContract.BrushIntent.LaunchTimer)
                }
            )
        }
        is BrushContract.TimerState.Running -> {
            CountDownContent(
                duration = (brushState.timerState as BrushContract.TimerState.Running).duration
            )
        }
    }
}