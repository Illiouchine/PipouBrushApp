package com.illiouchine.toothbrush.ui.composable.settings

import android.text.format.DateUtils
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.R
import kotlin.time.Duration.Companion.seconds
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlin.time.Duration

@Preview
@Composable
fun CountDownSettingsView(
    countDownState: CountDownState = CountDownState.Loading,
    onCountDownDurationChanged : (Duration) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Icon(painterResource(id = R.drawable.ic_timer), "")
            Text(text = "Brush Duration", modifier = Modifier.align(Alignment.CenterVertically))
        }
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            when(countDownState){
                is CountDownState.Loaded -> {
                    var durationInSeconds by remember { mutableStateOf(countDownState.duration.inWholeSeconds.toFloat()) }

                    Text(text = DateUtils.formatElapsedTime(durationInSeconds.toLong()))

                    Slider(
                        value = durationInSeconds,
                        onValueChange = { newPosition -> durationInSeconds = newPosition },
                        valueRange = 60f..360f,
                        steps = 9,
                        onValueChangeFinished = { onCountDownDurationChanged(durationInSeconds.toLong().seconds) }
                    )
                }
                CountDownState.Loading -> {
                    CircularProgressIndicator()
                }
            }
        }

    }

}

@Preview
@Composable
fun CountDownSettingsViewLoaded(
    countDownState: CountDownState = CountDownState.Loading
) {
    CountDownSettingsView(
        countDownState = CountDownState.Loaded(
            duration = 160.seconds
        )
    )
}