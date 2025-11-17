package com.example.data.ui.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.ui.storage.room.entity.FilmEntity

@Dao
interface FilmDao {

    @Query("SELECT * FROM films WHERE id = :filmId")
    suspend fun getFilmById(filmId: Int): FilmEntity

    @Query("SELECT * FROM films")
    suspend fun getAllFilms(): List<FilmEntity>

    @Insert(entity = FilmEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: FilmEntity)

    @Insert(entity = FilmEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(films: List<FilmEntity>)
}