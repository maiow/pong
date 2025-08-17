package com.rp.ponggame

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import ponggame.app.generated.resources.Res
import ponggame.app.generated.resources.score_text

@OptIn(org.jetbrains.compose.resources.ExperimentalResourceApi::class)
@Composable
fun PongGame(
    screenWidth: Float = 400f,
    screenHeight: Float = 700f
) {
    val backgroundColor = Color(0xFF222831)
    val paddleColor = Color(0xFF00ADB5)
    val ballColor = Color(0xFFFF5722)

    var state by remember { mutableStateOf(PongGameState(screenWidth, screenHeight)) }
    var lastPaddleCenter by remember { mutableStateOf(screenWidth / 2f) }

    LaunchedEffect(Unit) {
        while (true) {
            state.updateGame()
            delay(16)
        }
    }

    PongTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            val pos = event.changes.firstOrNull()?.position
                            if (pos != null) {
                                lastPaddleCenter = pos.x
                                state.movePaddle(lastPaddleCenter)
                            }
                        }
                    }
                }
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRect(
                    color = paddleColor,
                    topLeft = Offset(state.paddleX, state.paddleY),
                    size = androidx.compose.ui.geometry.Size(state.paddleWidth, state.paddleHeight)
                )
                drawCircle(
                    color = ballColor,
                    radius = state.ballRadius,
                    center = Offset(state.ballX, state.ballY)
                )
            }
            Text(
                text = stringResource(Res.string.score_text, state.score),
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 24.dp)
            )

        }
    }
}
