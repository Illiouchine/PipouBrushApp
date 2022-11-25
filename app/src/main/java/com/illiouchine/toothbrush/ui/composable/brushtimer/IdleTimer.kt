package com.illiouchine.toothbrush.ui.composable.brushtimer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.R
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import com.illiouchine.toothbrush.ui.composable.brushtimer.chrono.Chrono
import com.illiouchine.toothbrush.ui.composable.brushtimer.chrono.StartIcon

@ExperimentalMaterialApi
@Composable
fun IdleTimer(totalSeconds: Long = 180L, ) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Chrono(
            seconds = totalSeconds,
            totalSeconds = totalSeconds,
        ){
            StartIcon(modifier = Modifier
                .size(100.dp)
                .padding(32.dp)
            )
        }
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