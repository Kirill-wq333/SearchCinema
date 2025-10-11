package com.example.domain.ui.presintashion.feature.discover.model

data class Film(
    val id: Int,
    val imageUrl: String,
    val film: String,
    val description: String,
    val genre: List<Genre>,
    val rate: Number,
    val time: Int,
    val year: Int
)

data class Genre(
    val genre: String
)
