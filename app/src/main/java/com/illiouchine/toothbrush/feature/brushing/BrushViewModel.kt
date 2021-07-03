package com.illiouchine.toothbrush.feature.brushing

import androidx.lifecycle.viewModelScope
import com.illiouchine.toothbrush.core.mvi.MVIViewModel
import com.illiouchine.toothbrush.feature.brushing.usecase.LaunchVibratorUseCase
import com.illiouchine.toothbrush.feature.brushing.usecase.StartCountDownUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@ExperimentalTime
@HiltViewModel
class BrushViewModel @Inject constructor(
    private val startCountDown: StartCountDownUseCase,
    private val launchVibrator: LaunchVibratorUseCase,
) : MVIViewModel<BrushContract.BrushIntent, BrushContract.BrushState, BrushContract.BrushEvent>()
{
    override fun createInitialState(): BrushContract.BrushState = BrushContract.BrushState(
        timerState = BrushContract.TimerState.Idle
    )

    override fun handleUserIntent(event: BrushContract.BrushIntent) {
        when(event){
            BrushContract.BrushIntent.LaunchTimer -> { launchTimer() }
            BrushContract.BrushIntent.RestartTimer -> { restart() }
        }
    }

    private fun launchTimer() {
        viewModelScope.launch {
            startCountDown(initialDuration = countDownDuration)
                .collect {
                    setState { copy(timerState = BrushContract.TimerState.CountDown(it)) }
                }
            startCountDown(initialDuration = brushDuration)
                .collect {
                    setState { copy(timerState = BrushContract.TimerState.Running(it)) }
                }
            setState { copy(timerState = BrushContract.TimerState.Finished) }
            launchVibrator()
        }
    }

    private fun restart() {
        setState { copy(timerState = BrushContract.TimerState.Idle) }
    }
}