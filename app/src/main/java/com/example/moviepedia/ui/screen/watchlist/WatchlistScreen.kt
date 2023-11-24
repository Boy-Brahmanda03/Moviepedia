package com.example.moviepedia.ui.screen.watchlist

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviepedia.R
import com.example.moviepedia.di.Injection
import com.example.moviepedia.model.Movie
import com.example.moviepedia.ui.ViewModelFactory
import com.example.moviepedia.ui.common.UiState
import com.example.moviepedia.ui.component.EmptyContent
import com.example.moviepedia.ui.screen.home.MovieList
import com.example.moviepedia.ui.theme.MoviepediaTheme

@Composable
fun WatchlistScreen(
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WatchlistViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getWatchlistMovies()
            }

            is UiState.Success -> {
                WatchlistContent(
                    movies = uiState.data,
                    onWatchlistIconClick = { id, newState ->
                        viewModel.updateMovies(id, newState)
                    },
                    navigateToDetail = navigateToDetail,
                    modifier = modifier
                )
            }

            is UiState.Error -> {
                Toast.makeText(LocalContext.current, uiState.errorMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}

@Composable
fun WatchlistContent(
    movies: List<Movie>,
    onWatchlistIconClick: (Long, Boolean) -> Unit,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    if (movies.isNotEmpty()) {
        MovieList(
            movies = movies,
            onWatchlistIconClick = onWatchlistIconClick,
            navigateToDetail = navigateToDetail,
            modifier = modifier
        )
    } else {
        EmptyContent(contentText = stringResource(R.string.empty_watchlist))
    }
}


@Preview
@Composable
fun WatchlistScreenPreview() {
    MoviepediaTheme {
        WatchlistScreen(
            {}
        )
    }
}