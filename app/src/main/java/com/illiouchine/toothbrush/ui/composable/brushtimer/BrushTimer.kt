package com.illiouchine.toothbrush.ui.composable.brushtimer

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@ExperimentalMaterialApi
@Composable
fun BrushTimer(
    modifier: Modifier = Modifier,
    timerState: BrushTimerState = BrushTimerState.Idle,
) {
    Box(modifier = modifier){
        when (timerState) {
            BrushTimerState.Idle -> {
                IdleTimer()
            }
            is BrushTimerState.Running -> {
                RunningTimer(
                    current = timerState.current,
                    total = timerState.total
                )
            }
            BrushTimerState.Finished -> {
                FinishedTimer()
            }
        }
    }
}


@Preview
@ExperimentalMaterialApi
@Composable
fun BrushTimerIdle(){
    BrushTimer(
        timerState = BrushTimerState.Idle
    )
}

@Preview
@ExperimentalMaterialApi
@Composable
fun BrushTimerRunning(){
    BrushTimer(
        timerState = BrushTimerState.Running(
            current = 20,
            total = 180
        ),
    )
}

@Preview
@ExperimentalMaterialApi
@Composable
fun BrushTimerFinished(){
    BrushTimer(
        timerState = BrushTimerState.Finished
    )
}