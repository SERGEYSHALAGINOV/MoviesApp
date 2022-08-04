package com.example.movieapp.data.api

import com.example.movieapp.data.mappers.MoviesMapper
import com.example.movieapp.domain.MoviesRepository
import com.example.movieapp.domain.models.Movies
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val apiService: MoviesApiService,
    private val mapper: MoviesMapper
) : MoviesRepository {

    override suspend fun getAllMovies(page: Int): List<Movies> {
        val movies = apiService.getAllMovies(page).results
        return try {
            mapper.mapAllMoviesDto(movies)
        } catch (e: Exception) {
            listOf()
        }
    }
}