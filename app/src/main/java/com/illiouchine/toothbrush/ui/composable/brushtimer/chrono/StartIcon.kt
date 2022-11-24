package com.illiouchine.toothbrush.ui.composable.brushtimer.chrono

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.LightColorPalette

@Preview
@Composable
fun StartIcon(
    modifier: Modifier = Modifier.size(100.dp).padding(32.dp)
) {
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
            color = LightColorPalette.onBackground,
        )
    }
}