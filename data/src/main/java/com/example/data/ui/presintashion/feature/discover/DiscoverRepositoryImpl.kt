package com.example.data.ui.presintashion.feature.discover

import com.example.data.ui.presintashion.feature.FilmDto.Companion.toFilm
import com.example.data.ui.presintashion.feature.discover.datasource.DiscoverApiService
import com.example.domain.ui.presintashion.feature.Film
import com.example.domain.ui.presintashion.feature.discover.repository.DiscoverRepository
import javax.inject.Inject

class DiscoverRepositoryImpl @Inject constructor(
    private val apiService: DiscoverApiService
): DiscoverRepository {

    override suspend fun getDiscover(): List<Film> =
        apiService.getFilm().map { it.toFilm() }

}