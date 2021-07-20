package com.illiouchine.toothbrush.ui.composable.chrono

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.ui.ToothBrushTheme


@Composable
fun ChronoText(
    modifier: Modifier = Modifier,
    textColor: Color = Color.DarkGray,
    textStyle: TextStyle = MaterialTheme.typography.h6,
    seconds: Int = 120,
) {
    var scaledTextStyle by remember { mutableStateOf(textStyle) }
    var readyToDraw by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        Text(
            text = "${seconds}s",
            style = scaledTextStyle,
            color = textColor,
            softWrap = false,
            modifier = Modifier
                .drawWithContent {
                    if (readyToDraw) {
                        drawContent()
                    }
                },
            onTextLayout = { textLayoutResult ->
                if (textLayoutResult.didOverflowWidth || textLayoutResult.didOverflowHeight) {
                    scaledTextStyle =
                        scaledTextStyle.copy(fontSize = scaledTextStyle.fontSize * 0.9)
                } else {
                    readyToDraw = true
                }
            },
            overflow = TextOverflow.Visible
        )
    }
}

@Preview
@Composable
fun ChronoTextPreview() {
    ToothBrushTheme() {
        Box(
            modifier = Modifier.size(500.dp)
        ) {
            ChronoText(seconds = 75)
        }
    }
}
