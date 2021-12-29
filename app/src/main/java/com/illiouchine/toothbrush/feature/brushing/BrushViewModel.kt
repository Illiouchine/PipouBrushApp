package com.illiouchine.toothbrush.feature.brushing

import androidx.lifecycle.viewModelScope
import com.illiouchine.mvi.core.MviViewModel
import com.illiouchine.mvi.core.Reducer
import com.illiouchine.toothbrush.feature.brushing.timer.Timer
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
    private var timer: Timer = Timer(duration = brushDuration)

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
        try {
            timer.resume()
            setPartialState {
                PartialState.TimerRunning(
                    current = timer.getRemindingTime(),
                    total = brushDuration
                )
            }
        } catch (exception: Timer.TimerException) {
            setEvent {
                Event.ShowToast("${exception.message}")
            }
        }
    }

    private fun startTimer() {
        timer.start(0L)
        setPartialState {
            PartialState.TimerRunning(
                current = timer.getRemindingTime(),
                total = brushDuration
            )
        }
    }

    private fun resetTimer() {
        timer = Timer(duration = brushDuration)
        setPartialState {
            PartialState.TimerRunning(
                current = timer.getRemindingTime(),
                total = brushDuration
            )
        }
    }

    private fun pauseTimer() {
        timer.pause()
        setPartialState {
            PartialState.TimerRunning(
                current = timer.getRemindingTime(),
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
                    is BrushContract.BrushPartialState.TimerPaused -> {
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
                    setPartialState {
                        PartialState.TimerRunning(
                            current = it.currentDuration,
                            total = it.totalDuration
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