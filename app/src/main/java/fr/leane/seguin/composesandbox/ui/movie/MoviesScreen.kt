@file:OptIn(ExperimentalFoundationApi::class)

package fr.leane.seguin.composesandbox.ui.movie

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.leane.seguin.data.vo.Movie

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesScreen(viewModel: MovieViewModel = hiltViewModel()) {

    val screenState by viewModel.state.collectAsState()
    val movies by viewModel.movies.collectAsState()
    val currentSearch by viewModel.search.collectAsState()

    if (movies.isEmpty()) {
        // TODO Empty view
    } else {
        LazyColumn {
            stickyHeader {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = currentSearch,
                        onValueChange = { newValue ->
                            viewModel.updateSearch(newValue)
                        },
                        placeholder = {
                            Text(text = "Name of movie or series")
                        })

                    AnimatedVisibility(visible = screenState.isLoading()) {
                        CircularProgressIndicator()
                    }
                }

            }
            items(movies) { movie ->
                MovieCard(movie = movie, modifier = Modifier.padding(12.dp))
            }
        }
    }
}

@Composable
private fun MovieCard(movie: Movie, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth()) {
        Text(text = movie.title, modifier = Modifier.padding(8.dp))
    }
}