package com.rp.ponggame

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    BoxWithConstraints {
        PongGame(
            screenWidth = constraints.maxWidth.toFloat(),
            screenHeight = constraints.maxHeight.toFloat()
        )
    }
}
