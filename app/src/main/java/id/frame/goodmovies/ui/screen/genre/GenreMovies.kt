package id.frame.goodmovies.ui.screen.genre

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import id.frame.goodmovies.ui.components.GenreMovieContainer
import id.frame.goodmovies.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreMovies(
    viewModel: MovieGenresViewModel = hiltViewModel(),
    navController: NavController,
    genreId: Int?,
    genreName: String?
) {
    val movies = viewModel.moviesWithGenres(genreId = genreId ?: 0).collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Genre: $genreName ",
                        fontWeight = FontWeight.Bold
                    )
                },
            )
        },
        modifier = Modifier.statusBarsPadding()
    )
    {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(movies) { item ->
                item?.let { movie ->
                    GenreMovieContainer(movie = movie) { navigatedItem ->
                        navController.navigate(Screen.MovieDetail.route + "/${navigatedItem.id}")
                    }
                }
            }
            movies.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        //when first time response page is loading
                        item { CircularProgressIndicator(color = Color.DarkGray) }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            LinearProgressIndicator(
                                color = Color.Red,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .statusBarsPadding(),
                            )
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        item { Text(text = "Error") }
                    }
                }
            }
        }
    }
}