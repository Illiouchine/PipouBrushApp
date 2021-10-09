package com.illiouchine.toothbrush.feature.brushing.controller

import androidx.lifecycle.viewModelScope
import com.illiouchine.mvi.core.MviViewModel
import com.illiouchine.mvi.core.Reducer
import com.illiouchine.toothbrush.feature.brushing.usecase.LaunchVibratorUseCase
import com.illiouchine.toothbrush.feature.brushing.usecase.SaveBrushProgressUseCase
import com.illiouchine.toothbrush.feature.brushing.usecase.StartCountDownUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import com.illiouchine.toothbrush.feature.brushing.controller.BrushContract.BrushAction as Action
import com.illiouchine.toothbrush.feature.brushing.controller.BrushContract.BrushEvent as Event
import com.illiouchine.toothbrush.feature.brushing.controller.BrushContract.BrushIntent as Intent
import com.illiouchine.toothbrush.feature.brushing.controller.BrushContract.BrushPartialState as PartialState
import com.illiouchine.toothbrush.feature.brushing.controller.BrushContract.BrushState as State

@ExperimentalTime
@HiltViewModel
class BrushViewModel @Inject constructor(
    private val startCountDown: StartCountDownUseCase,
    private val launchVibrator: LaunchVibratorUseCase,
    private val saveBrushProgress: SaveBrushProgressUseCase
) : MviViewModel<Intent, Action, PartialState, State, Event>() {

    override fun createInitialState(): State =
        State(
            timerState = BrushContract.TimerState.Idle
        )

    override fun handleUserIntent(intent: Intent): Action {
        return when (intent) {
            Intent.LaunchTimer -> {
                Action.LaunchTimer
            }
            Intent.RestartTimer -> {
                Action.LaunchTimer
            }
        }
    }

    override suspend fun handleAction(action: Action) {
        when (action) {
            Action.LaunchTimer -> {
                launchTimer()
            }
        }
    }

    override fun createReducer(): Reducer<State, PartialState> {
        return object : Reducer<State, PartialState>() {
            override fun reduce(
                currentState: State,
                partialState: PartialState
            ): State {
                return when (partialState) {
                    is PartialState.TimerRunning -> {
                        currentState.copy(
                            timerState = BrushContract.TimerState.Running(
                                duration = partialState.duration,
                                totalDuration = partialState.totalDuration
                            )
                        )
                    }
                    PartialState.TimerFinished -> {
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
                        PartialState.TimerRunning(
                            duration = it.currentDuration,
                            totalDuration = it.totalDuration
                        )
                    }
                }
            setPartialState {
                PartialState.TimerFinished
            }
            launchVibrator()
            saveBrushProgress()
        }
    }
}