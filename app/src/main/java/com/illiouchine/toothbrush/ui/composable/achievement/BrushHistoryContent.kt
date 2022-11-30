package com.illiouchine.toothbrush.ui.composable.achievement

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BrushHistoryContent(
    brushHistory: List<String>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        brushHistory.forEach { date ->
            item {
                Text(
                    text = date
                )
            }
        }
    }
}