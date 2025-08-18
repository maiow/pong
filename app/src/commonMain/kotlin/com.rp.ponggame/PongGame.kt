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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ponggame.app.generated.resources.Res
import ponggame.app.generated.resources.score_text

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PongGame(
    screenWidth: Float = 400f,
    screenHeight: Float = 700f
) {

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
                .background(PongDarkColorScheme.background)
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
                //paddle
                drawRoundRect(
                    color = PongDarkColorScheme.secondary,
                    topLeft = Offset(state.paddleX, state.paddleY),
                    size = Size(state.paddleWidth, state.paddleHeight),
                    cornerRadius = CornerRadius(12f, 12f)
                )
                //ball
                drawCircle(
                    color = PongDarkColorScheme.tertiary,
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

@Composable
@Preview
fun PongGamePreview() {
    PongGame()
}
