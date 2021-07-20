package com.illiouchine.toothbrush.feature.brushing.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.illiouchine.toothbrush.feature.brushing.ui.composable.CountDownContent
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import kotlin.time.ExperimentalTime

@ExperimentalMaterialApi
@Preview
@Composable
@ExperimentalTime
fun CountDownContentBrushScreenLight() {
    ToothBrushTheme {
        CountDownContent()
    }
}


@ExperimentalMaterialApi
@Preview
@Composable
@ExperimentalTime
fun CountDownContentBrushScreenDark() {
    ToothBrushTheme (darkTheme = true) {
        CountDownContent()
    }
}