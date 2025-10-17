package com.example.data.ui.core.module

import com.example.data.ui.presintashion.feature.discover.DiscoverRepositoryImpl
import com.example.domain.ui.presintashion.feature.discover.repository.DiscoverRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindFilmRepository(
        impl: DiscoverRepositoryImpl
    ) : DiscoverRepository

}