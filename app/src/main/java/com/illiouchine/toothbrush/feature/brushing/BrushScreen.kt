package com.illiouchine.toothbrush.feature.brushing

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
import com.illiouchine.toothbrush.feature.brushing.content.CountDownContent
import com.illiouchine.toothbrush.feature.brushing.content.RestartContent
import com.illiouchine.toothbrush.feature.brushing.content.WaitingContent
import com.illiouchine.toothbrush.ui.utils.assetsToBitmap
import com.illiouchine.toothbrush.feature.brushing.BrushContract.BrushIntent as UiIntent
import com.illiouchine.toothbrush.feature.brushing.BrushContract.BrushState as State

@ExperimentalMaterialApi
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
        when (brushState.timer) {
            State.Timer.Finished -> {
                RestartContent(
                    onRestartClick = {
                        viewModel.dispatchIntent(UiIntent.ResetBrushing)
                    },
                )
            }
            is State.Timer.Idle -> {
                WaitingContent(
                    onStartTimerClick = {
                        viewModel.dispatchIntent(UiIntent.StartBrushing)
                    }
                )
            }
            is State.Timer.Running -> {
                val runningState = (brushState.timer as State.Timer.Running)
                CountDownContent(
                    current = runningState.current,
                    total = runningState.total
                )
            }
        }
    }
}