package com.illiouchine.toothbrush.ui.composable.brushtimer.chrono

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.ToothBrushTheme

@Composable
internal fun CircleProgress(
    angle: Float,
    progressColor: List<Color> = listOf(Color.DarkGray, Color.Gray, Color.LightGray),
    backgroundProgressColor: List<Color> = listOf(Color.Transparent, Color.Transparent),
    centerColor: Color = Color.White,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawWithContent {

                val sizeMultiplier = 0.70f
                val strokeWidth = size.width / 60

                val drawSize = Size(
                    size.width * sizeMultiplier,
                    size.height * sizeMultiplier
                )

                val drawOffset = Offset(
                    size.width / 2 - (drawSize.width / 2),
                    size.height / 2 - (drawSize.height / 2)
                )

                drawArc(
                    brush = Brush.verticalGradient(
                        backgroundProgressColor
                    ),
                    startAngle = 0f,
                    sweepAngle = 360f,
                    topLeft = drawOffset,
                    size = drawSize,
                    useCenter = false,
                    style = Stroke(width = strokeWidth)
                )
                drawArc(
                    brush = Brush.verticalGradient(
                        progressColor
                    ),
                    topLeft = drawOffset,
                    size = drawSize,
                    startAngle = -90f,
                    sweepAngle = angle,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
                drawArc(
                    color = centerColor,
                    topLeft = drawOffset + Offset(strokeWidth / 2, strokeWidth / 2),
                    size = Size(drawSize.width - strokeWidth, drawSize.height - strokeWidth),
                    startAngle = -90f,
                    sweepAngle = 360.0f,
                    useCenter = true
                )
            },
    )
}

@Preview
@Composable
fun CircleProgress45() {
    ToothBrushTheme() {
        Box(modifier = Modifier.size(200.dp)) {
            CircleProgress(angle = 45.0F)
        }
    }
}

@Preview
@Composable
fun CircleProgress95() {
    ToothBrushTheme() {
        Box(modifier = Modifier.size(200.dp)) {
            CircleProgress(angle = 95.0F)
        }
    }
}

@Preview
@Composable
fun CircleProgress180() {
    ToothBrushTheme() {
        Box(modifier = Modifier.size(200.dp)) {
            CircleProgress(angle = 180.0F)
        }
    }
}