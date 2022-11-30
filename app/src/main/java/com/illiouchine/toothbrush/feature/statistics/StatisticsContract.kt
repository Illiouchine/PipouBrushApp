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
        val rawStatisticsState: RawStatisticsState,
        override val event: StatisticsEvent?
    ) : UiState {
        sealed class StatisticsEvent : UiEvent {}
        sealed class RawStatisticsState {
            object Loading : RawStatisticsState()
            data class Loaded(
                val brushHistory: List<Pair<Date, Int>>
            ) : RawStatisticsState()

            object Error : RawStatisticsState()
        }
    }



    sealed class StatisticsPartialState : UiPartialState {
        data class Loaded(
            val data: List<Pair<Date, Int>>
        ) : StatisticsPartialState()

        object Error : StatisticsPartialState()
    }
}