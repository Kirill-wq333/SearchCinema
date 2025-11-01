package com.example.searchcinema.ui.presintation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.searchcinema.ui.presintation.approuts.AppRouts
import com.example.searchcinema.ui.presintation.feature.detail.ui.DetailScreen
import com.example.searchcinema.ui.presintation.feature.detail.viewmodel.DetailContract
import com.example.searchcinema.ui.presintation.feature.detail.viewmodel.DetailViewModel
import com.example.searchcinema.ui.presintation.feature.discover.ui.DiscoverScreen
import com.example.searchcinema.ui.presintation.feature.discover.viewmodel.DiscoverViewModel

@Composable
fun NavigationBuilder(
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
            val vmDiscover = hiltViewModel<DiscoverViewModel>()

            DiscoverScreen(
                vm = vmDiscover,
                openDetailScreen = { id ->
                    navController.navigate("${AppRouts.DETAIL}/$id")
                }
            )
        }

        composable(
            route = "${AppRouts.DETAIL}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("id") ?: 0
            val vmDetail = hiltViewModel<DetailViewModel>()

            LaunchedEffect(movieId) {
                vmDetail.handleEvent(DetailContract.Event.FetchContent(movieId))
            }

            DetailScreen(
                vm = vmDetail,
                onBack = { navController.popBackStack() },
                openRelatedFilm = { id -> navController.navigate("${AppRouts.DETAIL}/$id") }
            )
        }
    }
}