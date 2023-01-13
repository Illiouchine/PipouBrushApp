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


/**
 * All credit to GerardPaligot
 * Github : https://github.com/GerardPaligot/android-countdown
 * Article : https://medium.com/@GerardPaligot/jetpack-compose-how-to-play-with-canvas-36d3638996b6
 */

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
        CircleMarkers2(
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