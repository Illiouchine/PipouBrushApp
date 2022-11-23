package com.illiouchine.toothbrush.ui.composable.chrono

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.illiouchine.toothbrush.feature.brushing.BrushContract
import com.illiouchine.toothbrush.feature.brushing.content.CountDownContent
import com.illiouchine.toothbrush.feature.brushing.content.RestartContent
import com.illiouchine.toothbrush.feature.brushing.content.WaitingContent


@ExperimentalMaterialApi
@Composable
fun brushTimer(
    modifier: Modifier = Modifier,
    timerState: BrushContract.BrushState.Timer = BrushContract.BrushState.Timer.Finished,
    onRestartClick : () -> Unit = {},
    onStartClick : () -> Unit = {},
) {
    when (timerState) {
        BrushContract.BrushState.Timer.Finished -> {
            RestartContent(
                onRestartClick = onRestartClick,
            )
        }
        is BrushContract.BrushState.Timer.Idle -> {
            WaitingContent(
                onStartTimerClick = onStartClick
            )
        }
        is BrushContract.BrushState.Timer.Running -> {
            CountDownContent(
                current = timerState.current,
                total = timerState.total
            )
        }
    }
}