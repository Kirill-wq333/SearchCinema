package com.example.searchcinema.ui.presintation.feature.discover.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.ui.presintashion.feature.discover.model.Film
import com.example.searchcinema.R
import com.example.searchcinema.ui.presintation.feature.discover.viewmodel.DiscoverContract
import com.example.searchcinema.ui.presintation.feature.discover.viewmodel.DiscoverViewModel
import com.example.searchcinema.ui.presintation.shared.discover.Cinema
import com.example.searchcinema.ui.presintation.shared.discover.Genre
import com.example.searchcinema.ui.presintation.shared.screens.ErrorScreen
import com.example.searchcinema.ui.presintation.shared.screens.IconAndText
import com.example.searchcinema.ui.presintation.shared.screens.LoadingScreen
import com.example.searchcinema.ui.presintation.theme.Colors
import com.example.searchcinema.ui.presintation.theme.SCTypography
import com.example.searchcinema.ui.presintation.theme.spacers
import com.example.searchcinema.ui.presintation.utils.sdkutil.Connectivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private interface DiscoverScreenCallBack {
    fun refresh() {}
}

@Composable
fun DiscoverScreen(
    openDetailScreen: (Int) -> Unit = {},
    vm: DiscoverViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val films by vm.films.collectAsState()
    val state by vm.state.collectAsState()

    LaunchedEffect(state) {
        if (state is DiscoverContract.State.Loading) {
            vm.handleEvent(DiscoverContract.Event.FetchContent)
        }
    }

    LaunchedEffect(Unit) {
        while(true) {
            delay(1000)
            if (!Connectivity.hasInternetConnection(context)) {
                vm.handleEvent(DiscoverContract.Event.EnableNoInternetConnectionState)
            }
        }
    }

    val callback = object: DiscoverScreenCallBack{
        override fun refresh() {
            vm.handleEvent(DiscoverContract.Event.Refresh)
        }
    }

    Discover(
        openDetailScreen = openDetailScreen,
        films = films,
        state = state,
        callback = callback
    )
}


@Composable
private fun Discover(
    callback: DiscoverScreenCallBack,
    state: DiscoverContract.State,
    films: List<Film>,
    openDetailScreen: (Int) -> Unit,
) {
    var query by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) }

    val genres = remember(films) {
        listOf("Все") + films.flatMap { it.genre }.toSet().toList().sorted()
    }
    val pagerState = rememberPagerState { genres.size }
    val coroutineScope = rememberCoroutineScope()

    val filteredFilms = remember(query, pagerState.currentPage, films, genres) {
        val currentGenre = if (pagerState.currentPage == 0) null else genres[pagerState.currentPage]

        films.filter { film ->
            val matchesQuery = query.isBlank() ||
                    film.title.contains(query, ignoreCase = true)

            val matchesGenre = currentGenre == null ||
                    currentGenre == "Все" ||
                    film.genre.contains(currentGenre)

            matchesQuery && matchesGenre
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
        when(state) {
            is DiscoverContract.State.Loaded -> {
                DiscoverContent(
                    genres = genres,
                    query = query,
                    coroutineScope = coroutineScope,
                    onQueryChanges = { query = it },
                    filteredFilms = filteredFilms,
                    selectedTab = selectedTab,
                    pagerState = pagerState,
                    openDetailScreen = openDetailScreen,
                )
            }
            is DiscoverContract.State.NoInternetConnection -> {
                ErrorScreen(
                    onRefresh = callback::refresh
                )
            }
            is DiscoverContract.State.Loading ->{
                LoadingScreen()
            }
        }
    }

}

@Composable
fun DiscoverContent(
    genres: List<String>,
    query: String,
    coroutineScope: CoroutineScope,
    onQueryChanges: (String) -> Unit,
    filteredFilms: List<Film>,
    selectedTab: Int,
    pagerState: PagerState,
    openDetailScreen: (Int) -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(26.dp))
        Search(
            query = query,
            onValueChanges = onQueryChanges,
            foundCount = filteredFilms.size
        )
        Spacer(modifier = Modifier.height(23.dp))
        if (filteredFilms.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                IconAndText(
                    text = R.string.not_found,
                    icon = R.drawable.not_found,
                    spacer = 36.dp,
                    width = 152.dp,
                    height = 146.dp,
                    style = SCTypography.labelLarge
                )
            }
        } else {
            RouletteGenre(
                genres = genres,
                onSelectedTab = { selectedTab == it },
                pagerState = pagerState,
                coroutine = coroutineScope,
            )
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
            ) { page ->
                val currentGenre = if (page == 0) null else genres[page]

                val filmsForPage = remember(filteredFilms, query, currentGenre) {
                    filteredFilms.filter { film ->
                        val matchesQuery = query.isBlank() ||
                                film.title.contains(query, ignoreCase = true)

                        val matchesGenre = currentGenre == null ||
                                currentGenre == "Все" ||
                                film.genre.contains(currentGenre)

                        matchesQuery && matchesGenre
                    }
                }
                Content(
                    films = filmsForPage,
                    openDetailScreen = openDetailScreen
                )
            }
        }
    }
}

@Composable
private fun Search(
    query: String,
    onValueChanges: (String) -> Unit,
    foundCount: Int
) {
    Column(
        modifier = Modifier.padding(start = 24.dp),
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
            placeholder = {
                Text(
                    text = "Шерлок Холмс",
                    color = Colors.gray,
                    style = SCTypography.bodySmall
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
fun RouletteGenre(
    genres: List<String>,
    onSelectedTab: (Int) -> Unit,
    pagerState: PagerState,
    coroutine: CoroutineScope,
) {
    val lazyListState = rememberLazyListState()

    LaunchedEffect(pagerState.currentPage) {
        lazyListState.animateScrollToItem(pagerState.currentPage)
    }

    LazyRow(
        modifier = Modifier
            .padding(start = 24.dp)
            .fillMaxWidth(),
        state = lazyListState,
        horizontalArrangement = Arrangement.spacedBy(13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(genres) { index, genre ->

            val offset = pagerState.offsetForPage(index)
            val startOffset = pagerState.startOffsetForPage(index)
            val endOffset = pagerState.endOffsetForPage(index)

            Genre(
                genre = genre,
                clickGenre = {
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