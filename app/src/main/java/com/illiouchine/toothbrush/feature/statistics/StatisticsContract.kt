package com.illiouchine.toothbrush.feature.statistics

import com.illiouchine.mvi.core.*
import java.util.*

interface StatisticsContract {

    sealed class StatisticsIntent : UiIntent {
        object LoadScreen : StatisticsIntent()
    }

    sealed class StatisticsAction : UiAction {
        object LoadHistoryAndAchievement : StatisticsAction()
    }

    data class StatisticsState(
        val historyState: HistoryState,
        val achievementState: AchievementState,
        override val event: StatisticsEvent?
    ) : UiState {
        sealed class StatisticsEvent : UiEvent {}
        sealed class HistoryState {
            object Loading : HistoryState()
            object Error : HistoryState()
            data class Loaded(
                val brushHistory: List<History>
            ) : HistoryState()
        }
        sealed class AchievementState{
            object Loading : AchievementState()
            object Error : AchievementState()
            data class Loaded(
                val achievements: List<Achievement>
            ) : AchievementState()

        }
    }

    sealed class StatisticsPartialState : UiPartialState {
        data class HistoryLoaded(
            val data: List<History>
        ) : StatisticsPartialState()
        object HistoryError : StatisticsPartialState()
        data class AchievementLoaded(
            val data: List<Achievement>
        ) : StatisticsPartialState()
        object AchievementError : StatisticsPartialState()
    }

    data class History(
        val date: Date,
        val brushCount: Int
    )

    data class Achievement(
        val name: String,
        val description: String,
        val earned: Boolean
    )
}