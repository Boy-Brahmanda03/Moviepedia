package com.example.moviepedia.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviepedia.data.MoviesRepository
import com.example.moviepedia.model.Movie
import com.example.moviepedia.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MoviesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Movie>>> = MutableStateFlow(UiState.Loading)
    val uiState get() = _uiState.asStateFlow()

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) = viewModelScope.launch {
        _query.value = newQuery
        repository.searchMovies(_query.value)
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }

    fun updateMovies(id: Long, newState: Boolean) = viewModelScope.launch {
        repository.updateMovies(id, newState)
            .collect {
                if (it) search(_query.value)
            }
    }
}