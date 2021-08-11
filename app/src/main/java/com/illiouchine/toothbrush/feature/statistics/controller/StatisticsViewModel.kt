package com.illiouchine.toothbrush.feature.statistics.controller

import androidx.lifecycle.viewModelScope
import com.illiouchine.toothbrush.core.mvi.MviViewModel
import com.illiouchine.toothbrush.core.mvi.Reducer
import com.illiouchine.toothbrush.feature.statistics.usecase.GetBrushHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val getBrushHistory: GetBrushHistoryUseCase
) : MviViewModel<
        StatisticsContract.StatisticsIntent,
        StatisticsContract.StatisticsAction,
        StatisticsContract.StatisticsPartialState,
        StatisticsContract.StatisticsState,
        StatisticsContract.StatisticsEvent>() {

    init {
        setAction {
            StatisticsContract.StatisticsAction.LoadStatistics
        }
    }

    override fun createInitialState(): StatisticsContract.StatisticsState {
        return StatisticsContract.StatisticsState(
            rawStatisticsState = StatisticsContract.RawStatisticsState.Loading
        )
    }

    override fun createReducer(): Reducer<StatisticsContract.StatisticsState, StatisticsContract.StatisticsPartialState> {
        return object :
            Reducer<StatisticsContract.StatisticsState, StatisticsContract.StatisticsPartialState>() {
            override fun reduce(
                currentState: StatisticsContract.StatisticsState,
                partialState: StatisticsContract.StatisticsPartialState
            ): StatisticsContract.StatisticsState {
                return when (partialState) {
                    StatisticsContract.StatisticsPartialState.Error -> {
                        currentState.copy(
                            rawStatisticsState = StatisticsContract.RawStatisticsState.Error
                        )
                    }
                    is StatisticsContract.StatisticsPartialState.Loaded -> {
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

    override fun handleUserIntent(intent: StatisticsContract.StatisticsIntent): StatisticsContract.StatisticsAction {
        return when (intent) {
            else -> StatisticsContract.StatisticsAction.LoadStatistics
        }
    }

    override suspend fun handleAction(action: StatisticsContract.StatisticsAction) {
        return when (action) {
            StatisticsContract.StatisticsAction.LoadStatistics -> {
                loadStatistics()
            }
        }
    }

    private fun loadStatistics() {
        viewModelScope.launch {
            try {
                val brushHistory = getBrushHistory()
                setPartialState {
                    StatisticsContract.StatisticsPartialState.Loaded(
                        data = brushHistory.map { it.date.toString() }
                    )
                }
            } catch (e: Exception) {
                setPartialState {
                    StatisticsContract.StatisticsPartialState.Error
                }
            }
        }
    }
}