package com.example.searchcinema.ui.presintation.shared.discover

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.searchcinema.R
import com.example.searchcinema.ui.presintation.theme.Colors
import com.example.searchcinema.ui.presintation.theme.spacers
import kotlin.math.abs

@Preview
@Composable
private fun CinemaItemPreview() {
    Roulette(
        cinema = "Ghbdnt",
        clickCinema = {},
        isSelected = false,
        offset = 0.1f,
        startOffset = 1f,
        endOffset = 0.1f
    )
}

@Composable
fun Roulette(
    cinema: String,
    isSelected: Boolean,
    clickCinema: () -> Unit,
    offset: Float,
    startOffset: Float,
    endOffset: Float
) {
    RouletteItem(
        cinema = cinema,
        isSelected = isSelected,
        clickCinema = clickCinema,
        offset = offset,
        startOffset = startOffset,
        endOffset = endOffset
    )
}

@Composable
fun RouletteItem(
    cinema: String,
    isSelected: Boolean,
    clickCinema: () -> Unit,
    offset: Float,
    startOffset: Float,
    endOffset: Float
) {
    val gradient = Brush.linearGradient(
        listOf(Colors.veryLightRed, Colors.lightRed),
        start = Offset(0F, 100F),
        end = Offset(40F, 0F)
    )

    val scale by remember(offset, startOffset, endOffset) {
        derivedStateOf {
            val scaleFactor = when {
                startOffset > 0 -> 1f - startOffset * 0.3f
                endOffset < 0 -> 1f + endOffset * 0.3f
                else -> 1f
            }
            scaleFactor.coerceIn(0.7f, 1.3f)
        }
    }

    val alpha by remember(offset) {
        derivedStateOf {
            0.6f + (1f - abs(offset) * 0.4f).coerceIn(0f, 0.4f)
        }
    }

    val color = if (isSelected)
            gradient
        else
        SolidColor(Colors.white)

    Column(
        modifier = Modifier
            .clickable(onClick = clickCinema)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                this.alpha = alpha
            }
    ) {
        Text(
            text = cinema,
            style = TextStyle(
                brush = color,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily(Font(R.font.lato_regular))
            ),
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.xSmall))
        Box(
            modifier = Modifier
                .border(2.dp, color, RoundedCornerShape(2.dp))
                .width(19.dp)
                .height(1.dp)
        )
    }
}
