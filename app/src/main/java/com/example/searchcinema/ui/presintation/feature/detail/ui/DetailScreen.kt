package com.example.searchcinema.ui.presintation.feature.detail.ui

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.domain.ui.presintashion.feature.Film
import com.example.searchcinema.R
import com.example.searchcinema.ui.presintation.feature.detail.viewmodel.DetailViewModel
import com.example.searchcinema.ui.presintation.shared.detail.GradientBox
import com.example.searchcinema.ui.presintation.shared.discover.Cinema
import com.example.searchcinema.ui.presintation.theme.Colors
import com.example.searchcinema.ui.presintation.theme.SCTypography
import com.example.searchcinema.ui.presintation.theme.spacers
import java.util.Calendar
import java.util.Locale

private interface DetailScreenCallback{
    fun openRelatedFilm(id: Int) {}
}

@Composable
fun DetailScreen(
    vm: DetailViewModel = hiltViewModel(),
    onBack: () -> Unit = {},
    openRelatedFilm: (Int) -> Unit
) {
    val films by vm.films.collectAsState()
    val film by vm.film.collectAsState()

    val callback = object : DetailScreenCallback {
        override fun openRelatedFilm(id: Int) = openRelatedFilm(id)
    }

    Detail(
        film = film,
        films = films,
        onBack = onBack,
        callback = callback,
        vm = vm
    )
}

@Composable
private fun Detail(
    vm: DetailViewModel,
    film: Film?,
    callback: DetailScreenCallback,
    films: List<Film>,
    onBack: () -> Unit
) {

    Scaffold(
        topBar = {
            TopBar(onBack = onBack)
        }
    ) { paddingValues ->

        film?.let { itemFilm ->

            val parts = itemFilm.releaseDate.split("-")

            DetailContent(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Colors.backColor)
                    .padding(paddingValues),
                films = films,
                callback = callback,
                image = itemFilm.pictureLink,
                nameFilm = itemFilm.title,
                quality = itemFilm.quality,
                rating = itemFilm.rating,
                duration = itemFilm.duration,
                genre = itemFilm.genre,
                description = itemFilm.description,
                year = parts[0],
                month = parts[1].toMonthName(),
                day = parts[2],
            )
        }
    }

}



@Composable
fun TopBar(
    onBack: () -> Unit
) {
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
                .clickable(onClick = onBack)
                .padding(horizontal = 16.dp, vertical = 18.dp)
        )
    }
}

@Composable
private fun DetailContent(
    modifier: Modifier = Modifier,
    films: List<Film>,
    callback: DetailScreenCallback,
    image: String,
    nameFilm: String,
    quality: String,
    rating: Number,
    duration: Int,
    genre: List<String>,
    year: String,
    month: String,
    day: String,
    description: String
) {
    Column(modifier = modifier) {
        ImageMain(
            image = image
        )
        Spacer(modifier = Modifier.height(24.dp))
        Content(
            nameFilm = nameFilm,
            quality = quality,
            rating = rating,
            duration = duration,
            genre = genre,
            films = films,
            callback = callback,
            description = description,
            day = day,
            month = month,
            year = year,
        )
    }
}

@Composable
private fun Content(
    genre: List<String>,
    films: List<Film>,
    callback: DetailScreenCallback,
    year: String,
    nameFilm: String,
    duration: Int,
    quality: String,
    description: String,
    rating: Number,
    day: String,
    month: String
) {
    Column(
        modifier = Modifier.padding(
            start = 19.dp,
            end = 24.dp,
        )
    ) {
        Main(
            nameFilm = nameFilm,
            quality = quality,
            duration = duration,
            rating = rating
        )
        HorizontalSpacer()
        RowReleasedDateAndGenre(
            genre = genre,
            year = year,
            day = day,
            month = month,
        )
        HorizontalSpacer()
        Description(
            description = description,
        )
        Spacer(modifier = Modifier.height(20.dp))
        RelatedFilms(
            films = films,
            openRelatedFilm = callback::openRelatedFilm,
        )
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
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
    }
}

@Composable
fun Main(
    nameFilm: String,
    quality: String,
    duration: Int,
    rating: Number
) {

    val durationText = remember(duration) {
        when {
            duration % 10 == 1 -> "$duration минута"
            duration % 10 in 2..4 -> "$duration минуты"
            else -> "$duration минут"
        }
    }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = nameFilm,
                color = Color.White,
                style = SCTypography.bodyLarge,
                maxLines = 3,
                modifier = Modifier
                    .weight(1f, fill = false)
                    .widthIn(max = 260.dp)
            )
            Spacer(modifier = Modifier.width(18.dp))
            GradientBox(
                text = quality,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.padding(
                    start = 7.dp,
                    bottom = 6.dp,
                    end = 6.dp,
                    top = 3.dp
                )
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.xSmall))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Duration(durationText = durationText)
            Spacer(modifier = Modifier.width(11.dp))
            Rating(rating = rating)
        }
    }
}

@Composable
fun Duration(durationText: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.clock),
                contentDescription = null,
                tint = Colors.grayB,
            )
        }
        Text(
            text = durationText,
            color = Colors.gray,
            style = SCTypography.titleMedium
        )
    }
}

@Composable
fun Rating(rating: Number) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.stars),
            contentDescription = null,
            tint = Colors.grayB,
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "$rating",
            color = Colors.gray,
            style = SCTypography.titleMedium
        )
    }
}

@Composable
fun RowReleasedDateAndGenre(
    genre: List<String>,
    year: String,
    day: String,
    month: String
) {
    Row {
        ReleasedDate(
            year = year,
            day = day,
            month = month
        )
        Spacer(modifier = Modifier.width(47.dp))
        Genre(genre = genre)
    }
}

@Composable
fun ReleasedDate(
    year: String,
    day: String,
    month: String
) {
    Column {
        Text(
            text = stringResource(R.string.released_date_heading),
            color = Color.White,
            style = SCTypography.bodyMedium
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.released_date, day,month,year),
            color = Colors.gray,
            style = SCTypography.titleMedium
        )
    }
}

@Composable
fun Genre(
    genre: List<String>
) {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.genre),
            color = Color.White,

        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.xSmall))
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacers.small)
        ) {
            genre.forEach { itemGenre ->
                GradientBox(
                    text = itemGenre,
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.padding(start = 14.dp, top = 4.dp, bottom = 4.dp, end = 10.dp)
                )
            }
        }
    }
}

@Composable
fun Description(
    description: String,
) {

    val maxLines = 4
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.description),
            color = Color.White,
            style = SCTypography.bodyMedium
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.small))
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(Colors.gray)) {
                    append(description)
                }
                if (maxLines > 4) {
                    append(" ")
                    withStyle(SpanStyle(Color.White)) {
                        append(stringResource(R.string.next_read))
                    }
                }
            },
            maxLines = maxLines,
            style = SCTypography.titleMedium
        )
    }
}

@Composable
fun RelatedFilms(
    films: List<Film>,
    openRelatedFilm: (Int) -> Unit
) {

    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.related_movies),
            color = Color.White,
            style = SCTypography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(films) { film ->
                Cinema(
                    film = film,
                    onClick = { openRelatedFilm(film.id) },
                    height = 106.dp,
                    width = 142.dp,
                    style = SCTypography.titleMedium
                )
            }
        }

    }
}

@Composable
fun HorizontalSpacer() {
    Column() {
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(thickness = 0.2.dp, color = Colors.gray51)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

fun String.toMonthName(): String {
    return try {
        val monthNumber = this.toInt()
        val dateFormat = SimpleDateFormat("MMMM", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, monthNumber - 1)
        dateFormat.format(calendar.time)
    } catch (e: NumberFormatException) {
        "Неизвестный месяц"
    }
}