package com.illiouchine.toothbrush.ui.composable.brushtimer.chrono


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.min

private const val NB_MARKER = 100
private const val rotationAngle: Float = (360f / NB_MARKER)

@Composable
internal fun CircleMarkers2(
    totalSeconds: Long,
    remainingSeconds: Long,
    markerColor: Color = MaterialTheme.colorScheme.secondary.copy(alpha = .1f),
    activeMarkerColor: Color = MaterialTheme.colorScheme.secondary,
) {
    Canvas(
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxSize(),
    ) {
        val sizeUnit = min(size.height, size.width) / 20

        for (i in 1 until NB_MARKER + 1) {

            rotate(degrees = -(rotationAngle * i)) {

                val isActive = i < ((NB_MARKER.toFloat() / totalSeconds) * remainingSeconds)
                drawLine(
                    color = if (isActive) activeMarkerColor else markerColor,
                    start = center.copy(y = sizeUnit),
                    end = center.copy(y = sizeUnit * 2),
                    strokeWidth = sizeUnit / 5,
                    cap = StrokeCap.Round
                )

            }
        }
    }
}

@Composable
@Preview(showBackground = true, heightDp = 100, widthDp = 100)
fun Quarter100CircleMarkerPreview2() {
    Box(modifier = Modifier.size(300.dp)) {
        CircleMarkers2(
            totalSeconds = 180,
            remainingSeconds = 40
        )
    }
}

@Composable
@Preview(showBackground = true, heightDp = 100, widthDp = 100)
fun Mid100CircleMarkerPreview2() {
    CircleMarkers2(
        totalSeconds = 180,
        remainingSeconds = 60
    )
}

@Composable
@Preview(showBackground = true, heightDp = 100, widthDp = 100)
fun Quarter200CircleMarkerPreview2() {
    CircleMarkers2(
        totalSeconds = 200,
        remainingSeconds = 50
    )
}

@Composable
@Preview(showBackground = true, heightDp = 100, widthDp = 100)
fun Mid200CircleMarkerPreview2() {
    CircleMarkers2(
        totalSeconds = 200,
        remainingSeconds = 100
    )
}

@Composable
@Preview(showBackground = true, heightDp = 100, widthDp = 100)
fun Quarter360CircleMarkerPreview2() {
    CircleMarkers2(
        totalSeconds = 360,
        remainingSeconds = 90
    )
}

@Composable
@Preview(showBackground = true, heightDp = 100, widthDp = 100)
fun Mid360CircleMarkerPreview2() {
    CircleMarkers2(
        totalSeconds = 360,
        remainingSeconds = 180
    )
}

