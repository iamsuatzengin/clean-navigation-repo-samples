package com.suatzengin.cleannavigation.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerLayout(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.LightGray,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(color = backgroundColor, shape = RoundedCornerShape(10.dp))
                .shimmerAnimation()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth().shimmerAnimation()
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(color = backgroundColor, shape = CircleShape)
                    .shimmerAnimation(shape = CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = "Suat Zengin | Hello Shimmer",
                    modifier = Modifier
                        .fillMaxWidth()
                        /*.height(24.dp)*/
                        .background(color = backgroundColor, shape = RoundedCornerShape(10.dp))
                        .shimmerAnimation()
                )

                Spacer(modifier = Modifier.height(4.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .background(color = backgroundColor, shape = RoundedCornerShape(10.dp))
                        .shimmerAnimation()
                )
            }


        }


    }
}

@Composable
fun Modifier.shimmerAnimation(
    shape: Shape = RoundedCornerShape(10.dp),
    widthOfShadowBrush: Int = 500,
    durationMillis: Int = 1000,
): Modifier {
    val shimmerColors = listOf(
        Color.White.copy(alpha = 0.3f),
        Color.White.copy(alpha = 0.5f),
        Color.White.copy(alpha = 1.0f),
        Color.White.copy(alpha = 0.5f),
        Color.White.copy(alpha = 0.3f),
    )

    val transition = rememberInfiniteTransition(label = "shimmer transition")

    val t = transition.animateFloat(
        label = "shimmer float anim",
        initialValue = 0f,
        targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    return this then background(
        brush = Brush.linearGradient(
            colors = shimmerColors,
            start = Offset(t.value - widthOfShadowBrush, 0f),
            end = Offset(t.value, 270f)
        ),
        shape = shape
    )
}

@Preview(showBackground = true)
@Composable
fun ShimmerLayoutPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ShimmerLayout()
    }
}