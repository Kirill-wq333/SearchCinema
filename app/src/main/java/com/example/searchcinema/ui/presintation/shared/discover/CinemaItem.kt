package com.example.searchcinema.ui.presintation.shared.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.searchcinema.ui.presintation.theme.SCTypography
import com.example.searchcinema.ui.presintation.theme.spacers

@Composable
fun CinemaItem(
    cinema: String
) {
    val gradient = Brush.linearGradient(
        listOf(Color.Black.copy(0f), Color.Black.copy(0.3f))
    )

    Column {
        Box(
            modifier = Modifier
                .widthIn( 160.dp, 184.dp)
                .background(gradient,RoundedCornerShape(20.dp))
        ) {

        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.small))
        Text(
            text = cinema,
            color = Color.White,
            style = SCTypography.bodySmall
        )
    }
}