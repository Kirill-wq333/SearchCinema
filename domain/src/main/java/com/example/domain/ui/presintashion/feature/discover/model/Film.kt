package com.example.domain.ui.presintashion.feature.discover.model

data class Film(
    val id: Int,
    val pictureUrl: String,
    val title: String,
    val description: String,
    val genre: List<Genre>,
    val rating: Number,
    val duration: Int,
    val quality: String,
    val releaseDate: String,
)

data class FilmList(
    val films: List<Film>
)

data class Genre(
    val genre: String
)
