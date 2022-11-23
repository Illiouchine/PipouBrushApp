package com.illiouchine.toothbrush.ui.composable.brushtimer

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import com.illiouchine.toothbrush.ui.composable.brushtimer.chrono.Chrono

@ExperimentalMaterialApi
@Composable
fun IdleTimer(
    totalSeconds: Long = 180L,
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Chrono(
            modifier = Modifier.size(150.dp),
            seconds = totalSeconds,
            totalSeconds = totalSeconds,
            centerColor = MaterialTheme.colors.background,
            backgroundColor = Color.Transparent,
        )
        Text(text = "Click to start !")
    }
}


@ExperimentalMaterialApi
@Preview
@Composable
fun WaitingStartBrushScreenLight() {
    ToothBrushTheme {
        IdleTimer()
    }
}


@ExperimentalMaterialApi
@Preview
@Composable
fun WaitingStartBrushScreenDark() {
    ToothBrushTheme(darkTheme = true) {
        IdleTimer()
    }
}
