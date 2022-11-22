package com.illiouchine.toothbrush.feature.brushing.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import com.illiouchine.toothbrush.ui.composable.chrono.Chrono


@ExperimentalMaterialApi
@Composable
fun CountDownContent(
    current: Long = 180L,
    total: Long = 180L
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Chrono(
            modifier = Modifier.size(150.dp),
            seconds = current,
            totalSeconds = total,
            centerColor = MaterialTheme.colors.background,
            backgroundColor = Color.Transparent,
        )
    }
}


@ExperimentalMaterialApi
@Preview
@Composable
fun CountDownContentBrushScreenLight() {
    ToothBrushTheme {
        CountDownContent()
    }
}


@ExperimentalMaterialApi
@Preview
@Composable
fun CountDownContentBrushScreenDark() {
    ToothBrushTheme(darkTheme = true) {
        CountDownContent()
    }
}
