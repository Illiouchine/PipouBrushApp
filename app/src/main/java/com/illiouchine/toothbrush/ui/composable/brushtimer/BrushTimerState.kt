package com.illiouchine.toothbrush.ui.composable.brushtimer

sealed class BrushTimerState{
    object Idle: BrushTimerState()
    object Finished: BrushTimerState()
    data class Running(
        val current: Long,
        val total: Long
    ) : BrushTimerState()
}