package com.example.data.ui.storage.di

import android.content.Context
import com.example.data.ui.storage.SearchCinemaDatabase
import com.example.data.ui.storage.room.dao.FilmDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): SearchCinemaDatabase {
        return SearchCinemaDatabase.getInstance(context)
    }
    
    @Provides
    @Singleton
    fun provideFilmDao(database: SearchCinemaDatabase): FilmDao {
        return database.filmDao()
    }
}