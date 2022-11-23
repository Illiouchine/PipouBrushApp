package com.illiouchine.toothbrush.ui.composable.brushtimer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
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
fun FinishedTimer() {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Chrono(
            modifier = Modifier.size(150.dp),
            seconds = 0,
            totalSeconds = 180,
            centerColor = MaterialTheme.colors.background,
            backgroundColor = Color.Transparent,
        )
        Text(text = "Bravo !")
    }
}


@ExperimentalMaterialApi
@Preview
@Composable
fun RestartContentBrushScreenLight() {
    ToothBrushTheme {
        FinishedTimer()
    }
}


@ExperimentalMaterialApi
@Preview
@Composable
fun RestartContentBrushScreenDark() {
    ToothBrushTheme(darkTheme = true) {
        FinishedTimer()
    }
}