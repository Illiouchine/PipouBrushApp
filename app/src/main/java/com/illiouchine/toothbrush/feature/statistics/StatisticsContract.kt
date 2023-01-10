package com.illiouchine.toothbrush.feature.statistics

import com.illiouchine.mvi.core.*
import java.util.*

interface StatisticsContract {

    sealed class StatisticsIntent : UiIntent {
        object LoadScreen : StatisticsIntent()
    }

    sealed class StatisticsAction : UiAction {
        object LoadStatistics : StatisticsAction()
    }

    data class StatisticsState(
        val statisticsState: StatisticsState,
        override val event: StatisticsEvent?
    ) : UiState {
        sealed class StatisticsEvent : UiEvent {}
        sealed class StatisticsState {
            object Loading : StatisticsState()
            object Error : StatisticsState()
            data class Loaded(
                val brushHistory: List<Statistics>
            ) : StatisticsState()
        }
    }

    sealed class StatisticsPartialState : UiPartialState {
        data class StatisticsLoaded(
            val data: List<Statistics>
        ) : StatisticsPartialState()

        object StatisticsError : StatisticsPartialState()
    }

    data class Statistics(
        val date: Date,
        val brushCount: Int
    )
}