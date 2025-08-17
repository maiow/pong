package com.rp.ponggame

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PongDarkColorScheme = darkColorScheme(
    background = Color(0xFF222831),
    surface = Color(0xFF222831),
    primary = Color(0xFF00ADB5),
    onPrimary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun PongTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PongDarkColorScheme,
        content = content
    )
}
