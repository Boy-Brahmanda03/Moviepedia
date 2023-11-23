package com.example.moviepedia.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviepedia.data.MoviesRepository
import com.example.moviepedia.model.Movie
import com.example.moviepedia.model.UserMovies
import com.example.moviepedia.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MoviesRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<UserMovies>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<UserMovies>>
        get() = _uiState

    fun getDetailMovieById(id: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getUserMoviesById(id))
        }
    }

    fun addToWatchList( movie: Movie,isWatchedList: Boolean){
        viewModelScope.launch {

        }
    }
}