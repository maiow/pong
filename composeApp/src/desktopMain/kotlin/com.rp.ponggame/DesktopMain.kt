package com.rp.ponggame

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.foundation.layout.BoxWithConstraints

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Pong Game") {
        BoxWithConstraints {
            PongGame(
                screenWidth = constraints.maxWidth.toFloat(),
                screenHeight = constraints.maxHeight.toFloat()
            )
        }
    }
}
