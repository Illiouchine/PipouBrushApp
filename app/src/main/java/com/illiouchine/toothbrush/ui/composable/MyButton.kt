package com.illiouchine.toothbrush.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.ToothBrushTheme

@Composable
fun MyButton(
    text: String = "TODO : Set Custom Text",
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier.padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { onClick() },
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(
                text = text
            )
        }
    }
}


@Preview(name = "Dark Button")
@Composable
fun DarkButtonPreview() {
    ToothBrushTheme(darkTheme = true) {
        MyButton(
            text = "Dark Button",
            onClick = {}
        )
    }
}


@Preview(name = "Light Button")
@Composable
fun LightButtonPreview() {
    ToothBrushTheme {
        MyButton(
            text = "Light Button",
            onClick = {}
        )
    }
}

