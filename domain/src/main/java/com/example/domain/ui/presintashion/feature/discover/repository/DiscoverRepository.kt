package com.example.domain.ui.presintashion.feature.discover.repository

import com.example.domain.ui.presintashion.feature.discover.model.Film
import com.example.domain.ui.presintashion.feature.discover.model.FilmList

interface DiscoverRepository {
    suspend fun getDiscover(): FilmList
}