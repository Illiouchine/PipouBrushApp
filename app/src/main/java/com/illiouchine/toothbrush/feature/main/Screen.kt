package com.illiouchine.toothbrush.feature.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.illiouchine.toothbrush.R

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    @StringRes val textId: Int,
    @DrawableRes val iconId: Int
) {

    object Brush : Screen(
        route = "Brush",
        resourceId = R.string.screen_brush_menu_title,
        textId = R.string.screen_brush_menu_title,
        iconId = R.drawable.ic_brush
    )

    object Calendar : Screen(
        route = "Calendar",
        resourceId = R.string.screen_calendar_menu_title,
        textId = R.string.screen_calendar_menu_title,
        iconId = R.drawable.ic_today
    )

    object Settings : Screen(
        route = "Settings",
        resourceId = R.string.screen_settings_menu_title,
        textId = R.string.screen_calendar_menu_title,
        iconId = R.drawable.ic_launcher_background
    )
}