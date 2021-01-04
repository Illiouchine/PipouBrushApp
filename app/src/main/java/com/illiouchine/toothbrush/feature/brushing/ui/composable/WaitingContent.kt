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
import com.illiouchine.toothbrush.feature.main.composable.MyButton
import kotlin.time.ExperimentalTime

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
            TimerText()
            Spacer(Modifier)
            //VideoExoPlayer()
            MyButton(text = "Go !"){
                onStartTimerClick()
            }
            Spacer(Modifier)
        }

    }
}


@Preview
@Composable
@ExperimentalTime
fun WaitingStartBrushScreenLight() {
    ToothBrushTheme {
        WaitingContent()
    }
}


@Preview
@Composable
@ExperimentalTime
fun WaitingStartBrushScreenDark() {
    ToothBrushTheme (darkTheme = true) {
        WaitingContent()
    }
}