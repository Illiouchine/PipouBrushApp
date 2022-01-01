package com.illiouchine.toothbrush.feature.brushing

import androidx.lifecycle.viewModelScope
import com.illiouchine.mvi.core.MviViewModel
import com.illiouchine.mvi.core.Reducer
import com.illiouchine.toothbrush.usecase.LaunchVibratorUseCase
import com.illiouchine.toothbrush.usecase.SaveBrushProgressUseCase
import com.illiouchine.toothbrush.usecase.StartCountDownUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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
    private var timerJob: Job? = null

    override fun createInitialState(): State =
        State(
            timer = State.Timer.Idle(
                current = 0L, total = brushDuration
            )
        )

    override fun handleUserIntent(intent: Intent): Action {
        return when (intent) {
            Intent.StartBrushing -> Action.StartTimer
            is Intent.ResumeBrushing -> Action.ResumeTimer(
                intent.currentDuration, intent.totalDuration
            )
            Intent.PauseBrushing -> Action.PauseTimer
            Intent.ResetBrushing -> Action.ResetTimer
        }
    }

    override suspend fun handleAction(action: Action) {
        when (action) {
            Action.PauseTimer -> {
                timerJob?.cancel()
                setPartialState {
                    PartialState.TimerPaused
                }
            }
            Action.ResetTimer -> resetTimer()
            Action.StartTimer -> {
                timerJob?.cancel()
                launchTimer()
            }
            is Action.ResumeTimer -> {
                timerJob?.cancel()
                launchTimer(action.currentDuration, action.totalDuration)
            }
            Action.FinishTimer -> resetTimer()
        }
    }

    private fun resetTimer() {
        timerJob?.cancel()
        setPartialState {
            PartialState.TimerIdle(
                current = brushDuration,
                total = brushDuration
            )
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
                    is PartialState.TimerPaused -> {
                        return when (currentState.timer) {
                            State.Timer.Finished -> {
                                currentState.copy(
                                    timer = State.Timer.Idle(
                                        current = brushDuration,
                                        total = brushDuration
                                    )
                                )
                            }
                            is State.Timer.Idle -> {
                                currentState.copy(
                                    timer = State.Timer.Idle(
                                        current = currentState.timer.current,
                                        total = currentState.timer.total
                                    )
                                )
                            }
                            is State.Timer.Running -> {
                                currentState.copy(
                                    timer = State.Timer.Idle(
                                        current = currentState.timer.current,
                                        total = currentState.timer.total
                                    )
                                )
                            }
                        }
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

    private fun launchTimer(
        initialDuration: Long = brushDuration,
        totalDuration: Long = brushDuration
    ) {
        timerJob = viewModelScope.launch {
            startCountDown(
                initialDuration = initialDuration,
                totalDuration = totalDuration
            )
                .collect {
                    if (it.currentDuration <= 0) {
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