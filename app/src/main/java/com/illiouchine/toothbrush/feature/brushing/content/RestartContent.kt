package com.illiouchine.toothbrush.feature.brushing.content

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
        MyButton(text = "Restart !") {
            onRestartClick()
        }
    }
}


@Preview
@Composable
fun RestartContentBrushScreenLight() {
    ToothBrushTheme {
        RestartContent()
    }
}


@Preview
@Composable
fun RestartContentBrushScreenDark() {
    ToothBrushTheme(darkTheme = true) {
        RestartContent()
    }
}