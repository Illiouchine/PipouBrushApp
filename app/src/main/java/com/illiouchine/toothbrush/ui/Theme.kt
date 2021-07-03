package com.illiouchine.toothbrush.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

private val LightColorPalette = lightColors(
    primary = Color(0xFF313F8B),
    primaryVariant = Color(0xFF232D70),
    secondary = Color(0xFF80CBC4),
    background = Color(0xFFF1F3D8),
    surface = Color(0xFFEFF1D8),
    error = Color(0xFFD32F2F),
    onPrimary = Color(0xFFF0F1DB),
    onSecondary = Color(0xFF1A237E),
    onBackground = Color(0xFF1A237E),
    onSurface = Color(0xFF1A237E),
    onError = Color(0XffEEEEEE)
)

private val DarkColorPalette = darkColors(
    primary = Color(0xFFFFCDD2),
    primaryVariant = Color(0xFFF8BBD0),
    secondary = Color(0xFF94E9E1),
    background = Color(0xFF2B2C23),
    surface = Color(0xFF23241C),
    error = Color(0xFF521212),
    onPrimary = Color(0xFF454638),
    onSecondary = Color(0xFF2D3EE7),
    onBackground = Color(0xFF2F40EB),
    onSurface = Color(0xFF2F40EB),
    onError = Color(0XffEEEEEE)
)

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
fun DarkTheme(){
    ToothBrushTheme(darkTheme = true){
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
fun LightTheme(){
    ToothBrushTheme{
        Surface {
            TextButton(
                onClick = {}) {
                Text("Light Theme")
            }
        }
    }
}