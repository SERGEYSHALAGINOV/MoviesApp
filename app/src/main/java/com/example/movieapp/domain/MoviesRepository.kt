package com.example.movieapp.domain

import com.example.movieapp.domain.models.Movies

interface MoviesRepository {
    suspend fun getAllMovies(page: Int): List<Movies>
}