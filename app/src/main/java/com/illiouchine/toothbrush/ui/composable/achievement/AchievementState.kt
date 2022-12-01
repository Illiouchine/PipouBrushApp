package com.illiouchine.toothbrush.ui.composable.achievement

sealed class AchievementState{
    object Loading: AchievementState()
    object Error: AchievementState()
    data class Loaded(val achievements: List<Achievement>):AchievementState()

    data class Achievement(
        val name: String,
        val description: String,
        val earned: Boolean
    )
}
