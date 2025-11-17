package com.example.domain.ui.presintashion.feature.detail.repository

import com.example.domain.ui.presintashion.feature.Film

interface DetailRepository {
    suspend fun getFilmId(filmId: Int): Result<Film?>

    suspend fun getFilm(): Result<List<Film>>
}