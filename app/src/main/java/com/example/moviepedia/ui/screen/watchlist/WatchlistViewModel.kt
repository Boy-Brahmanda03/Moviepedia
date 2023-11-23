package com.example.moviepedia.ui.screen.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviepedia.data.MoviesRepository
import com.example.moviepedia.model.Movie
import com.example.moviepedia.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WatchlistViewModel(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Movie>>> = MutableStateFlow(UiState.Loading)
    val uiState get() = _uiState.asStateFlow()

    fun getWatchlistMovies() = viewModelScope.launch {
        repository.getWatchlistMovies()
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }

    fun updateMovies(id: Long, newState: Boolean) {
        repository.updateMovies(id, newState)
        getWatchlistMovies()
    }
}