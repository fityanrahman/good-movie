package id.frame.goodmovies.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import id.frame.goodmovies.R
import id.frame.goodmovies.domain.model.HomeList
import id.frame.goodmovies.ui.components.GenreContainer
import id.frame.goodmovies.ui.components.Header
import id.frame.goodmovies.ui.components.MovieContainer
import id.frame.goodmovies.ui.navigation.Screen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    genreViewModel: GenreViewModel = hiltViewModel(),
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
) {

    val genres = genreViewModel.stateGenre.value

    val state = viewModel.state.value
    val homeItems = state.homeList

    Scaffold(
        topBar = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    fontWeight = FontWeight.Black,
                    fontSize = 32.sp,
                    color = Color.White
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                homeItems.forEach { homeList ->
                    when (homeList) {
                        is HomeList.Genres -> {
                            item {
                                Header(
                                    header = stringResource(R.string.popular_genres),
                                )
                            }
                            item {
                                LazyRow {
                                    items(homeList.genres, key = { it.id }) { genre ->
                                        GenreContainer(genre) {navigatedItem ->
                                            navController.navigate(Screen.MovieWithGenres.route + "/${genre.id}/${genre.name}")
                                        }
                                    }
                                }
                            }
                        }

                        is HomeList.Popular -> {
                            item {
                                Header(
                                    header = stringResource(R.string.popular_movies),
                                )
                            }
                            items(items = homeList.popular) { popular ->
                                MovieContainer(
                                    popular = popular,
                                    genres = genres
                                ) { navigatedItem ->
                                    navController.navigate(Screen.MovieDetail.route + "/${navigatedItem.id}")
                                }
                            }
                        }
                    }
                }

            }
            if (state.error.isNotBlank()) {
                Text(text = "${state.error} ds")
            }
            if (state.isLoading) {
                CircularProgressIndicator()
            }
        }
    }

}