package com.illiouchine.toothbrush.feature.brushing.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import com.illiouchine.toothbrush.ui.composable.MyButton
import com.illiouchine.toothbrush.ui.composable.chrono.Chrono
import com.illiouchine.toothbrush.ui.utils.assetsToBitmap
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalMaterialApi
@ExperimentalTime
@Composable
fun WaitingContent(
    totalSeconds: Duration = Duration.Companion.seconds(30),
    onStartTimerClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val bitmap: ImageBitmap? = remember {
        context
            .assetsToBitmap("brossage_dents_redimension.jpg")
            ?.asImageBitmap()
    }

    Surface {
        bitmap?.let {
            Image(
                modifier = Modifier.fillMaxSize(),
                bitmap = it,
                contentDescription = "BG"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier)
            Chrono(
                modifier = Modifier.size(300.dp),
                seconds = totalSeconds.toInt(TimeUnit.SECONDS),
                totalSeconds = totalSeconds.toInt(TimeUnit.SECONDS),
                centerColor = MaterialTheme.colors.background,
                backgroundColor = MaterialTheme.colors.background,
            )
            Spacer(Modifier)
            //VideoExoPlayer()
            MyButton(text = "Go !") {
                onStartTimerClick()
            }
            Spacer(Modifier)
        }

    }
}


@ExperimentalMaterialApi
@Preview
@Composable
@ExperimentalTime
fun WaitingStartBrushScreenLight() {
    ToothBrushTheme {
        WaitingContent()
    }
}


@ExperimentalMaterialApi
@Preview
@Composable
@ExperimentalTime
fun WaitingStartBrushScreenDark() {
    ToothBrushTheme(darkTheme = true) {
        WaitingContent()
    }
}