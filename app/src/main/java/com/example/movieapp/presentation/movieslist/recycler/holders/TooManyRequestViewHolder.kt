package com.example.movieapp.presentation.movieslist.recycler.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemMoviesTooManyRequestsBinding

class TooManyRequestViewHolder(private val binding: ItemMoviesTooManyRequestsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        tryAgainLoadAllMovies: (() -> Unit)?,
    ) {
        binding.buttonTryAgain.setOnClickListener { tryAgainLoadAllMovies?.invoke() }
    }
}