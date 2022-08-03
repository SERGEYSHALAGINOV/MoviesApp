package com.example.movieapp.presentation

import com.example.movieapp.domain.models.Multimedia

sealed class ListItem {

    data class MoviesItem(
        val displayTitle: String?,
        val summaryShort: String?,
        val multimedia: Multimedia
    ) : ListItem()

    object Loader : ListItem()

    object TooManyRequest : ListItem()
}
