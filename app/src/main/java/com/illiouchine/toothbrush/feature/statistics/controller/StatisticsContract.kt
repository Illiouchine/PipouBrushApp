package com.illiouchine.toothbrush.feature.statistics.controller

import com.illiouchine.mvi.core.UiAction
import com.illiouchine.mvi.core.UiEvent
import com.illiouchine.mvi.core.UiIntent
import com.illiouchine.mvi.core.UiPartialState
import com.illiouchine.mvi.core.UiState

interface StatisticsContract {

    sealed class StatisticsIntent : UiIntent {
        object LoadScreen : StatisticsIntent()
    }

    sealed class StatisticsAction : UiAction {
        object LoadStatistics : StatisticsAction()
    }

    data class StatisticsState(
        val rawStatisticsState: RawStatisticsState
    ) : UiState

    sealed class RawStatisticsState {
        object Loading : RawStatisticsState()
        data class Loaded(
            val data: List<String>
        ) : RawStatisticsState()

        object Error : RawStatisticsState()
    }

    sealed class StatisticsPartialState : UiPartialState {
        data class Loaded(
            val data: List<String>
        ) : StatisticsPartialState()

        object Error : StatisticsPartialState()
    }

    sealed class StatisticsEvent : UiEvent {}
}