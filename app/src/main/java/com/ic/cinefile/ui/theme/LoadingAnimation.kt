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
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val circles = (0..2).map {
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, delayMillis = it * 200, easing = LinearEasing)
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
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

@Preview(showBackground = true)
@Composable
fun LoadingAnimationPreview() {
    LoadingAnimation()
}
