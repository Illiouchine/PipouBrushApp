package com.illiouchine.toothbrush.feature.brushing.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import com.illiouchine.toothbrush.ui.composable.chrono.Chrono
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.ExperimentalTime


@ExperimentalMaterialApi
@ExperimentalTime
@Composable
fun CountDownContent(
    duration: Duration = Duration.seconds(30),
    totalDuration: Duration = Duration.seconds(260)
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Chrono(
            modifier = Modifier.size(150.dp),
            seconds = duration.toInt(TimeUnit.SECONDS),
            totalSeconds = totalDuration.toInt(TimeUnit.SECONDS),
            centerColor = MaterialTheme.colors.background,
            backgroundColor = Color.Transparent,
        )
    }
}


@ExperimentalMaterialApi
@Preview
@Composable
@ExperimentalTime
fun CountDownContentBrushScreenLight() {
    ToothBrushTheme {
        CountDownContent()
    }
}


@ExperimentalMaterialApi
@Preview
@Composable
@ExperimentalTime
fun CountDownContentBrushScreenDark() {
    ToothBrushTheme(darkTheme = true) {
        CountDownContent()
    }
}
