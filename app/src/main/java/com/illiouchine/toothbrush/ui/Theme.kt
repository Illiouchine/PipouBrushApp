package com.illiouchine.toothbrush.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ToothBrushTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) =
    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        typography = typography,
        shapes = shapes,
        content = content
    )

@Preview(name = "Dark Theme")
@Composable
fun DarkTheme() {
    ToothBrushTheme(darkTheme = true) {
        Surface {
            TextButton(
                onClick = {}) {
                Text("Dark Theme")
            }
        }
    }
}


@Preview(name = "Light Theme")
@Composable
fun LightTheme() {
    ToothBrushTheme {
        Surface {
            TextButton(
                onClick = {}) {
                Text("Light Theme")
            }
        }
    }
}