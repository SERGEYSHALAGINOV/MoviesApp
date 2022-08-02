package com.example.movieapp.presentation

import com.example.movieapp.domain.models.Multimedia

sealed class ListItem {

    data class Movies(
        val displayTitle: String?,
        val summaryShort: String?,
        val multimedia: Multimedia
    ) : ListItem()

    object Loader : ListItem()
}