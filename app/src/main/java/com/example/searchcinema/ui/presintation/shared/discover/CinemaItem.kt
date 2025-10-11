package com.example.searchcinema.ui.presintation.shared.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
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
    onClick: () -> Unit
) {
    CinemaItem(
        cinema = film.film,
        date = film.year,
        image = film.imageUrl,
        onClick = onClick
    )
}

@Composable
fun CinemaItem(
    cinema: String,
    image: String,
    onClick: () -> Unit,
    date: Int
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
                .heightIn(160.dp, 185.dp)
                .width(154.dp)
                .background(gradient, RoundedCornerShape(20.dp))
        ) {
            AsyncImage(
                model = image,
                contentDescription = null,
                error = painterResource(R.drawable.placeholder),
                placeholder = painterResource(R.drawable.placeholder),
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(RoundedCornerShape(20.dp))
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
            style = SCTypography.bodyMedium
        )
    }
}