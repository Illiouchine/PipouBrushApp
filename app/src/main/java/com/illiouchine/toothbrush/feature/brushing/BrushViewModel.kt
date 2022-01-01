package com.illiouchine.toothbrush.feature.brushing

import androidx.lifecycle.viewModelScope
import com.illiouchine.mvi.core.MviViewModel
import com.illiouchine.mvi.core.Reducer
import com.illiouchine.toothbrush.usecase.LaunchVibratorUseCase
import com.illiouchine.toothbrush.usecase.SaveBrushProgressUseCase
import com.illiouchine.toothbrush.usecase.StartCountDownUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.illiouchine.toothbrush.feature.brushing.BrushContract.BrushAction as Action
import com.illiouchine.toothbrush.feature.brushing.BrushContract.BrushEvent as Event
import com.illiouchine.toothbrush.feature.brushing.BrushContract.BrushIntent as Intent
import com.illiouchine.toothbrush.feature.brushing.BrushContract.BrushPartialState as PartialState
import com.illiouchine.toothbrush.feature.brushing.BrushContract.BrushState as State

@HiltViewModel
class BrushViewModel @Inject constructor(
    private val startCountDown: StartCountDownUseCase,
    private val launchVibrator: LaunchVibratorUseCase,
    private val saveBrushProgress: SaveBrushProgressUseCase
) : MviViewModel<Intent, Action, PartialState, State, Event>() {

    private val brushDuration: Long = 260L

    override fun createInitialState(): State =
        State(
            timer = State.Timer.Idle(
                current = 0L, total = brushDuration
            )
        )

    override fun handleUserIntent(intent: Intent): Action {
        return when (intent) {
            Intent.StartBrushing,
            Intent.ResumeBrushing -> Action.StartTimer
            Intent.PauseBrushing -> Action.PauseTimer
            Intent.ResetBrushing -> Action.ResetTimer
        }
    }

    override suspend fun handleAction(action: Action) {
        when (action) {
            Action.PauseTimer -> pauseTimer()
            Action.ResetTimer -> resetTimer()
            Action.StartTimer -> startTimer()
            Action.ResumeTimer -> resumeTimer()
            Action.FinishTimer -> resetTimer()
        }
    }

    private fun resumeTimer() {
        TODO()
    }

    private fun startTimer() {
        launchTimer()
    }

    private fun resetTimer() {
        setPartialState {
            PartialState.TimerIdle(
                current = brushDuration,
                total = brushDuration
            )
        }
    }

    private fun pauseTimer() {
        TODO()
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
                            timer = State.Timer.Running(
                                current = partialState.current,
                                total = partialState.total
                            )
                        )
                    }
                    PartialState.TimerFinished -> {
                        currentState.copy(
                            timer = State.Timer.Finished
                        )
                    }
                    is BrushContract.BrushPartialState.TimerPaused -> {
                        currentState.copy(
                            timer = State.Timer.Idle(
                                current = partialState.current,
                                total = partialState.total
                            )
                        )
                    }
                    is BrushContract.BrushPartialState.TimerIdle -> {
                        currentState.copy(
                            timer = State.Timer.Idle(
                                current = partialState.current,
                                total = partialState.total
                            )
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
                    if (it.currentDuration <= 0){
                        setPartialState {
                            PartialState.TimerFinished
                        }
                        launchVibrator()
                        saveBrushProgress()
                    } else {
                        setPartialState {
                            PartialState.TimerRunning(
                                current = it.currentDuration,
                                total = it.totalDuration
                            )
                        }
                    }
                }
        }
    }
}