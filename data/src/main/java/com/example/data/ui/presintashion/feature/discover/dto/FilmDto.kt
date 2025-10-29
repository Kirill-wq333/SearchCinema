package com.example.data.ui.presintashion.feature.discover.dto

import com.example.domain.ui.presintashion.feature.discover.model.Film

data class FilmDto(
    val id: Int,
    val pictureLink: String,
    val title: String,
    val description: String,
    val genre: String,
    val rating: Number,
    val duration: Int,
    val quality: String,
    val releaseDate: String,
){
    companion object{

        fun FilmDto.toFilm(): Film =
            Film(
                id = id,
                pictureLink = pictureLink,
                title = title,
                description = description,
                genre = genre,
                rating = rating,
                duration = duration,
                quality = quality,
                releaseDate = releaseDate
            )
    }
}
