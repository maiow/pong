package com.rp.ponggame

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PongDarkColorScheme = darkColorScheme(
    background = Color(0xFF3B3932),
    surface = Color(0xFF3B3932),//Color(0xFF222831),
    primary = Color(0xFF00ADB5),
    onPrimary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    secondary = Color(0xFF903719),//Color(0xFF00ADB5)
    tertiary = Color(0xFFFF8100)//Color(0xFFFF5722)
)

@Composable
fun PongTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PongDarkColorScheme,
        content = content
    )
}
