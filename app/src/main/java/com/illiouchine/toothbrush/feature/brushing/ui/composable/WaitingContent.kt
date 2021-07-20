package com.illiouchine.toothbrush.feature.brushing.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.feature.brushing.brushDuration
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import com.illiouchine.toothbrush.feature.main.composable.MyButton
import com.illiouchine.toothbrush.ui.composable.TimerText
import com.illiouchine.toothbrush.ui.composable.chrono.Chrono
import java.util.concurrent.TimeUnit
import kotlin.time.ExperimentalTime

@ExperimentalMaterialApi
@ExperimentalTime
@Composable
fun WaitingContent(
    onStartTimerClick: ()-> Unit = {}
){
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
                seconds = brushDuration.toInt(TimeUnit.SECONDS),
                totalSeconds = brushDuration.toInt(TimeUnit.SECONDS),
                centerColor = MaterialTheme.colors.background,
                backgroundColor = MaterialTheme.colors.background,
            )
            Spacer(Modifier)
            //VideoExoPlayer()
            MyButton(text = "Go !"){
                onStartTimerClick()
            }
            Spacer(Modifier)
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
    ToothBrushTheme (darkTheme = true) {
        WaitingContent()
    }
}