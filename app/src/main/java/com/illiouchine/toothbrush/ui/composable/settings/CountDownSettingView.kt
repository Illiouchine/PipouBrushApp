package com.illiouchine.toothbrush.ui.composable.settings

import android.text.format.DateUtils
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.R
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@Preview
@Composable
fun CountDownSettingsView(
    countDownState: CountDownState = CountDownState.Loading,
    onCountDownDurationChanged : (Duration) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Icon(painterResource(id = R.drawable.ic_timer), "")
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = stringResource(R.string.settings_brush_duration_title), modifier = Modifier.align(Alignment.CenterVertically))
        }
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            when(countDownState){
                is CountDownState.Loaded -> {
                    var durationInSeconds by remember { mutableStateOf(countDownState.duration.inWholeSeconds.toFloat()) }

                    Text(text = DateUtils.formatElapsedTime(durationInSeconds.toLong()))
                    Spacer(modifier = Modifier.width(16.dp))
                    Slider(
                        value = durationInSeconds,
                        onValueChange = { newPosition -> durationInSeconds = newPosition },
                        valueRange = 60f..360f,
                        steps = 9,
                        onValueChangeFinished = { onCountDownDurationChanged(durationInSeconds.toLong().seconds) }
                    )
                }
                CountDownState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                    )
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