package com.example.movieapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.models.Movies
import com.example.movieapp.domain.models.ScreenState
import com.example.movieapp.domain.usecases.GetAllMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getAllMoviesUseCase: GetAllMoviesUseCase
) : ViewModel() {

    private val listMovies: MutableList<ListItem.Movies> by lazy { mutableListOf() }
    private var countPage = FIRST_OFFSET
    private var isFinished = false
    private var isLoading = false

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState

    private val _movies = MutableLiveData<List<ListItem.Movies>>()
    val movies: LiveData<List<ListItem.Movies>> = _movies

    init {
        loadAllMovies(countPage)
    }

    private fun loadAllMovies(offset: Int) {
        viewModelScope.launch {
            _screenState.value = ScreenState.Loading
            try {
                withContext(Dispatchers.IO) {
                    val movies = getAllMoviesUseCase(offset).map {
                        ListItem.Movies(
                            displayTitle = it.displayTitle,
                            summaryShort = it.summaryShort,
                            multimedia = it.multimedia
                        )
                    }
                    if (movies.isNullOrEmpty()) {
                        isFinished = true
                    } else {
                        listMovies.addAll(movies)
                    }
                }
                _movies.postValue(listMovies)
                _screenState.value = ScreenState.Content
            } catch (e: Exception) {
                e.printStackTrace()
                _screenState.value = ScreenState.Error("No connection", e.message.toString())
            } finally {
                isLoading = false
            }
        }
    }

    fun loadingNextPage() {
        if (countPage > 19) {
            _screenState.value = ScreenState.Error("No connection", HTTP_429_TOO_MANY_REQUESTS)
            return
        }
        if (isFinished || isLoading) {
            return
        }
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    isLoading = true
                    countPage++
                    loadAllMovies(countPage * NUMBER_ITEMS_PER_PAGE)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        const val NUMBER_ITEMS_PER_PAGE = 20
        const val FIRST_OFFSET = 0
        private const val HTTP_429_TOO_MANY_REQUESTS = "429"
    }
}