package com.example.searchcinema

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.searchcinema.ui.presintation.NavigationHost
import com.example.searchcinema.ui.presintation.feature.discover.ui.DiscoverScreen
import com.example.searchcinema.ui.presintation.theme.SearchCinemaTheme

class MainActivity : ComponentActivity() {

    private var navController: NavHostController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navController = rememberNavController()

            SearchCinemaTheme {
                NavigationHost(
                    navController = navController
                )
            }
        }
    }
}

