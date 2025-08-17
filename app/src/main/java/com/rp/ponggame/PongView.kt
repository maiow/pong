package com.rp.ponggame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.graphics.toColorInt
import kotlin.math.max
import kotlin.math.min

class PongView(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val bgPaint = Paint().apply { color = "#222831".toColorInt() }
    private val paddlePaint = Paint().apply { color = "#00adb5".toColorInt() }
    private val ballPaint = Paint().apply { color = "#ff5722".toColorInt() }

    private var paddleWidth = 300f
    private var paddleHeight = 30f
    private var paddleX = 0f
    private var paddleY = 0f

    private var ballX = 0f
    private var ballY = 0f
    private var ballRadius = 32f
    private var ballVX = 8f
    private var ballVY = -8f

    private var score = 0
    private var scoreListener: ((Int) -> Unit)? = null

    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval = 16L // ~60 FPS

    init {
        setBackgroundColor("#222831".toColorInt())
        post {
            resetGame()
            startGameLoop()
        }
    }

    fun setScoreListener(listener: (Int) -> Unit) {
        scoreListener = listener
        scoreListener?.invoke(score)
    }

    private fun resetGame() {
        paddleX = (width - paddleWidth) / 2f
        paddleY = height - paddleHeight - 40f
        ballX = width / 2f
        ballY = height / 2f
        ballVX = 8f
        ballVY = -8f
        score = 0
        scoreListener?.invoke(score)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), bgPaint)
        canvas.drawRect(
            paddleX,
            paddleY,
            paddleX + paddleWidth,
            paddleY + paddleHeight,
            paddlePaint
        )
        canvas.drawCircle(ballX, ballY, ballRadius, ballPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_MOVE || event.action == MotionEvent.ACTION_DOWN) {
            paddleX = event.x - paddleWidth / 2f
            paddleX = max(0f, min(paddleX, width - paddleWidth))
            invalidate()
        }
        return true
    }

    private fun startGameLoop() {
        handler.postDelayed({
            updateGame()
            invalidate()
            startGameLoop()
        }, updateInterval)
    }

    private fun updateGame() {
        ballX += ballVX
        ballY += ballVY

        if (ballX - ballRadius < 0) {
            ballX = ballRadius
            ballVX = -ballVX
        }
        if (ballX + ballRadius > width) {
            ballX = width - ballRadius
            ballVX = -ballVX
        }
        if (ballY - ballRadius < 0) {
            ballY = ballRadius
            ballVY = -ballVY
        }

        if (ballY + ballRadius >= paddleY &&
            ballX >= paddleX && ballX <= paddleX + paddleWidth &&
            ballY + ballRadius <= paddleY + paddleHeight + 32f
        ) {
            ballY = paddleY - ballRadius
            ballVY = -ballVY
            score++
            scoreListener?.invoke(score)
        }

        if (ballY + ballRadius > height) {
            resetGame()
        }
    }
}
