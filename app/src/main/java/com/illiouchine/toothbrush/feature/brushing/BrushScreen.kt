package com.illiouchine.toothbrush.feature.brushing

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.composable.brushtimer.BrushTimerState
import com.illiouchine.toothbrush.ui.composable.brushtimer.BrushTimer
import com.illiouchine.toothbrush.ui.utils.assetsToBitmap

@Preview
@ExperimentalMaterialApi
@Composable
fun BrushScreen(
    timerState: BrushContract.BrushState.Timer = BrushContract.BrushState.Timer.Idle,
    onRestartClick : () -> Unit = {},
    onStartClick : () -> Unit = {},
) {

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    when (timerState){
                        BrushContract.BrushState.Timer.Finished -> onRestartClick()
                        BrushContract.BrushState.Timer.Idle -> onStartClick()
                        is BrushContract.BrushState.Timer.Running -> {}
                    }
                },
            ){
            BrushTimer(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(x = 0.dp, y= (-170).dp),
                timerState.toBrushTimerState(),
            )
        }
    }
}

fun BrushContract.BrushState.Timer.toBrushTimerState(): BrushTimerState {
    return when (this){
        BrushContract.BrushState.Timer.Finished -> BrushTimerState.Finished
        BrushContract.BrushState.Timer.Idle -> BrushTimerState.Idle
        is BrushContract.BrushState.Timer.Running -> BrushTimerState.Running(
            current = current,
            total = total
        )
    }
}