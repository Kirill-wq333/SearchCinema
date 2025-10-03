package com.example.searchcinema

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.searchcinema.ui.presintation.feature.discover.ui.DiscoverScreen
import com.example.searchcinema.ui.presintation.theme.SearchCinemaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SearchCinemaTheme {
                DiscoverScreen()
            }
        }
    }
}

