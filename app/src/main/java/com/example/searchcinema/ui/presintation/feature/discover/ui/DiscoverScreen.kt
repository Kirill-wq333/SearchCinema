package com.example.searchcinema.ui.presintation.feature.discover.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.ui.presintashion.feature.discover.model.Film
import com.example.searchcinema.R
import com.example.searchcinema.ui.presintation.feature.discover.viewmodel.DiscoverViewModel
import com.example.searchcinema.ui.presintation.shared.discover.Cinema
import com.example.searchcinema.ui.presintation.shared.discover.Roulette
import com.example.searchcinema.ui.presintation.theme.Colors
import com.example.searchcinema.ui.presintation.theme.SCTypography
import com.example.searchcinema.ui.presintation.theme.SearchCinemaTheme
import com.example.searchcinema.ui.presintation.theme.spacers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview
@Composable
private fun DiscoverScreenPreview() {
    SearchCinemaTheme {
        DiscoverScreen()
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun DiscoverScreen(
    openDetailScreen: (Int) -> Unit = {},
    vm: DiscoverViewModel = hiltViewModel()
) {
    val film by vm.film.collectAsState()
    val state = vm.state.collectAsState()

        Discover(
            openDetailScreen = openDetailScreen,
            films = film
        )
}

@Composable
fun Discover(
    films: List<Film>,
    openDetailScreen: (Int) -> Unit,
) {
    var query by remember { mutableStateOf("") }
    val cinemas = remember { listOf("Фильмы", "Сериалы", "Документальные", "Спортивные") }
    var selectedTab by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { cinemas.size }
    val coroutineScope = rememberCoroutineScope()

    val filteredFilms = remember(query, films) {
        if (query.isBlank()) {
            films
        } else {
            films.filter { film ->
                film.title.contains(query, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.backColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(R.string.movie_film),
            color = Color.White,
            style = SCTypography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 34.dp)
        )
        Spacer(modifier = Modifier.height(26.dp))
        SearchAndRouletteCinema(
            cinemas = cinemas,
            query = query,
            selectedTab = selectedTab,
            pagerState = pagerState,
            coroutine = coroutineScope,
            onQueryChanges = { query = it },
            foundCount = filteredFilms.size
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) {
            Content(
                films = filteredFilms,
                openDetailScreen = openDetailScreen
            )
        }
    }
}

@Composable
fun SearchAndRouletteCinema(
    cinemas: List<String>,
    query: String,
    selectedTab: Int,
    pagerState: PagerState,
    onQueryChanges: (String) -> Unit,
    foundCount: Int,
    coroutine: CoroutineScope
) {
    Column(
        modifier = Modifier.padding(start = 24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Search(
            query = query,
            onValueChanges = onQueryChanges,
            foundCount = foundCount
            )
        Spacer(modifier = Modifier.height(23.dp))
        RouletteCinema(
            cinemas = cinemas,
            onSelectedTab = { selectedTab == it},
            pagerState = pagerState,
            coroutine = coroutine
        )
    }
}

@Composable
private fun Search(
    query: String,
    onValueChanges: (String) -> Unit,
    foundCount: Int
) {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 24.dp),
            value = query,

            onValueChange = onValueChanges,
            shape = RoundedCornerShape(20.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            maxLines = 1,
            textStyle = SCTypography.bodySmall,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedContainerColor = Colors.jaguar,
                unfocusedContainerColor = Colors.jaguar,
                disabledContainerColor = Colors.jaguar,
                errorContainerColor = Colors.jaguar
            )
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.xSmall))
        if (query.isNotBlank()) {
            Text(
                text = stringResource(R.string.finded, foundCount),
                color = Color.White,
                style = SCTypography.titleMedium
            )
        }
    }
}

@Composable
fun RouletteCinema(
    cinemas: List<String>,
    onSelectedTab: (Int) -> Unit,
    pagerState: PagerState,
    coroutine: CoroutineScope
) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(13.dp)
    ) {
        cinemas.forEachIndexed { index, cinema ->

            val offset = pagerState.offsetForPage(index)
            val startOffset = pagerState.startOffsetForPage(index)
            val endOffset = pagerState.endOffsetForPage(index)

            Roulette(
                cinema = cinema,
                clickCinema = {
                    coroutine.launch {
                        pagerState.animateScrollToPage(index)
                    }
                    onSelectedTab(index)
                },
                isSelected = (index == pagerState.currentPage),
                offset = offset,
                startOffset = startOffset,
                endOffset = endOffset
            )
        }
    }
}

@Composable
private fun Content(
    films: List<Film>,
    openDetailScreen: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(19.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(films) { film ->
            Cinema(
                film = film,
                onClick = { openDetailScreen(film.id) }
            )
        }
    }
}

private fun PagerState.offsetForPage(page: Int) = (currentPage - page) + currentPageOffsetFraction

private fun PagerState.startOffsetForPage(page: Int): Float {
    return offsetForPage(page).coerceAtLeast(0f)
}

private fun PagerState.endOffsetForPage(page: Int): Float {
    return offsetForPage(page).coerceAtMost(0f)
}