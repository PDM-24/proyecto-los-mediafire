package com.ic.cinefile.ui.theme

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun LoadingAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val circles = (0..2).map { // Cambié a tres círculos
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, delayMillis = it * 200, easing = LinearEasing) // Mueve los círculos uno después del otro
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), // Fondo negro
        contentAlignment = Alignment.Center // Centra la animación en la pantalla
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            circles.forEach { animatedFloat ->
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .graphicsLayer {
                            translationY = 30f * animatedFloat.value
                        }
                        .background(Color.White, shape = CircleShape)
                )
            }
        }
    }
}
