package id.frame.goodmovies.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import id.frame.goodmovies.R
import id.frame.goodmovies.domain.model.HomeList
import id.frame.goodmovies.ui.components.Header

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value
    val homeItems = state.homeList

    Column(
        modifier = Modifier.statusBarsPadding()
    ) {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            homeItems.forEach { homeList ->
                when (homeList) {
                    is HomeList.Genres -> {
                        item {
                            Header(
                                header = stringResource(R.string.popular_genres),
                                more = stringResource(R.string.expand),
                                onClickSeeMore = {}
                            )
                        }
                    }

                    is HomeList.Popular -> {
                        item {
                            Header(
                                header = stringResource(R.string.popular_movies),
                            )
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