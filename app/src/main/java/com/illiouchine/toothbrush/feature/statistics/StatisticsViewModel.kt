package com.illiouchine.toothbrush.feature.statistics

import androidx.lifecycle.viewModelScope
import com.illiouchine.mvi.core.MviViewModel
import com.illiouchine.mvi.core.Reducer
import com.illiouchine.toothbrush.usecase.GetBrushHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsAction as Action
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsEvent as Event
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsIntent as Intent
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsPartialState as PartialState
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsState as State


@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val getBrushHistory: GetBrushHistoryUseCase
) : MviViewModel<Intent, Action, PartialState, State, Event>() {

    init {
        setAction { Action.LoadStatistics }
    }

    override fun createInitialState(): State {
        return State(
            rawStatisticsState = StatisticsContract.RawStatisticsState.Loading
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
                    PartialState.Error -> {
                        currentState.copy(
                            rawStatisticsState = StatisticsContract.RawStatisticsState.Error
                        )
                    }
                    is PartialState.Loaded -> {
                        currentState.copy(
                            rawStatisticsState = StatisticsContract.RawStatisticsState.Loaded(
                                data = partialState.data
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
                loadStatistics()
            }
        }
    }

    private fun loadStatistics() {
        viewModelScope.launch {
            try {
                val brushHistory = getBrushHistory()
                setPartialState {
                    PartialState.Loaded(
                        data = brushHistory.map { it.date.toString() }
                    )
                }
            } catch (e: Exception) {
                setPartialState {
                    PartialState.Error
                }
            }
        }
    }
}