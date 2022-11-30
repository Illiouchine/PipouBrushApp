package com.illiouchine.toothbrush.ui.composable.brushtimer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            Icon(painterResource(id = R.drawable.ic_achievement_star_2), "")
        }
        Text(text = "Bravo !")
    }
}



@Preview
@Composable
fun RestartContentBrushScreenLight() {
    ToothBrushTheme {
        FinishedTimer()
    }
}



@Preview
@Composable
fun RestartContentBrushScreenDark() {
    ToothBrushTheme(darkTheme = true) {
        FinishedTimer()
    }
}