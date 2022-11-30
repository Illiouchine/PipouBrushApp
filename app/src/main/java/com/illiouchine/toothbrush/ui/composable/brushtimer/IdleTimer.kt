package com.illiouchine.toothbrush.ui.composable.brushtimer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import com.illiouchine.toothbrush.ui.composable.brushtimer.chrono.Chrono
import com.illiouchine.toothbrush.ui.composable.brushtimer.chrono.StartIcon

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
        Text(
            text = "Click to start !",
            color = MaterialTheme.colorScheme.secondary
        )
    }
}



@Preview
@Composable
fun WaitingStartBrushScreenLight() {
    ToothBrushTheme {
        IdleTimer()
    }
}