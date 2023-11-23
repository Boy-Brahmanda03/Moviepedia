package com.example.moviepedia.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviepedia.data.MoviesRepository
import com.example.moviepedia.model.Movie
import com.example.moviepedia.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MoviesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Movie>> = MutableStateFlow(UiState.Loading)
    val uiState get() = _uiState.asStateFlow()

    fun getMovieById(id: Long) = viewModelScope.launch {
        repository.getMoviesById(id)
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }

    fun updateMovies(id: Long, newState: Boolean) = viewModelScope.launch {
        repository.updateMovies(id, !newState)
            .collect {
                if (it) getMovieById(id)
            }
    }
}