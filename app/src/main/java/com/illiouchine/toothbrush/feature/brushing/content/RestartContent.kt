package com.illiouchine.toothbrush.feature.brushing.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import com.illiouchine.toothbrush.ui.composable.MyButton
import kotlin.time.ExperimentalTime


@ExperimentalTime
@Composable
fun RestartContent(
    onRestartClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bravo !")
        Spacer(Modifier)
        //VideoExoPlayer()
        MyButton(text = "Restart !") {
            onRestartClick()
        }
    }
}


@Preview
@Composable
@ExperimentalTime
fun RestartContentBrushScreenLight() {
    ToothBrushTheme {
        RestartContent()
    }
}


@Preview
@Composable
@ExperimentalTime
fun RestartContentBrushScreenDark() {
    ToothBrushTheme(darkTheme = true) {
        RestartContent()
    }
}