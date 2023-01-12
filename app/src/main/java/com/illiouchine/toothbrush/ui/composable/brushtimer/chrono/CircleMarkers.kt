package com.illiouchine.toothbrush.ui.composable.brushtimer.chrono

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private const val NB_MARKER = 180

@Composable
internal fun CircleMarkers(
    totalSeconds: Long,
    remainingSeconds: Long,
    markerColor: Color = MaterialTheme.colorScheme.secondary,
    activeMarkerColor: Color = MaterialTheme.colorScheme.secondary,
) {
    Canvas(
        modifier = Modifier.fillMaxSize(),
    ) {
        println("---------------")
        println("---------  Start Print Marker -------")
        // i = 0 .. 180
        for (i in 0 until NB_MARKER) {

            val angle = -i * (360 / NB_MARKER)

            // FIXME : don't work properly
            // issue with totalSeconds > 180 ?

            // TEST with totalSeconds 200
            // isActive = 1 < 1 * (180/200) => 1 < 0.9
            // isActive = 1 < 2 * (180/200) => 1 < 1.8
            val isActive = i < (remainingSeconds * (NB_MARKER / totalSeconds))
            println("isActive = i < remainingSeconds * (NB_MARKER/totalSeconds) ")
            println("$isActive = $i < ${(remainingSeconds * (NB_MARKER / totalSeconds))}")

            val theta = (angle - 90) * PI.toFloat() / 180f
            val startRadius = size.width / 2 * .72f
            val endRadius = size.width / 2 * .8f
            val startPos = Offset(cos(theta) * startRadius, sin(theta) * startRadius)
            val endPos = Offset(cos(theta) * endRadius, sin(theta) * endRadius)

            drawLine(
                color = if (isActive) activeMarkerColor else markerColor.copy(alpha = .1f),
                start = center + startPos,
                end = center + endPos,
                strokeWidth = 4f,
                cap = StrokeCap.Round
            )
        }
    }
}

@Composable
@Preview(showBackground = true, heightDp = 100, widthDp = 100)
fun IssueCircleMarkerPreview() {
    CircleMarkers(
        totalSeconds = 180,
        remainingSeconds = 40
    )
}

@Composable
@Preview(showBackground = true, heightDp = 100, widthDp = 100)
fun FullCircleMarkerPreview() {
    CircleMarkers(
        totalSeconds = 360,
        remainingSeconds = 360
    )
}

@Composable
@Preview(showBackground = true, heightDp = 100, widthDp = 100)
fun MidCircleMarkerPreview() {
    CircleMarkers(
        totalSeconds = 360,
        remainingSeconds = 180
    )
}

@Composable
@Preview(showBackground = true, heightDp = 100, widthDp = 100)
fun EmptyCircleMarkerPreview() {
    CircleMarkers(
        totalSeconds = 360,
        remainingSeconds = 0
    )
}

@Composable
@Preview(showBackground = true, heightDp = 100, widthDp = 100)
fun OneMinCircleMarkerPreview() {
    CircleMarkers(
        totalSeconds = 60,
        remainingSeconds = 46
    )
}

@Composable
@Preview(showBackground = true, heightDp = 100, widthDp = 100)
fun ShouldBeFullCircleMarkerPreview() {
    CircleMarkers(
        totalSeconds = 120,
        remainingSeconds = 120
    )
}