package com.illiouchine.toothbrush.feature.brushing

import androidx.lifecycle.viewModelScope
import com.illiouchine.mvi.core.MviViewModel
import com.illiouchine.mvi.core.Reducer
import com.illiouchine.toothbrush.usecase.GetCountDownDurationUseCase
import com.illiouchine.toothbrush.usecase.LaunchVibratorUseCase
import com.illiouchine.toothbrush.usecase.SaveBrushProgressUseCase
import com.illiouchine.toothbrush.usecase.StartCountDownUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration
import com.illiouchine.toothbrush.feature.brushing.BrushContract.BrushAction as Action
import com.illiouchine.toothbrush.feature.brushing.BrushContract.BrushIntent as Intent
import com.illiouchine.toothbrush.feature.brushing.BrushContract.BrushPartialState as PartialState
import com.illiouchine.toothbrush.feature.brushing.BrushContract.BrushState as State

@HiltViewModel
class BrushViewModel @Inject constructor(
    private val startCountDown: StartCountDownUseCase,
    private val launchVibrator: LaunchVibratorUseCase,
    private val saveBrushProgress: SaveBrushProgressUseCase,
    private val getCountDownDurationUseCase: GetCountDownDurationUseCase
) : MviViewModel<Intent, Action, PartialState, State>() {

    private var timerJob: Job? = null

    override fun createInitialState(): State =
        State(
            timer = State.Timer.Idle,
            event = null
        )

    override fun handleUserIntent(intent: Intent): Action {
        return when (intent) {
            Intent.StartBrushing -> Action.StartTimer
            Intent.ResetBrushing -> Action.ResetTimer
        }
    }

    override suspend fun handleAction(action: Action) {
        when (action) {
            Action.ResetTimer -> resetTimer()
            Action.StartTimer -> {
                timerJob?.cancel()
                val countDownDuration = getCountDownDurationUseCase()
                launchTimer(
                    initialDuration = countDownDuration,
                    totalDuration = countDownDuration
                )
            }
            Action.FinishTimer -> resetTimer()
        }
    }

    private fun resetTimer() {
        timerJob?.cancel()
        setPartialState { PartialState.TimerIdle }
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
                        currentState.copy(timer = State.Timer.Finished)
                    }
                    is BrushContract.BrushPartialState.TimerIdle -> {
                        currentState.copy(timer = State.Timer.Idle)
                    }
                }
            }
        }
    }

    private fun launchTimer(
        initialDuration: Duration,
        totalDuration: Duration
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