package com.illiouchine.toothbrush.ui.composable.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.R

@Preview
@Composable
fun HistoryContent(
    modifier: Modifier = Modifier,
    historyState: HistoryState = HistoryState.Loaded(data = previewDataHistoryList())
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.semantics { heading() },
            text = stringResource(R.string.history_title),
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(2.dp))

        when (historyState) {
            HistoryState.Error -> {
                Text(stringResource(R.string.history_error_description))
            }
            is HistoryState.Loaded -> {
                HistoryRow(
                    brushHistory = historyState.data
                )
            }
            HistoryState.Loading -> {
                Text(stringResource(R.string.history_loading_description))
            }
        }
    }
}