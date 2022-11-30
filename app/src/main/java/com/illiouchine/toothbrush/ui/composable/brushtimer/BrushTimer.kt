package com.illiouchine.toothbrush.ui.composable.brushtimer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun BrushTimer(
    modifier: Modifier = Modifier,
    timerState: BrushTimerState = BrushTimerState.Idle,
) {
    Box(
        modifier = modifier.width(150.dp)
            .wrapContentHeight()
    ){
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
@Composable
fun BrushTimerIdle(){
    BrushTimer(
        timerState = BrushTimerState.Idle
    )
}

@Preview
@Composable
fun BrushTimerRunning20(){
    BrushTimer(
        timerState = BrushTimerState.Running(
            current = 20,
            total = 180
        ),
    )
}

@Preview
@Composable
fun BrushTimerRunning60(){
    BrushTimer(
        timerState = BrushTimerState.Running(
            current = 60,
            total = 180
        ),
    )
}

@Preview
@Composable
fun BrushTimerRunning120(){
    BrushTimer(
        timerState = BrushTimerState.Running(
            current = 120,
            total = 180
        ),
    )
}

@Preview
@Composable
fun BrushTimerFinished(){
    BrushTimer(
        timerState = BrushTimerState.Finished
    )
}