package com.example.movieapp.di

import com.example.movieapp.data.api.ApiFactory
import com.example.movieapp.data.api.MoviesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun providesMoviesApiService(): MoviesApiService = ApiFactory.apiService
}