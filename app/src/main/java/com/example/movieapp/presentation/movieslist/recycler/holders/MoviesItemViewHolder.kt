package com.example.movieapp.presentation.movieslist.recycler.holders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.ItemMoviesBinding
import com.example.movieapp.presentation.models.ListItem

class MoviesItemViewHolder(private val binding: ItemMoviesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        movie: ListItem.MoviesItem,
    ) {
        Glide.with(itemView.context)
            .load(movie.multimedia.src)
            .into(binding.ivLogoMovie)
        binding.tvTitleText.text = movie.displayTitle
        binding.tvDescriptionText.text = movie.summaryShort
    }
}