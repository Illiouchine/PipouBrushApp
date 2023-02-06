package com.illiouchine.toothbrush.ui.composable.brushtimer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.illiouchine.toothbrush.R
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import com.illiouchine.toothbrush.ui.composable.brushtimer.chrono.Chrono
import com.illiouchine.toothbrush.ui.composable.brushtimer.chrono.StartIcon

@Composable
fun IdleTimer(totalSeconds: Long = 180L) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Chrono(
            modifier = Modifier.weight(1f),
            seconds = totalSeconds,
            totalSeconds = totalSeconds,
        ) {
            StartIcon(
                modifier = Modifier.size(
                    width = this.maxWidth / 2,
                    height = this.maxHeight / 2
                )
            )
        }
        val countDownAccessibility = stringResource(R.string.brush_timer_accessibility_label)
        Text(
            modifier = Modifier.semantics {
                contentDescription = countDownAccessibility
            },
            text = stringResource(R.string.brush_start_description),
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(heightDp = 100)
@Composable
fun WaitingStartBrushScreenLight() {
    ToothBrushTheme {
        IdleTimer()
    }
}