package com.example.movieapp.data.mappers

import com.example.movieapp.data.models.MoviesDto
import com.example.movieapp.data.models.MultimediaDto
import com.example.movieapp.domain.models.Movies
import com.example.movieapp.domain.models.Multimedia
import javax.inject.Inject

class MoviesMapper @Inject constructor() {

    fun mapAllMoviesDto(dtoItems: List<MoviesDto>) = dtoItems.map { mapMoviesDto(it) }

    private fun mapMoviesDto(dtoItem: MoviesDto) =
        Movies(
            displayTitle = dtoItem.displayTitle,
            summaryShort = dtoItem.summaryShort,
            multimedia = mapMultimediaDto(dtoItem.multimedia)
        )


    private fun mapMultimediaDto(dtoItem: MultimediaDto) =
        Multimedia(
            src = dtoItem.src
        )
}