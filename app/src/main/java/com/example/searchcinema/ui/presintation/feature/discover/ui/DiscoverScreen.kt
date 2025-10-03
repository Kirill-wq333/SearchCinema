package com.example.searchcinema.ui.presintation.feature.discover.ui

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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import com.example.searchcinema.R
import com.example.searchcinema.ui.presintation.shared.discover.Roulette
import com.example.searchcinema.ui.presintation.theme.Colors
import com.example.searchcinema.ui.presintation.theme.SCTypography
import com.example.searchcinema.ui.presintation.theme.SearchCinemaTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview
@Composable
private fun DiscoverScreenPreview() {
    SearchCinemaTheme {
        DiscoverScreen()
    }
}

@Composable
fun DiscoverScreen() {
    SearchCinemaTheme {
        Discover()
    }
}

@Composable
fun Discover() {
    var quary by remember { mutableStateOf("") }
    val cinemas = remember { listOf("Фильмы", "Сериалы", "Документальные", "Спорт") }
    var selectedTab by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { cinemas.size }
    val coroutineScope = rememberCoroutineScope()

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
            quary = quary,
            selectedTab = selectedTab,
            pagerState = pagerState,
            coroutine = coroutineScope
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) {
            Content()
        }
    }
}

@Composable
fun SearchAndRouletteCinema(
    cinemas: List<String>,
    quary: String,
    selectedTab: Int,
    pagerState: PagerState,
    coroutine: CoroutineScope
) {
    var text by remember { mutableStateOf(quary) }

    Column(
        modifier = Modifier.padding(start = 24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 24.dp),
            value = text,

            onValueChange = { it -> text = it },
            shape = RoundedCornerShape(20.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            colors = TextFieldDefaults.colors(
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
fun Content() {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
//            CinemaItem(
//                cinama = ""
//            )
        }
    }
}

fun PagerState.offsetForPage(page: Int) = (currentPage - page) + currentPageOffsetFraction

fun PagerState.startOffsetForPage(page: Int): Float {
    return offsetForPage(page).coerceAtLeast(0f)
}

fun PagerState.endOffsetForPage(page: Int): Float {
    return offsetForPage(page).coerceAtMost(0f)
}