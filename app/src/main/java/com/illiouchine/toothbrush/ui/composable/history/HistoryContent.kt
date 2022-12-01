package com.illiouchine.toothbrush.ui.composable.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun HistoryContent(
    historyState: HistoryState = HistoryState.Loaded(data = listOf(Date() to 1))
) {
    Column {
        Text(
            text = "Historique",
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(2.dp))

        when (historyState) {
            HistoryState.Error -> {
                Text("error")
            }
            is HistoryState.Loaded -> {
                HistoryRow(
                    brushHistory = historyState.data
                )
            }
            HistoryState.Loading -> {
                Text("loading")
            }
        }
    }
}