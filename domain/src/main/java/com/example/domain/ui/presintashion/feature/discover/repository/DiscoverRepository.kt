package com.example.domain.ui.presintashion.feature.discover.repository

import com.example.domain.ui.presintashion.feature.discover.model.Film

interface DiscoverRepository {
    suspend fun getDiscover(): List<Film>
}