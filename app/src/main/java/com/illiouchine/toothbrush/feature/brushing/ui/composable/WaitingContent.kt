package com.illiouchine.toothbrush.feature.brushing.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import com.illiouchine.toothbrush.ui.composable.MyButton
import com.illiouchine.toothbrush.ui.composable.chrono.Chrono
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
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Chrono(
            modifier = Modifier.size(150.dp),
            seconds = totalSeconds.toInt(TimeUnit.SECONDS),
            totalSeconds = totalSeconds.toInt(TimeUnit.SECONDS),
            centerColor = MaterialTheme.colors.background,
            backgroundColor = Color.Transparent,
        )
        Spacer(Modifier)
        //VideoExoPlayer()
        MyButton(text = "Go !") {
            onStartTimerClick()
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