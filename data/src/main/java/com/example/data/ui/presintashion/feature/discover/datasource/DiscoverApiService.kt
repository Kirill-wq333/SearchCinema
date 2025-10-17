package com.example.data.ui.presintashion.feature.discover.datasource

import com.example.data.ui.presintashion.feature.discover.dto.FilmDto
import com.example.data.ui.presintashion.feature.discover.dto.FilmListDto
import retrofit2.http.GET

interface DiscoverApiService {

    @GET("api/movies/")
    suspend fun getDiscover(): FilmListDto
}