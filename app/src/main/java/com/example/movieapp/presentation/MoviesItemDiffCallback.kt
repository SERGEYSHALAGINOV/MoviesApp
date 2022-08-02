package com.example.movieapp.presentation

import androidx.recyclerview.widget.DiffUtil
import javax.inject.Inject

class MoviesItemDiffCallback @Inject constructor() : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return if (oldItem == ListItem.Loader || newItem == ListItem.Loader) {
            false
        } else {
            if (oldItem is ListItem.Movies && newItem is ListItem.Movies) {
                oldItem.displayTitle == newItem.displayTitle
            } else {
                false
            }
        }
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }
}




