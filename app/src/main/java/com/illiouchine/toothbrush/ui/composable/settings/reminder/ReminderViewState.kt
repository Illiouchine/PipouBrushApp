package com.illiouchine.toothbrush.ui.composable.settings.reminder

sealed class ReminderViewState {
    object Loading : ReminderViewState()
    data class Loaded(
        val checked: Boolean,
        val hour: Int,
        val min: Int,
    ) : ReminderViewState()
}
