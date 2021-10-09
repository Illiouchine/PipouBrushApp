package com.illiouchine.toothbrush.feature.brushing.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import com.illiouchine.toothbrush.feature.brushing.controller.BrushContract.BrushIntent
import com.illiouchine.toothbrush.feature.brushing.controller.BrushContract.TimerState
import com.illiouchine.toothbrush.feature.brushing.controller.BrushViewModel
import com.illiouchine.toothbrush.feature.brushing.ui.composable.CountDownContent
import com.illiouchine.toothbrush.feature.brushing.ui.composable.RestartContent
import com.illiouchine.toothbrush.feature.brushing.ui.composable.WaitingContent
import com.illiouchine.toothbrush.ui.utils.assetsToBitmap
import kotlin.time.ExperimentalTime

@ExperimentalMaterialApi
@ExperimentalTime
@Composable
fun BrushScreen(
    viewModel: BrushViewModel
) {
    val brushState by viewModel.uiState.collectAsState()

    val context = LocalContext.current
    val bitmap: ImageBitmap? = remember {
        context
            .assetsToBitmap("brossage_dents_redimension.jpg")
            ?.asImageBitmap()
    }

    Surface {
        bitmap?.let {
            Image(
                modifier = Modifier.fillMaxSize(),
                bitmap = it,
                contentDescription = "BG"
            )
        }
        when (brushState.timerState) {
            TimerState.Finished -> {
                RestartContent(
                    onRestartClick = {
                        viewModel.dispatchIntent(BrushIntent.RestartTimer)
                    },
                )
            }
            TimerState.Idle -> {
                WaitingContent(
                    onStartTimerClick = {
                        viewModel.dispatchIntent(BrushIntent.LaunchTimer)
                    }
                )
            }
            is TimerState.Running -> {
                CountDownContent(
                    duration = (brushState.timerState as TimerState.Running).duration,
                    totalDuration = (brushState.timerState as TimerState.Running).totalDuration
                )
            }
        }
    }

}