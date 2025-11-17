package com.example.domain.ui.presintashion.feature.detail.interactot

import com.example.domain.ui.presintashion.feature.Film
import com.example.domain.ui.presintashion.feature.detail.repository.DetailRepository

class DetailInteractor(
    private val detailRepository: DetailRepository
) {
    suspend fun getFilmId(filmId: Int): Result<Film?> =
        detailRepository.getFilmId(filmId = filmId)

    suspend fun getFilm(): Result<List<Film>> =
        detailRepository.getFilm()
}