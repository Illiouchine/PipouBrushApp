package com.illiouchine.toothbrush.feature.achievements

import com.illiouchine.mvi.core.*

interface AchievementsContract {


    sealed class AchievementsIntent : UiIntent {
        object LoadScreen : AchievementsIntent()
    }

    sealed class AchievementsAction : UiAction {
        object LoadAchievement : AchievementsAction()
    }

    data class AchievementsState(
        val achievementState: AchievementState,
        override val event: AchievementsEvent?
    ) : UiState {
        sealed class AchievementsEvent : UiEvent {}
        sealed class AchievementState {
            object Loading : AchievementState()
            object Error : AchievementState()
            data class Loaded(
                val achievements: List<Achievement>
            ) : AchievementState()
        }
    }

    sealed class AchievementsPartialState : UiPartialState {
        data class AchievementLoaded(
            val data: List<Achievement>
        ) : AchievementsPartialState()

        object AchievementError : AchievementsPartialState()
    }

    data class Achievement(
        val nameResId: Int,
        val descriptionResId: Int,
        val earned: Boolean
    )
}