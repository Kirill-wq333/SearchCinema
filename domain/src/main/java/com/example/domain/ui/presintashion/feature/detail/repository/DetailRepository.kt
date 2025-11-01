package com.example.domain.ui.presintashion.feature.detail.repository

import com.example.domain.ui.presintashion.feature.discover.model.Film

interface DetailRepository {
    suspend fun getFilmId(filmId: Int): Film

    suspend fun getFilm(): List<Film>
}