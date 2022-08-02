package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.MoviesRepository
import com.example.movieapp.domain.models.Movies
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(page: Int): List<Movies> = moviesRepository.getAllMovies(page)
}