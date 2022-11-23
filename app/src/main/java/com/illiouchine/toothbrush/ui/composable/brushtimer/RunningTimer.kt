package com.illiouchine.toothbrush.ui.composable.brushtimer

import android.text.format.DateUtils
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import com.illiouchine.toothbrush.ui.composable.brushtimer.chrono.Chrono


@ExperimentalMaterialApi
@Composable
fun RunningTimer(
    current: Long = 180L,
    total: Long = 180L,
    textStyle: TextStyle = MaterialTheme.typography.h6,
    textColor: Color = MaterialTheme.colors.primary,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Chrono(
            seconds = current,
            totalSeconds = total,
        ){
            Text(
                text = DateUtils.formatElapsedTime(current),
                style = textStyle,
                color = textColor,
                modifier = Modifier
            )
        }
        Text(text = "Brush your teeth !")
    }
}


@ExperimentalMaterialApi
@Preview
@Composable
fun CountDownContentBrushScreenLight() {
    ToothBrushTheme {
        RunningTimer()
    }
}


@ExperimentalMaterialApi
@Preview
@Composable
fun CountDownContentBrushScreenDark() {
    ToothBrushTheme(darkTheme = true) {
        RunningTimer()
    }
}
