package com.illiouchine.toothbrush.ui.composable.chrono

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private const val NB_MARKER = 120

@Composable
internal fun CircleMarkers(
    totalSeconds: Long,
    seconds: Long,
    markerColor: Color = MaterialTheme.colors.primaryVariant,
    activeMarkerColor: Color = MaterialTheme.colors.primary,
) {
    for (i in 0 until NB_MARKER) {

        val angle = i * (360 / NB_MARKER)

        // FIXME : don't work properly
        val isActive = i < seconds * (NB_MARKER / totalSeconds)

        Marker(
            angle = angle,
            active = isActive,
            markerColor = markerColor,
            activeMarkerColor = activeMarkerColor,
        )
    }
}

@Composable
internal fun Marker(
    angle: Int,
    active: Boolean,
    modifier: Modifier = Modifier,
    markerColor: Color = Color.LightGray,
    activeMarkerColor: Color = Color.DarkGray,
) {
    Box(
        modifier
            .fillMaxSize()
            .drawBehind {
                val theta = (angle - 90) * PI.toFloat() / 180f
                val startRadius = size.width / 2 * .72f
                val endRadius = size.width / 2 * .8f
                val startPos = Offset(cos(theta) * startRadius, sin(theta) * startRadius)
                val endPos = Offset(cos(theta) * endRadius, sin(theta) * endRadius)
                drawLine(
                    color = if (!active) activeMarkerColor else markerColor.copy(alpha = .1f),
                    start = center + startPos,
                    end = center + endPos,
                    strokeWidth = 8f,
                    cap = StrokeCap.Round
                )
            }
    )
}


@Composable
@Preview
fun CircleMarkerPreviewLight() {
    ToothBrushTheme() {
        Box(
            modifier = Modifier
                .size(300.dp)
                .background(Color.White)
        ) {
            CircleMarkers(
                totalSeconds = 360,
                seconds = 120
            )
        }
    }
}

@Composable
@Preview
fun CircleMarkerPreviewDark() {
    ToothBrushTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .background(Color.Black)
        ) {
            CircleMarkers(
                totalSeconds = 360,
                seconds = 120
            )
        }
    }
}