package com.example.data.ui.presintashion.feature.detail.datasource

import com.example.data.ui.presintashion.feature.FilmDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApiService {

    @GET("api/movies/{id}")
    suspend fun getFilmId(@Path("id") id: Int): FilmDto?

    @GET("api/movies/")
    suspend fun getFilm(): List<FilmDto>

}