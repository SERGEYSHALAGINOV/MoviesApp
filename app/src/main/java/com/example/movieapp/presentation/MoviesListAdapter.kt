package com.example.movieapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemMoviesBinding
import com.example.movieapp.databinding.ItemMoviesLoadingBinding
import com.example.movieapp.domain.models.Movies
import javax.inject.Inject

class MoviesListAdapter @Inject constructor(
    callback: MoviesItemDiffCallback,
) :
    ListAdapter<ListItem, RecyclerView.ViewHolder>(callback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_ITEM ->
                MoviesItemViewHolder(ItemMoviesBinding.inflate(inflater, parent, false))
            VIEW_TYPE_LOADER ->
                LoaderViewHolder(ItemMoviesLoadingBinding.inflate(inflater, parent, false))
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (val moviesItem = getItem(position)) {
            is ListItem.Movies -> (viewHolder as MoviesItemViewHolder).bind(
                moviesItem
            )
            is ListItem.Loader -> (viewHolder as LoaderViewHolder).bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val moviesItem = getItem(position)
        return if (moviesItem == ListItem.Loader) {
            VIEW_TYPE_LOADER
        } else {
            VIEW_TYPE_ITEM
        }
    }

    fun createLoader() {
        if (currentList[currentList.lastIndex] != ListItem.Loader) {
            val listMut: MutableList<ListItem> = currentList.toMutableList()
            listMut.add(ListItem.Loader)
            submitList(listMut)
        } else {
            return
        }
    }

    fun removeLoader() {
        if (currentList[currentList.lastIndex] == ListItem.Loader) {
            val listMut: MutableList<ListItem> = currentList.toMutableList()
            listMut.removeLast()
            submitList(listMut)
        } else {
            return
        }
    }

    companion object {
        const val VIEW_TYPE_ITEM = 1
        const val VIEW_TYPE_LOADER = 2
    }
}