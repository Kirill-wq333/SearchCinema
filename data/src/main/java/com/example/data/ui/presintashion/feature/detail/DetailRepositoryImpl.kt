package com.example.data.ui.presintashion.feature.detail

import com.example.data.ui.presintashion.feature.detail.datasource.DetailApiService
import com.example.data.ui.presintashion.feature.discover.dto.FilmDto.Companion.toFilm
import com.example.domain.ui.presintashion.feature.detail.repository.DetailRepository
import com.example.domain.ui.presintashion.feature.discover.model.Film
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val apiService: DetailApiService
): DetailRepository {
    override suspend fun getFilmId(filmId: Int): Film =
        apiService.getFilmId(filmId).toFilm()

    override suspend fun getFilm(): List<Film> =
        apiService.getFilm().map { it.toFilm() }

}