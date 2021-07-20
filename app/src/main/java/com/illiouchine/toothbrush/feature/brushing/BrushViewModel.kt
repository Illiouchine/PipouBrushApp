package com.illiouchine.toothbrush.feature.brushing

import androidx.lifecycle.viewModelScope
import com.illiouchine.toothbrush.core.mvi.MviViewModel
import com.illiouchine.toothbrush.core.mvi.Reducer
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
                BrushContract.BrushAction.launchTimer
            }
            BrushContract.BrushIntent.RestartTimer -> {
                BrushContract.BrushAction.launchTimer
            }
        }
    }

    override suspend fun handleAction(action: BrushContract.BrushAction) {
        when (action) {
            BrushContract.BrushAction.launchTimer -> {
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
                                duration = partialState.duration
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
            startCountDown(initialDuration = brushDuration)
                .collect {
                    setPartialState {
                        BrushContract.BrushPartialState.TimerRunning(it)
                    }
                }
            setPartialState {
                BrushContract.BrushPartialState.TimerFinished
            }
            launchVibrator()
        }
    }
}