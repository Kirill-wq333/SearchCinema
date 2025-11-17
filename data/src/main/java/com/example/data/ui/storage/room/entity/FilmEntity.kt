package com.example.data.ui.storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.ui.presintashion.feature.Film
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "films")
data class FilmEntity(
    @PrimaryKey
    val id: Int,
    val pictureLink: String,
    val title: String,
    val description: String,
    val rating: Double,
    val duration: Int,
    val quality: String,
    val releaseDate: String,
    val genre: String
)

fun Film.toFilmEntity(): FilmEntity {
    return FilmEntity(
        id = this.id,
        pictureLink = this.pictureLink,
        title = this.title,
        description = this.description,
        rating = this.rating.toDouble(),
        duration = this.duration,
        quality = this.quality,
        releaseDate = this.releaseDate,
        genre = Gson().toJson(this.genre)
    )
}

fun FilmEntity.toFilm(): Film {
    val genreListType = object : TypeToken<List<String>>() {}.type
    return Film(
        id = this.id,
        pictureLink = this.pictureLink,
        title = this.title,
        description = this.description,
        genre = Gson().fromJson(this.genre, genreListType),
        rating = this.rating,
        duration = this.duration,
        quality = this.quality,
        releaseDate = this.releaseDate
    )
}