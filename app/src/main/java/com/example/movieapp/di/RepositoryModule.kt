package com.example.movieapp.di

import com.example.movieapp.data.api.MoviesRepositoryImpl
import com.example.movieapp.domain.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun provideMovieRepository(impl: MoviesRepositoryImpl): MoviesRepository

}