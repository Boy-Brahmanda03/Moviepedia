package com.example.moviepedia.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviepedia.di.Injection
import com.example.moviepedia.model.UserMovies
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
                viewModel.getDetailMovieById(id)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(data)
            }

            is UiState.Error -> {

            }
        }
    }

}

@Composable
fun DetailContent(
    userMovies: UserMovies,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row {
            Image(
                painter = painterResource(id = userMovies.movie.imageCover),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(200.dp)
                    .padding(8.dp)
            )
            Column (
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Text(
                    text = userMovies.movie.title,
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = userMovies.movie.releasedYear.toString())
                    Text(text = userMovies.movie.duration)
                }
            }
        }

        Text(
            text = "Storyline",
            fontWeight = FontWeight.SemiBold
        )
        Text(text = userMovies.movie.storyline)

        Row (
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Director",
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = userMovies.movie.director)
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Rating",
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.padding(horizontal = 14.dp))
            Text(text = userMovies.movie.rating.toString())
        }
    }
}

@Preview(showSystemUi = true, device = "id:pixel_6")
@Composable
fun DetailScreenPreview() {
    MoviepediaTheme {
        DetailScreen(1)
    }
}