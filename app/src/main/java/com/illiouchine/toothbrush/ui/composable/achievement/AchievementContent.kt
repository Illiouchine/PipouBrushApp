package com.illiouchine.toothbrush.ui.composable.achievement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AchievementContent() {
    Column() {
        Text(
            text = "Achievement",
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "List des achievements en construction",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}