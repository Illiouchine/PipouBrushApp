package com.illiouchine.toothbrush.feature

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.materialIcon
import com.illiouchine.toothbrush.R

val screenList = listOf(
    Screen.Brush,
    Screen.Statistics,
    Screen.Settings
)

sealed class Screen(
    val route: String,
    @StringRes val textId: Int,
    @DrawableRes val iconId: Int
) {
    object Brush : Screen(
        route = "Brush",
        textId = R.string.screen_brush_menu_title,
        iconId = R.drawable.ic_toothbrush_1
    )

    object Statistics : Screen(
        route = "Statistics",
        textId = R.string.screen_statistics_menu_title,
        iconId = R.drawable.ic_achievement_trophy
    )

    object Settings : Screen(
        route = "Settings",
        textId = R.string.screen_settings_menu_title,
        iconId = R.drawable.ic_settings_1
    )
}