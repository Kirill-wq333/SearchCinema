package com.example.searchcinema.ui.presintation.feature.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.searchcinema.R
import com.example.searchcinema.ui.presintation.shared.detail.GradientBox
import com.example.searchcinema.ui.presintation.theme.Colors
import com.example.searchcinema.ui.presintation.theme.spacers

@Composable
fun DetailScreen(
) {
    Detail(
        image = "",
        nameFilm = "",
        quality = "",
        rating = 8.0,
        duration = 124,
        genre = "",
        releasedDate = "",
        description = ""
    )
}

@Composable
fun Detail(
    image: String,
    nameFilm: String,
    quality: String,
    rating: Number,
    duration: Int,
    genre: String,
    releasedDate: String,
    description: String
) {
    Scaffold(
        topBar = {
        }
    ) { paddingValues ->
        DetailContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            image = image,
            nameFilm = nameFilm,
            quality = quality,
            rating = rating,
            duration = duration,
            genre = genre,
            releasedDate = releasedDate,
            description = description
        )
    }
}

@Preview
@Composable
private fun prev() {
    TopBar()
}

@Composable
fun TopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Colors.backColor)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_left_chevron),
            contentDescription = null,
            tint = Colors.white,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 18.dp)
        )
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    image: String,
    nameFilm: String,
    quality: String,
    rating: Number,
    duration: Int,
    genre: String,
    releasedDate: String,
    description: String
) {
    Column(modifier = modifier) {
        ImageMain(
            image = image
        )
    }
}

@Composable
fun Content() {
    Column(
        modifier = Modifier.padding(
            start = 19.dp,
            end = 24.dp,
            top = 24.dp
        )
    ) {

    }
}

@Composable
fun ImageMain(image: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(235.dp)
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun Genre(
    genre: String
) {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = ""
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.xSmall))

    }
}

@Composable
fun Discover() {

}