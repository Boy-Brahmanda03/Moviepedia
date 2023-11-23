package com.example.moviepedia.ui.screen.watchlist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviepedia.di.Injection
import com.example.moviepedia.model.UserMovies
import com.example.moviepedia.ui.ViewModelFactory
import com.example.moviepedia.ui.common.UiState
import com.example.moviepedia.ui.component.MovieItemList
import com.example.moviepedia.ui.theme.MoviepediaTheme

@Composable
fun WatchlistScreen(
    viewModel: WatchlistViewModel = viewModel (
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState().value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getWatchlist()
            }
            is UiState.Success -> {
                WatchlistContent(userMovies = uiState.data)
            }
            is UiState.Error -> {

            }
        }

    }
}

@Composable
fun WatchlistContent(
    userMovies: List<UserMovies>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(userMovies, key = { it.movie.id }){userMovie ->
            MovieItemList(
                userMovies = userMovie,
                addToWatchlist = {id: Long, isOnWatchlist: Boolean ->  }
            )
        }
    }
}

@Preview
@Composable
fun WatchlistScreenPreview() {
    MoviepediaTheme {
        WatchlistScreen()
    }
}