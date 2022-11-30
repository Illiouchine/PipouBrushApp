package com.illiouchine.toothbrush.ui.composable.brushtimer.chrono

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun StartIcon(
    modifier: Modifier = Modifier
) {
    val pathColor = MaterialTheme.colorScheme.secondary
    Canvas(modifier = modifier) {
        val trianglePath = Path().let {
            it.moveTo(this.size.width * 0.05f, 0f)
            it.lineTo(this.size.width, this.size.height * 0.5f)
            it.lineTo(this.size.width * 0.05f, this.size.height)
            it.close()
            it
        }

        drawPath(
            path = trianglePath,
            color = pathColor,
        )
    }
}