package com.illiouchine.toothbrush.ui.composable.brushtimer.chrono

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun Chrono(
    seconds: Long,
    totalSeconds: Long,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Brush.radialGradient(listOf(Color.LightGray, backgroundColor))),
        contentAlignment = Alignment.Center
    ) {
        CircleMarkers(
            totalSeconds = totalSeconds,
            remainingSeconds = seconds
        )
        content()
    }
}


@Composable
@Preview
fun ChronoPreview() {
    Chrono(
        seconds = 240,
        totalSeconds = 500
    ) {}
}