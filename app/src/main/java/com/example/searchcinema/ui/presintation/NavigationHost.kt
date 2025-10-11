package com.example.searchcinema.ui.presintation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.searchcinema.ui.presintation.navigation.NavigationBuilder

@Composable
fun NavigationHost(
    navController: NavHostController?
) {
    if (navController == null) return
    HostScaffold { paddingValues ->
        NavigationBuilder(
            navController = navController,
            paddingValues = paddingValues,
        )
    }
}

@Composable
private fun HostScaffold(
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        content = content,
    )

}