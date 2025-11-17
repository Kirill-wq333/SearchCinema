package com.example.data.ui.presintashion.feature.detail

import com.example.data.ui.presintashion.feature.FilmDto.Companion.toFilm
import com.example.data.ui.presintashion.feature.detail.datasource.DetailApiService
import com.example.data.ui.storage.room.dao.FilmDao
import com.example.data.ui.storage.room.entity.toFilm
import com.example.data.ui.storage.room.entity.toFilmEntity
import com.example.domain.ui.presintashion.feature.Film
import com.example.domain.ui.presintashion.feature.detail.repository.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val apiService: DetailApiService,
    private val filmDao: FilmDao
): DetailRepository {

    override suspend fun getFilmId(filmId: Int): Result<Film?> {
        return try {
            val filmDto = apiService.getFilmId(filmId)

            filmDto?.let { dto ->
                val film = dto.toFilm()

                filmDao.insertFilms(listOf(film.toFilmEntity()))

                Result.success(film)
            } ?: Result.success(null)

        } catch (e: Exception) {
            try {
                val cachedFilm = filmDao.getFilmById(filmId)?.toFilm()
                if (cachedFilm != null) {
                    Result.success(cachedFilm)
                } else {
                    Result.failure(e)
                }
            } catch (dbException: Exception) {
                Result.failure(dbException)
            }
        }
    }

    override suspend fun getFilm(): Result<List<Film>> {
        return try {
            val filmDtos = apiService.getFilm()

            val films = filmDtos.map { it.toFilm() }

            val filmEntities = films.map { it.toFilmEntity() }

            filmDao.insertFilms(filmEntities)

            Result.success(films)

        } catch (e: Exception) {
            try {
                val cachedFilms = filmDao.getAllFilms()
                val convertedFilms = cachedFilms.map { it.toFilm() }

                if (convertedFilms.isNotEmpty()) {
                    Result.success(convertedFilms)
                } else {
                    Result.failure(e)
                }
            } catch (dbException: Exception) {
                Result.failure(dbException)
            }
        }
    }

}