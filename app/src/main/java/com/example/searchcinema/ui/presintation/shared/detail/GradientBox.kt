package com.example.searchcinema.ui.presintation.shared.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.searchcinema.ui.presintation.theme.Colors
import com.example.searchcinema.ui.presintation.theme.SCTypography

@Composable
fun GradientBox(
    text: String,
    modifier: Modifier = Modifier,
    shape: Shape
) {
    val gradient = Brush.linearGradient(
        listOf(Color.White.copy(0.25f), Color.White.copy(0f))
    )

    Box(
        modifier = Modifier
            .border(width = 1.dp, brush = gradient, shape = shape),
    ){
        Text(
            text = text,
            color = Colors.gray,
            style = SCTypography.titleMedium,
            modifier = modifier
        )
    }
}