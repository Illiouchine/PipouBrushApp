package com.illiouchine.toothbrush.feature.brushing.controller

import androidx.lifecycle.viewModelScope
import com.illiouchine.toothbrush.core.mvi.MviViewModel
import com.illiouchine.toothbrush.core.mvi.Reducer
import com.illiouchine.toothbrush.feature.brushing.usecase.LaunchVibratorUseCase
import com.illiouchine.toothbrush.feature.brushing.usecase.SaveBrushProgressUseCase
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
    private val saveBrushProgress: SaveBrushProgressUseCase
) : MviViewModel<
        BrushContract.BrushIntent,
        BrushContract.BrushAction,
        BrushContract.BrushPartialState,
        BrushContract.BrushState,
        BrushContract.BrushEvent,
        >() {

    override fun createInitialState(): BrushContract.BrushState =
        BrushContract.BrushState(
            timerState = BrushContract.TimerState.Idle
        )

    override fun handleUserIntent(intent: BrushContract.BrushIntent): BrushContract.BrushAction {
        return when (intent) {
            BrushContract.BrushIntent.LaunchTimer -> {
                BrushContract.BrushAction.LaunchTimer
            }
            BrushContract.BrushIntent.RestartTimer -> {
                BrushContract.BrushAction.LaunchTimer
            }
        }
    }

    override suspend fun handleAction(action: BrushContract.BrushAction) {
        when (action) {
            BrushContract.BrushAction.LaunchTimer -> {
                launchTimer()
            }
        }
    }

    override fun createReducer(): Reducer<BrushContract.BrushState, BrushContract.BrushPartialState> {
        return object : Reducer<BrushContract.BrushState, BrushContract.BrushPartialState>() {
            override fun reduce(
                currentState: BrushContract.BrushState,
                partialState: BrushContract.BrushPartialState
            ): BrushContract.BrushState {
                return when (partialState) {
                    is BrushContract.BrushPartialState.TimerRunning -> {
                        currentState.copy(
                            timerState = BrushContract.TimerState.Running(
                                duration = partialState.duration,
                                totalDuration = partialState.totalDuration
                            )
                        )
                    }
                    BrushContract.BrushPartialState.TimerFinished -> {
                        currentState.copy(
                            timerState = BrushContract.TimerState.Finished
                        )
                    }
                }
            }
        }
    }

    private fun launchTimer() {
        viewModelScope.launch {
            startCountDown()
                .collect {
                    setPartialState {
                        BrushContract.BrushPartialState.TimerRunning(
                            duration = it.currentDuration,
                            totalDuration = it.totalDuration
                        )
                    }
                }
            setPartialState {
                BrushContract.BrushPartialState.TimerFinished
            }
            launchVibrator()
            saveBrushProgress()
        }
    }
}