package com.example.data.ui.presintashion.feature.discover

import com.example.data.ui.presintashion.feature.discover.datasource.DiscoverApiService
import com.example.data.ui.presintashion.feature.discover.dto.FilmListDto.Companion.toFilmDto
import com.example.domain.ui.presintashion.feature.discover.model.FilmList
import com.example.domain.ui.presintashion.feature.discover.repository.DiscoverRepository
import javax.inject.Inject

class DiscoverRepositoryImpl @Inject constructor(
    private val apiService: DiscoverApiService
): DiscoverRepository {
    override suspend fun getDiscover(): FilmList {
       return apiService.getDiscover().toFilmDto()
    }
}