package com.example.movieapp.presentation

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemMoviesLoadingBinding

class LoaderViewHolder(private val binding: ItemMoviesLoadingBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        binding.loader.isVisible = true
    }
}