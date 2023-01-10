package com.illiouchine.toothbrush.ui.composable.settings

import kotlin.time.Duration

sealed class CountDownState {
    object Loading : CountDownState()
    data class Loaded(val duration: Duration) : CountDownState()
}
