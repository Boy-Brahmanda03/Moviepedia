package com.example.moviepedia.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviepedia.ui.theme.MoviepediaTheme

@Composable
fun SectionText(
    titleSection: String,
    modifier: Modifier = Modifier
) {
   Text(
       text = titleSection,
       style= MaterialTheme.typography.titleMedium,
       modifier = modifier
           .fillMaxWidth()
           .padding(8.dp)
   )
}


@Preview(showBackground = true)
@Composable
fun SectionTextPreview() {
    MoviepediaTheme {
        SectionText("Trending")
    }
}