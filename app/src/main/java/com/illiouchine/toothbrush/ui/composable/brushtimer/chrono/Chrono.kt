package com.illiouchine.toothbrush.ui.composable.brushtimer.chrono

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.illiouchine.toothbrush.ui.ToothBrushTheme


/**
 * All credit to GerardPaligot
 * Github : https://github.com/GerardPaligot/android-countdown
 * Article : https://medium.com/@GerardPaligot/jetpack-compose-how-to-play-with-canvas-36d3638996b6
 */

@ExperimentalMaterialApi
@Composable
fun Chrono(
    seconds: Long,
    totalSeconds: Long,
    modifier: Modifier = Modifier,
    progressColor: List<Color> = listOf(
        MaterialTheme.colors.primary,
        MaterialTheme.colors.primaryVariant
    ),
    backgroundProgressColor: List<Color> = listOf(Color.Transparent, Color.Transparent),
    centerColor: Color = Color.Transparent,
    backgroundColor: Color = Color.Transparent,
    content: @Composable () -> Unit
) {
    val progressAngle by animateFloatAsState(
        targetValue = -360f / totalSeconds.toFloat() * seconds,
        animationSpec = tween(500)
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Brush.radialGradient(listOf(Color.LightGray, backgroundColor))),
        contentAlignment = Alignment.Center
    ) {
        CircleMarkers(
            totalSeconds = totalSeconds,
            seconds = seconds
        )
        /*
        CircleProgress(
            angle = progressAngle,
            progressColor = progressColor,
            backgroundProgressColor = backgroundProgressColor,
            centerColor = centerColor,
        )

         */
        content()
    }
}


@ExperimentalMaterialApi
@Composable
@Preview
fun ChronoPreviewLight() {
    ToothBrushTheme {
        Chrono(
            seconds = 240,
            totalSeconds = 500
        ){}
    }
}


@ExperimentalMaterialApi
@Composable
@Preview
fun ChronoPreviewDark() {
    ToothBrushTheme(darkTheme = true) {
        Chrono(
            seconds = 240,
            totalSeconds = 500
        ){}
    }
}