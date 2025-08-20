package com.rp.ponggame

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.math.max
import kotlin.math.min

class PongGameState(
    val screenWidth: Float,
    val screenHeight: Float,
) {
    var paddleWidth by mutableStateOf(220f)
    var paddleHeight by mutableStateOf(24f)
    var paddleX by mutableStateOf((screenWidth - paddleWidth) / 2f)
    val paddleY = screenHeight - paddleHeight - 40f

    var ballX by mutableStateOf(screenWidth / 2f)
    var ballY by mutableStateOf(screenHeight / 2f)
    var ballRadius by mutableStateOf(32f)
    var ballVX by mutableStateOf(8f)
    var ballVY by mutableStateOf(-8f)

    var score by mutableStateOf(0)

    fun movePaddle(centerX: Float) {
        paddleX = centerX - paddleWidth / 2f
        paddleX = max(0f, min(paddleX, screenWidth - paddleWidth))
    }

    fun updateGame() {
        ballX += ballVX
        ballY += ballVY

        // Wall collision
        if (ballX - ballRadius < 0) {
            ballX = ballRadius
            ballVX = -ballVX
        }
        if (ballX + ballRadius > screenWidth) {
            ballX = screenWidth - ballRadius
            ballVX = -ballVX
        }
        if (ballY - ballRadius < 0) {
            ballY = ballRadius
            ballVY = -ballVY
        }

        // Paddle collision
        if (ballY + ballRadius >= paddleY &&
            ballX >= paddleX && ballX <= paddleX + paddleWidth &&
            ballY + ballRadius <= paddleY + paddleHeight + 32f
        ) {
            ballY = paddleY - ballRadius
            ballVY = -ballVY
            score++
        }

        if (ballY + ballRadius > screenHeight) {
            resetGame()
        }
    }

    fun resetGame() {
        paddleX = (screenWidth - paddleWidth) / 2f
        ballX = screenWidth / 2f
        ballY = screenHeight / 2f
        ballVX = 8f
        ballVY = -8f
        score = 0
    }
}
