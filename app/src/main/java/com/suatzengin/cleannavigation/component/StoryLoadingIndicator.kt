package com.suatzengin.cleannavigation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StoryLoadingIndicator(
    numberOfStory: Int,
    modifier: Modifier = Modifier,
    progress: Float = 0f,
    gap: Dp = StoryLoadingIndicatorDefaults.IndicatorGap,
    strokeWidth: Dp = StoryLoadingIndicatorDefaults.IndicatorStrokeWidth,
    color: Color = Color.White,
    backgroundColor: Color = Color.Gray
) {
    Canvas(modifier = modifier.fillMaxWidth()) {

        drawSegments(1f, backgroundColor, strokeWidth.toPx(), numberOfStory, gap.toPx())
        drawSegments(progress, color, strokeWidth.toPx(), numberOfStory, gap.toPx())
    }
}

object StoryLoadingIndicatorDefaults {
    val IndicatorGap = 8.dp

    val IndicatorStrokeWidth = 6.dp
}

private fun DrawScope.drawSegments(
    progress: Float,
    color: Color,
    strokeWidth: Float,
    segments: Int,
    segmentGap: Float,
) {
    val width = size.width
    val start = 0f
    val gaps = (segments - 1) * segmentGap
    val segmentWidth = (width - gaps) / segments
    val barsWidth = segmentWidth * segments
    val end = barsWidth * progress + (progress * segments).toInt()* segmentGap

    repeat(segments) { index ->
        val offset = index * (segmentWidth + segmentGap)
        if (offset < end) {
            val barEnd = (offset + segmentWidth).coerceAtMost(end)
            drawLine(
                color,
                Offset(start + offset, 0f),
                Offset(barEnd, 0f),
                strokeWidth,
                cap = StrokeCap.Round
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun StoryLoadingIndicatorPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        StoryLoadingIndicator(numberOfStory = 3, progress = .5f)
    }
}