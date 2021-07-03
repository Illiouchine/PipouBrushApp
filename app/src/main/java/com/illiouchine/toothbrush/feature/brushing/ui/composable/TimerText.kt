package com.illiouchine.toothbrush.feature.brushing.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.feature.brushing.brushDuration
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Composable
fun TimerText(
    durationInSeconds: Double = brushDuration.toDouble(DurationUnit.SECONDS)
) {
    Surface {
        Text(
            text = durationInSeconds.toTimerText(),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@ExperimentalTime
@Preview(name = "Dark Time")
@Composable
fun DarkTimer() {
    ToothBrushTheme(darkTheme = true) {
        TimerText(330.3)
    }
}

@ExperimentalTime
@Preview(name = "Light Time")
@Composable
fun LightTimer() {
    ToothBrushTheme {
        TimerText(423.0)
    }
}

private fun Double.toTimerText(): String {
    val minuteCount = (this / 60).toInt()
    val secondCount = (this % 60).toInt()

    val minuteString = if (minuteCount < 10) {
        "0$minuteCount"
    } else {
        "$minuteCount"
    }
    val secondString = if (secondCount < 10) {
        "0$secondCount"
    } else {
        "$secondCount"
    }
    return "$minuteString:$secondString"
}