package com.illiouchine.toothbrush.ui.composable.brushtimer

import android.text.format.DateUtils
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import com.illiouchine.toothbrush.ui.composable.brushtimer.chrono.Chrono


@Composable
fun RunningTimer(
    current: Long = 180L,
    total: Long = 180L,
    textStyle: TextStyle = MaterialTheme.typography.headlineMedium,
    textColor: Color = MaterialTheme.colorScheme.primary,
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



@Preview
@Composable
fun CountDownContentBrushScreenLight() {
    ToothBrushTheme {
        RunningTimer()
    }
}



@Preview
@Composable
fun CountDownContentBrushScreenDark() {
    ToothBrushTheme(darkTheme = true) {
        RunningTimer()
    }
}
