package com.illiouchine.toothbrush.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.utils.assetsToBitmap

@Preview
@Composable
fun PipouBackground(
    enableBlur: Boolean = false,
    content: @Composable ()-> Unit = {}
) {
    val context = LocalContext.current
    val bitmap: ImageBitmap? = remember {
        context
            .assetsToBitmap("brossage_dents_redimension.jpg")
            ?.asImageBitmap()
    }

    val modifier = if (enableBlur) Modifier.blur(radius = 40.dp) else Modifier

    Surface {
        bitmap?.let {
            Image(
                modifier = modifier.fillMaxSize(),
                bitmap = it,
                contentDescription = "BG"
            )
        }
        content()
    }
}

@Preview
@Composable
fun PipouBackgroundWithBlur(
){
    PipouBackground(
        enableBlur = true
    )
}