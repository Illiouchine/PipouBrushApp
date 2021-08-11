package com.illiouchine.toothbrush.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.illiouchine.toothbrush.R

sealed class Screen(
    val route: String,
    @StringRes val textId: Int,
    @DrawableRes val iconId: Int
) {

    object Brush : Screen(
        route = "Brush",
        textId = R.string.screen_brush_menu_title,
        iconId = R.drawable.ic_timer
    )

    object Statistics : Screen(
        route = "Statistics",
        textId = R.string.screen_statistics_menu_title,
        iconId = R.drawable.ic_chart
    )

    object Settings : Screen(
        route = "Settings",
        textId = R.string.screen_settings_menu_title,
        iconId = R.drawable.ic_launcher_background
    )
}