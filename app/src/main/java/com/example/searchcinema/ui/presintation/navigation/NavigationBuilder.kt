package com.example.searchcinema.ui.presintation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.searchcinema.ui.presintation.approuts.AppRouts
import com.example.searchcinema.ui.presintation.feature.discover.ui.Discover
import com.example.searchcinema.ui.presintation.feature.discover.ui.DiscoverScreen
import com.example.searchcinema.ui.presintation.mock.Mock

@Composable
fun NavigationBuilder(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = AppRouts.DISCOVER,
        modifier = Modifier
            .padding(paddingValues = paddingValues)
    ) {

        composable(route = AppRouts.DISCOVER) {
            DiscoverScreen(
                films = Mock.demoFilms,
                openDetailScreen = { id ->
                    navController.navigate("${AppRouts.DETAIL}/${id}")
                }
            )
        }

        composable(route = AppRouts.DETAIL) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0

        }

    }
}