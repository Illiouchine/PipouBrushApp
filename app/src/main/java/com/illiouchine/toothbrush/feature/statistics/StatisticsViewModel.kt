package com.illiouchine.toothbrush.feature.statistics

import androidx.lifecycle.viewModelScope
import com.illiouchine.mvi.core.MviViewModel
import com.illiouchine.mvi.core.Reducer
import com.illiouchine.toothbrush.usecase.GetStatisticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsAction as Action
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsIntent as Intent
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsPartialState as PartialState
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsState as State


@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val getBrushHistory: GetStatisticsUseCase,
) : MviViewModel<Intent, Action, PartialState, State>() {

    init {
        setAction { Action.LoadStatistics }
    }

    override fun createInitialState(): State {
        return State(
            statisticsState = State.StatisticsState.Loading,
            event = null
        )
    }

    override fun createReducer(): Reducer<State, PartialState> {
        return object :
            Reducer<State, PartialState>() {
            override fun reduce(
                currentState: State,
                partialState: PartialState
            ): State {
                return when (partialState) {
                    PartialState.StatisticsError -> {
                        currentState.copy(
                            statisticsState = State.StatisticsState.Error
                        )
                    }
                    is PartialState.StatisticsLoaded -> {
                        currentState.copy(
                            statisticsState = State.StatisticsState.Loaded(
                                brushHistory = partialState.data
                            )
                        )
                    }
                }
            }
        }
    }

    override fun handleUserIntent(intent: Intent): Action {
        return when (intent) {
            Intent.LoadScreen -> {
                Action.LoadStatistics
            }
        }
    }

    override suspend fun handleAction(action: Action) {
        return when (action) {
            Action.LoadStatistics -> {
                loadHistory()
            }
        }
    }

    private fun loadHistory() {
        viewModelScope.launch {
            try {
                val brushHistory = getBrushHistory()
                setPartialState {
                    PartialState.StatisticsLoaded(
                        data = brushHistory.toVMDataHistory()
                    )
                }
            } catch (e: Exception) {
                setPartialState {
                    PartialState.StatisticsError
                }
            }
        }
    }
}

private fun List<GetStatisticsUseCase.Statistics>.toVMDataHistory(): List<StatisticsContract.Statistics> {
    return this.map {
        StatisticsContract.Statistics(
            date = it.date,
            brushCount = it.brushCount
        )
    }
}