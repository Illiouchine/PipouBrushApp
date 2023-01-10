package com.illiouchine.toothbrush.feature.achievements

import androidx.lifecycle.viewModelScope
import com.illiouchine.mvi.core.MviViewModel
import com.illiouchine.mvi.core.Reducer
import com.illiouchine.toothbrush.usecase.achievement.GetAchievementsUseCase
import com.illiouchine.toothbrush.usecase.datagateway.Achievement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AchievementsViewModel @Inject constructor(
    private val getAchievements: GetAchievementsUseCase
) : MviViewModel<AchievementsContract.AchievementsIntent, AchievementsContract.AchievementsAction, AchievementsContract.AchievementsPartialState, AchievementsContract.AchievementsState>() {

    init {
        setAction { AchievementsContract.AchievementsAction.LoadAchievement }
    }

    override fun createInitialState(): AchievementsContract.AchievementsState {
        return AchievementsContract.AchievementsState(
            achievementState = AchievementsContract.AchievementsState.AchievementState.Loading,
            event = null
        )
    }

    override fun createReducer(): Reducer<AchievementsContract.AchievementsState, AchievementsContract.AchievementsPartialState> {
        return object :
            Reducer<AchievementsContract.AchievementsState, AchievementsContract.AchievementsPartialState>() {
            override fun reduce(
                currentState: AchievementsContract.AchievementsState,
                partialState: AchievementsContract.AchievementsPartialState
            ): AchievementsContract.AchievementsState {
                return when (partialState) {
                    AchievementsContract.AchievementsPartialState.AchievementError -> {
                        currentState.copy(
                            achievementState = AchievementsContract.AchievementsState.AchievementState.Error
                        )
                    }
                    is AchievementsContract.AchievementsPartialState.AchievementLoaded -> {
                        currentState.copy(
                            achievementState = AchievementsContract.AchievementsState.AchievementState.Loaded(
                                achievements = partialState.data
                            )
                        )
                    }
                }
            }
        }
    }

    override fun handleUserIntent(intent: AchievementsContract.AchievementsIntent): AchievementsContract.AchievementsAction {
        return when (intent) {
            AchievementsContract.AchievementsIntent.LoadScreen -> {
                AchievementsContract.AchievementsAction.LoadAchievement
            }
        }
    }

    override suspend fun handleAction(action: AchievementsContract.AchievementsAction) {
        return when (action) {
            AchievementsContract.AchievementsAction.LoadAchievement -> {
                loadAchievement()
            }
        }
    }

    private fun loadAchievement() {
        viewModelScope.launch {
            try {
                val achievements = getAchievements()
                setPartialState {
                    AchievementsContract.AchievementsPartialState.AchievementLoaded(
                        data = achievements.toVMDataAchievement()
                    )
                }
            } catch (e: Exception) {
                setPartialState {
                    AchievementsContract.AchievementsPartialState.AchievementError
                }
            }
        }
    }
}

private fun List<Achievement>.toVMDataAchievement(): List<AchievementsContract.Achievement> {
    return this.map {
        AchievementsContract.Achievement(
            nameResId = it.nameResId,
            descriptionResId = it.descriptionResId,
            earned = it.earned
        )
    }
}