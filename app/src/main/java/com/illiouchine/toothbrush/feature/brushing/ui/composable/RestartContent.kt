package com.illiouchine.toothbrush.feature.brushing.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
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
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier)
            Text("Bravo !")
            Spacer(Modifier)
            //VideoExoPlayer()
            MyButton(text = "Restart !") {
                onRestartClick()
            }
            Spacer(Modifier)
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