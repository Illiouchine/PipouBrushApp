package com.illiouchine.toothbrush.feature.brushing.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
            ,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier)
            Chrono(
                modifier = Modifier.size(300.dp),
                seconds = duration.toInt(TimeUnit.SECONDS),
                totalSeconds = totalDuration.toInt(TimeUnit.SECONDS),
                centerColor = MaterialTheme.colors.background,
                backgroundColor = MaterialTheme.colors.background,
            )
            Spacer(Modifier)
            Spacer(Modifier)
        }

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
    ToothBrushTheme (darkTheme = true) {
        CountDownContent()
    }
}
