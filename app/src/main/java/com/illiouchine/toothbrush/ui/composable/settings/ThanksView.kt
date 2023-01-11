package com.illiouchine.toothbrush.ui.composable.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.R
import com.illiouchine.toothbrush.ui.utils.InstagramIcon

@Preview
@Composable
fun ThanksView(
    onKrogogoClicked: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.settings_thanks_krog),
            modifier = Modifier
                .padding(8.dp),
            style = MaterialTheme.typography.bodyMedium,
        )
        InstagramIcon(
            modifier = Modifier.size(44.dp),
            onClick = { onKrogogoClicked() },
            clickLabel = stringResource(R.string.settings_thanks_krog_action)
        )
    }
}