package com.example.searchcinema.ui.presintation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.searchcinema.ui.presintation.theme.spacer.SpacerLetter

val darkPurple = Color(0xFF15141F)

val MaterialTheme.spacers
    @Composable
    get() = SpacerLetter.current

object Colors {
    val backColor = Color(0xFF15141F)
    val veryLightRed = Color(0xFFFF8F71)
    val lightRed = Color(0xFFEF2D1A)

    val gray = Color(0xFFBCBCBC)
    val jaguar = Color(0xFF211F30)
    val white = Color(0xFFE2E2E2)
}

private val DarkColorScheme = darkColorScheme(
    primary = darkPurple,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@Composable
fun SearchCinemaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}