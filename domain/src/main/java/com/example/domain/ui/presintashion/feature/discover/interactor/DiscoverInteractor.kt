package com.example.domain.ui.presintashion.feature.discover.interactor

import com.example.domain.ui.presintashion.feature.Film
import com.example.domain.ui.presintashion.feature.discover.repository.DiscoverRepository

class DiscoverInteractor(
    private val discoverRepository: DiscoverRepository
) {
    suspend fun getDiscover(): List<Film> =
        discoverRepository.getDiscover()
}