package com.example.data.ui.presintashion.feature.discover.dto

import com.example.data.ui.presintashion.feature.discover.dto.FilmDto.Companion.toFilm
import com.example.domain.ui.presintashion.feature.discover.model.Film
import com.example.domain.ui.presintashion.feature.discover.model.FilmList
import com.example.domain.ui.presintashion.feature.discover.model.Genre

data class FilmDto(
    val id: Int,
    val pictureUrl: String,
    val title: String,
    val description: String,
    val genre: List<Genre>,
    val rating: Number,
    val duration: Int,
    val quality: String,
    val releaseDate: String,
){
    companion object{

        fun FilmDto.toFilm(): Film =
            Film(
                id = id,
                pictureUrl = pictureUrl,
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

data class FilmListDto(
    val film:List<FilmDto>
){
    companion object{
        fun FilmListDto.toFilmDto(): FilmList =
            FilmList(
                films = film.map{ it.toFilm() }
            )
    }
}