package com.illiouchine.toothbrush.ui.composable.achievement

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.DateFormat
import java.util.*

@Preview
@Composable
fun BrushHistoryContent(
    brushHistory: List<Pair<Date, Int>> = listOf(Date() to 3)
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        brushHistory
            .sortedBy { it.first }.forEach { element ->
            item {
                Row{
                    Text(text = element.first.toFormattedDate())
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = element.second.toString())
                }
            }
        }
    }
}

private fun Date.toFormattedDate(): String {
    return DateFormat.getDateInstance().format(this)
}