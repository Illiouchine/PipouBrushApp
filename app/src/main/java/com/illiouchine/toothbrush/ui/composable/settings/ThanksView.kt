package com.illiouchine.toothbrush.ui.composable.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.R

@Preview
@Composable
fun ThanksView() {
    Text(text = stringResource(R.string.settings_thanks_krog),
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.bodyMedium,
    )
}