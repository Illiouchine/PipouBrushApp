package com.illiouchine.toothbrush.ui.composable.brushtimer.chrono

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
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
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    BoxWithConstraints(
        modifier = modifier
            .aspectRatio(1f)
            .fillMaxSize()
            .background(Brush.radialGradient(listOf(Color.LightGray, backgroundColor))),
        contentAlignment = Alignment.Center
    ) {
        CircleMarkers(
            totalSeconds = totalSeconds,
            remainingSeconds = seconds
        )
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding((this.maxHeight / 4)),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}


@Composable
@Preview(heightDp = 100)
fun ChronoPreview() {
    Chrono(
        seconds = 240,
        totalSeconds = 500
    ) {
        Text(text = "Dododi")
    }
}