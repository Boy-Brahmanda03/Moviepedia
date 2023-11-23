package com.example.moviepedia.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviepedia.di.Injection
import com.example.moviepedia.model.Movie
import com.example.moviepedia.ui.ViewModelFactory
import com.example.moviepedia.ui.common.UiState
import com.example.moviepedia.ui.theme.MoviepediaTheme

@Composable
fun DetailScreen(
    id: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getMovieById(id)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    movie = data,
                    onWatchlistIconClick = { id, newState ->
                        viewModel.updateMovies(id, newState)
                    }
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    movie: Movie,
    onWatchlistIconClick: (Long, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(id = movie.imageCover),
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(296.dp)
                    .testTag("scroll")
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = movie.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)
                )
                Text(
                    text = movie.rating.toString(),
                    modifier = Modifier
                        .padding(start = 2.dp, end = 8.dp)
                )
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)
                )
                Text(
                    text = movie.releasedYear.toString(),
                    overflow = TextOverflow.Visible,
                    modifier = Modifier
                        .padding(start = 4.dp)
                )
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)
                )
                Text(
                    text = movie.director,
                    overflow = TextOverflow.Visible,
                    modifier = Modifier
                        .padding(start = 4.dp)
                )
            }
            Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp))
            Text(
                text = movie.storyline,
                fontSize = 16.sp,
                lineHeight = 28.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
        }
        IconButton(
            onClick = {
                onWatchlistIconClick(movie.id, movie.isWatchlist)
            },
            modifier = Modifier
                .padding(end = 16.dp, top = 8.dp)
                .align(Alignment.TopEnd)
                .clip(CircleShape)
                .size(40.dp)
                .background(Color.White)
                .testTag("add_remove_favorite")
        ) {
            Icon(
                imageVector = if (!movie.isWatchlist) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                contentDescription = if (!movie.isWatchlist) "Test"
                else "huh",
//                tint = if (!isFavorite) Color.Black else Color.Red
            )
        }
    }
}

@Preview(showSystemUi = true, device = "id:pixel_6")
@Composable
fun DetailScreenPreview() {
    MoviepediaTheme {
        DetailScreen(3)
    }
}