package com.example.domain.ui.presintashion.feature

import kotlinx.serialization.Serializable

@Serializable
data class Film(
    val id: Int,
    val pictureLink: String,
    val title: String,
    val description: String,
    val genre: List<String>,
    val rating: Number,
    val duration: Int,
    val quality: String,
    val releaseDate: String,
)