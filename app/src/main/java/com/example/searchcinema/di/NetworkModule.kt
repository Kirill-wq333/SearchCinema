package com.example.searchcinema.di

import com.example.data.ui.presintashion.feature.discover.datasource.DiscoverApiService
import com.example.domain.ui.presintashion.feature.discover.interactor.DiscoverInteractor
import com.example.domain.ui.presintashion.feature.discover.repository.DiscoverRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://103.90.75.40:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideDiscoverApiService(retrofit: Retrofit): DiscoverApiService {
        return retrofit.create(DiscoverApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDiscoverInteractor(repository: DiscoverRepository): DiscoverInteractor {
        return DiscoverInteractor(repository)
    }
}