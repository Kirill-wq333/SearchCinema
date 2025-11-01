package com.example.domain.ui.presintashion.feature.detail.interactot

import com.example.domain.ui.presintashion.feature.detail.repository.DetailRepository
import com.example.domain.ui.presintashion.feature.discover.model.Film

class DetailInteractor(
    private val detailRepository: DetailRepository
) {
    suspend fun getFilmId(filmId: Int): Film =
        detailRepository.getFilmId(filmId = filmId)

    suspend fun getFilm(): List<Film> =
        detailRepository.getFilm()
}