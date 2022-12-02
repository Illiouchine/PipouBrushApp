package com.illiouchine.toothbrush.feature.statistics

import androidx.lifecycle.viewModelScope
import com.illiouchine.mvi.core.MviViewModel
import com.illiouchine.mvi.core.Reducer
import com.illiouchine.toothbrush.usecase.GetAchievementsUseCase
import com.illiouchine.toothbrush.usecase.GetBrushHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsAction as Action
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsIntent as Intent
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsPartialState as PartialState
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract.StatisticsState as State


@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val getBrushHistory: GetBrushHistoryUseCase,
    private val getAchievements: GetAchievementsUseCase
) : MviViewModel<Intent, Action, PartialState, State>() {

    init {
        setAction { Action.LoadHistoryAndAchievement }
    }

    override fun createInitialState(): State {
        return State(
            historyState = State.HistoryState.Loading,
            achievementState = State.AchievementState.Loading,
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
                    PartialState.HistoryError -> {
                        currentState.copy(
                            historyState = State.HistoryState.Error
                        )
                    }
                    is PartialState.HistoryLoaded -> {
                        currentState.copy(
                            historyState = State.HistoryState.Loaded(
                                brushHistory = partialState.data
                            )
                        )
                    }
                    PartialState.AchievementError -> {
                        currentState.copy(
                            achievementState = State.AchievementState.Error
                        )
                    }
                    is PartialState.AchievementLoaded -> {
                        currentState.copy(
                            achievementState = State.AchievementState.Loaded(
                                achievements = partialState.data
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
                Action.LoadHistoryAndAchievement
            }
        }
    }

    override suspend fun handleAction(action: Action) {
        return when (action) {
            Action.LoadHistoryAndAchievement -> {
                loadHistory()
                loadAchievement()
            }
        }
    }

    private fun loadAchievement() {
        viewModelScope.launch {
            try {
                val achievements = getAchievements()
                setPartialState {
                    PartialState.AchievementLoaded(
                        data = achievements.toVMDataAchievement()
                    )
                }
            } catch (e: Exception) {
                setPartialState {
                    PartialState.AchievementError
                }
            }
        }
    }

    private fun loadHistory() {
        viewModelScope.launch {
            try {
                val brushHistory = getBrushHistory()
                setPartialState {
                    PartialState.HistoryLoaded(
                        data = brushHistory.toVMDataHistory()
                    )
                }
            } catch (e: Exception) {
                setPartialState {
                    PartialState.HistoryError
                }
            }
        }
    }
}

private fun List<GetBrushHistoryUseCase.BrushHistory>.toVMDataHistory(): List<StatisticsContract.History> {
    return this.map {
        StatisticsContract.History(
            date = it.date,
            brushCount = it.brushCount
        )
    }
}

private fun List<GetAchievementsUseCase.Achievement>.toVMDataAchievement(): List<StatisticsContract.Achievement> {
    return this.map {
        StatisticsContract.Achievement(
            nameResId = it.nameResId,
            descriptionResId = it.descriptionResId,
            earned = it.earned
        )
    }
}