package com.example.searchcinema.ui.presintation.shared.discover

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.ui.presintashion.feature.discover.model.Film
import com.example.searchcinema.R
import com.example.searchcinema.ui.presintation.theme.Colors
import com.example.searchcinema.ui.presintation.theme.SCTypography
import com.example.searchcinema.ui.presintation.theme.spacers

@Composable
fun Cinema(
    film: Film,
    onClick: () -> Unit,
    height: Dp = 185.dp,
    width: Dp = 154.dp,
    style: TextStyle = SCTypography.bodyMedium,
) {
    val parts = film.releaseDate.split("-")

    CinemaItem(
        cinema = film.title,
        date = parts[0],
        image = film.pictureLink,
        onClick = onClick,
        width = width,
        height = height,
        style = style
    )
}

@Composable
fun CinemaItem(
    cinema: String,
    image: String,
    height: Dp,
    width: Dp,
    style: TextStyle,
    onClick: () -> Unit,
    date: String
) {
    val gradient = Brush.linearGradient(
        listOf(Color.Black.copy(0f), Color.Black.copy(0.3f))
    )

    Column(
        modifier = Modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .height(height = height)
                .width(width = width)
                .background(gradient, RoundedCornerShape(20.dp))
        ) {
            AsyncImage(
                model = image,
                contentDescription = null,
                error = painterResource(R.drawable.placeholder),
                placeholder = painterResource(R.drawable.placeholder),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(20.dp))
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.small))
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = Colors.white)) {
                    append(cinema)
                }
                append(" ")
                withStyle(SpanStyle(color = Colors.gray)){
                append("($date)")
                }
            },
            color = Color.White,
            style = style,
            maxLines = 3,
            modifier = Modifier.width(width)
        )
    }
}