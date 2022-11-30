package com.illiouchine.toothbrush.ui.composable.brushtimer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.illiouchine.toothbrush.R
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import com.illiouchine.toothbrush.ui.composable.brushtimer.chrono.Chrono


@Composable
fun FinishedTimer() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Chrono(
            seconds = 180,
            totalSeconds = 180,
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_achievement_star_2),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        Text(text = "Bravo !", color = MaterialTheme.colorScheme.secondary)
    }
}



@Preview
@Composable
fun RestartContentBrushScreenLight() {
    ToothBrushTheme {
        FinishedTimer()
    }
}