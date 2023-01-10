package com.illiouchine.toothbrush.ui.composable.achievement

sealed class AchievementState {
    object Loading : AchievementState()
    object Error : AchievementState()
    data class Loaded(val achievements: List<Achievement>) : AchievementState()

    data class Achievement(
        val name: String,
        val description: String,
        val earned: Boolean
    )
}

fun previewDataAchievementList(): List<AchievementState.Achievement> {
    return listOf(
        previewDataAchievement("Achievement 1", "Achievement description", false),
        previewDataAchievement(
            "Achievement 2",
            "Achievement description, this could be long and be display on 5 lines",
            false
        ),
        previewDataAchievement(
            "Achievement 3",
            "Achievement description, this could be long and be display on 5 lines, i say extremely long",
            false
        ),
    )
}

fun previewDataAchievement(
    name: String = "Preview Achievement",
    description: String = "Achievement description, this could be long and be display on 5 lines",
    earned: Boolean = true
): AchievementState.Achievement {
    return AchievementState.Achievement(
        name = name,
        description = description,
        earned = earned
    )
}
