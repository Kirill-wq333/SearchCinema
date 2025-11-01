package com.example.searchcinema.ui.presintation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.searchcinema.R

private val lato = FontFamily(Font(R.font.lato_regular, FontWeight.Normal))


val     SCTypography: Typography
    @Composable
    get() = Typography(
        bodyLarge = TextStyle(
            fontSize = 24.sp,
            fontFamily = lato,
            lineHeight = 36.sp,
            fontWeight = FontWeight.Normal
        ),
        labelLarge = TextStyle(
            fontSize = 20.sp,
            fontFamily = lato,
            fontWeight = FontWeight.Normal
        ),
        bodyMedium = TextStyle(
            fontSize = 16.sp,
            fontFamily = lato,
            fontWeight = FontWeight.Normal
        ),
        bodySmall = TextStyle(
            fontSize = 14.sp,
            lineHeight = 16.sp,
            fontFamily = lato,
            fontWeight = FontWeight.Normal,
        ),
        titleLarge = TextStyle(
            fontSize = 22.sp,
            lineHeight = 36.sp,
            fontFamily = lato,
            fontWeight = FontWeight.Normal
        ),
        titleMedium = TextStyle(
            fontSize = 12.sp,
            fontFamily = lato,
            fontWeight = FontWeight.Normal,
        ),
    )