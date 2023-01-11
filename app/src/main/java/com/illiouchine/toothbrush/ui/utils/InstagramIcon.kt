package com.illiouchine.toothbrush.ui.utils

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import com.illiouchine.toothbrush.ui.bleu_instagram
import com.illiouchine.toothbrush.ui.jaune_instagram
import com.illiouchine.toothbrush.ui.magenta_instagram

@Preview
@Composable
fun InstagramIcon(
    modifier: Modifier = Modifier,
    clickLabel: String = "",
    onClick: () -> Unit = {},
) {
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .aspectRatio(1f)
            .clickable(
                onClick = { onClick() },
                onClickLabel = clickLabel
            ),
        onDraw = {
            val measureUnit = size.height / 10
            drawRoundRect(
                brush = Brush.radialGradient(
                    0f to jaune_instagram,
                    0.8f to magenta_instagram,
                    1f to bleu_instagram,
                    center = Offset(measureUnit * 3, measureUnit * 10),
                    radius = measureUnit * 10
                ),
                topLeft = Offset.Zero,
                size = size,
                cornerRadius = CornerRadius(measureUnit * 2),
                alpha = 1f,
                style = Fill,
                colorFilter = null,
            )
            drawRoundRect(
                color = androidx.compose.ui.graphics.Color.White,
                topLeft = Offset(measureUnit * 1.5f, measureUnit * 1.5f),
                size = Size(measureUnit * 7f, measureUnit * 7f),
                cornerRadius = CornerRadius(measureUnit * 2),
                alpha = 1f,
                style = Stroke(
                    measureUnit * 0.8f
                ),
                colorFilter = null,
            )
            drawCircle(
                color = androidx.compose.ui.graphics.Color.White,
                radius = measureUnit * 1.5f,
                alpha = 1f,
                style = Stroke(
                    measureUnit * 0.8f
                ),
                colorFilter = null,
            )
            drawCircle(
                color = androidx.compose.ui.graphics.Color.White,
                center = Offset(measureUnit * 7f, measureUnit * 3f),
                radius = measureUnit / 2,
                alpha = 1f,
                style = Fill,
                colorFilter = null,
            )
        }
    )
}