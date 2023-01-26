package com.illiouchine.toothbrush.ui.utils

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.typography

@Composable
fun AutoSizeText(
    modifier: Modifier = Modifier,
    text: String = "",
    defaultTextStyle: TextStyle = typography.bodyMedium
) {
    var textStyle by remember { mutableStateOf(defaultTextStyle) }
    var readyToDraw by remember { mutableStateOf(false) }
    Text(
        text = text,
        style = textStyle,
        maxLines = 1,
        softWrap = false,
        onTextLayout = { textLayoutResult ->
            if (textLayoutResult.didOverflowWidth && !readyToDraw) {
                textStyle = textStyle.copy(fontSize = textStyle.fontSize * 0.9)
            } else {
                readyToDraw = true
            }
        },
        modifier = modifier.drawWithContent {
            if (readyToDraw) drawContent()
        },
    )
}

@Preview
@Composable
private fun AutoSizeTextSmall() {
    AutoSizeText(
        modifier = Modifier.size(100.dp),
        text = "Small"
    )
}

@Preview
@Composable
private fun AutoSizeTextLong() {
    // Seems to doesn't work with the preview
    AutoSizeText(
        modifier = Modifier.size(100.dp),
        text = "Long text that sh"
    )
}