package com.illiouchine.toothbrush.ui.composable.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.R
import java.text.DateFormat
import java.util.*

@Preview
@Composable
fun HistoryRow(
    brushHistory: List<HistoryState.History> = previewDataHistoryList()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(brushHistory.sortedBy { it.date }){ element ->
            Row(
                modifier = Modifier
                    .height(32.dp)
                    .fillMaxWidth(),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = element.date.toFormattedDate(),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
                ToothStar(
                    modifier = Modifier
                        .height(32.dp)
                        .padding(8.dp),
                    value = element.brushCount
                )
            }
        }
    }
}

@Preview
@Composable
fun ToothStar(
    modifier: Modifier = Modifier,
    value: Int = 0,
) {
    Row(modifier = modifier) {
        Icon(
            painter = painterResource(id = R.drawable.ic_tooth_1),
            contentDescription = "Tooth",
            tint = value.colorIfActive(1)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_tooth_1),
            contentDescription = "Tooth",
            tint = value.colorIfActive(2)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
                painter = painterResource(id = R.drawable.ic_tooth_1),
        contentDescription = "Tooth",
        tint = value.colorIfActive(3)
        )
    }
}

@Composable
private fun Int.colorIfActive(value:Int): Color {
    return if (this >= value ) {
        MaterialTheme.colorScheme.secondary
    } else {
        MaterialTheme.colorScheme.secondaryContainer
    }
}


private fun Date.toFormattedDate(): String {
    return DateFormat.getDateInstance().format(this)
}