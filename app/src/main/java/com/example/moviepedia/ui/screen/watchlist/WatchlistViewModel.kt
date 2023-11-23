package com.example.moviepedia.ui.screen.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviepedia.data.MoviesRepository
import com.example.moviepedia.model.UserMovies
import com.example.moviepedia.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WatchlistViewModel(
    private val repository: MoviesRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<UserMovies>>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<List<UserMovies>>>
        get() = _uiState


    fun getWatchlist(){
      viewModelScope.launch {
          repository.getWatchListMovies()
              .catch {
                  _uiState.value = UiState.Error(it.message.toString())
              }
              .collect {
                  _uiState.value = UiState.Success(it)
              }
      }
    }

    fun updateWatchlist(id: Long, isOnWatchlist: Boolean){
        viewModelScope.launch {
            repository.addToWatchList(id, isOnWatchlist)
                .collect {
                    if (it){

                    }
                }
        }
    }
}