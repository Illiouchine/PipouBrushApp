package com.illiouchine.toothbrush.ui.composable.achievement

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import java.util.*

@Preview
@Composable
fun BrushHistoryContent(
    brushHistory: List<Pair<Date, Int>> = listOf(Date() to 3)
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        brushHistory.forEach { element ->
            item {
                Text(
                    text = element.first.toString()
                )
                Text(text = element.second.toString())
            }
        }
    }
}