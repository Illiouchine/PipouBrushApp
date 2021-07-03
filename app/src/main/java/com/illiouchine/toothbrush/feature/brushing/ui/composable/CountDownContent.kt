package com.illiouchine.toothbrush.feature.brushing.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import kotlin.time.ExperimentalTime


@ExperimentalTime
@Composable
fun CountDownContent(currentDurationInSeconds: Double) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
            ,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier)
            TimerText(currentDurationInSeconds)
            Spacer(Modifier)
            Spacer(Modifier)
        }

    }
}


@Preview
@Composable
@ExperimentalTime
fun CountDownContentBrushScreenLight() {
    ToothBrushTheme {
        CountDownContent(333.33)
    }
}


@Preview
@Composable
@ExperimentalTime
fun CountDownContentBrushScreenDark() {
    ToothBrushTheme (darkTheme = true) {
        CountDownContent(333.33)
    }
}
