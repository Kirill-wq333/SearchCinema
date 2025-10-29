package com.example.domain.ui.presintashion.feature.discover.model

import kotlinx.serialization.Serializable

@Serializable
data class Film(
    val id: Int,
    val pictureLink: String,
    val title: String,
    val description: String,
    val genre: String,
    val rating: Number,
    val duration: Int,
    val quality: String,
    val releaseDate: String,
)


