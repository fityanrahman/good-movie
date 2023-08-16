package id.frame.goodmovies.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import id.frame.goodmovies.R
import id.frame.goodmovies.common.Constants
import id.frame.goodmovies.ui.screen.detail.components.MovieCast
import id.frame.goodmovies.ui.screen.detail.components.MovieDescription
import id.frame.goodmovies.ui.screen.detail.components.MovieGenres
import id.frame.goodmovies.ui.screen.detail.components.MovieReviews
import kotlin.time.Duration.Companion.minutes

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    navController: NavController,

    ) {

    val state = viewModel.state.value

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding()
    ) {
        state.movie?.let { movie ->

            val genres = remember {
                mutableStateOf(movie.genres)
            }

            val (video, poster, originalTitle) = createRefs()
            val (rateScore, genresFlowRow, row, description, cast, reviews) = createRefs()


            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .constrainAs(video){},
                factory =
                {
                var view = YouTubePlayerView(it)
                val fragment = view.addYouTubePlayerListener(
                    object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            super.onReady(youTubePlayer)
                            youTubePlayer.loadVideo(movie.trailer.results[0].key, 0f)
                        }
                    }
                )
                view
            })

            CoilImage(
                imageModel = Constants.IMAGE_POSTER + movie.posterPath,
                modifier = Modifier
                    .width(120.dp)
                    .height(180.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .constrainAs(poster) {
                        top.linkTo(parent.top, margin = 240.dp)
                        linkTo(start = parent.start, end = parent.end)
                    },
                circularReveal = CircularReveal(duration = 350),
            )

            Text(
                text = movie.title,
                modifier = Modifier.constrainAs(originalTitle) {
                    linkTo(start = parent.start, end = parent.end)
                    top.linkTo(poster.bottom, margin = 4.dp)
                }
            )
            Row(
                modifier = Modifier.constrainAs(rateScore) {
                    top.linkTo(originalTitle.bottom, margin = 4.dp)
                    linkTo(start = parent.start, end = parent.end)
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star, contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(text = "${movie.voteAverage} / 10", color = Color.LightGray)
            }


            MovieGenres(
                genres = genres.value,
                modifier = Modifier.constrainAs(genresFlowRow) {
                    linkTo(start = parent.start, end = parent.end)
                    top.linkTo(rateScore.bottom, margin = 4.dp)
                },
//                navController = navController
            )
            Row(
                modifier = Modifier
                    .constrainAs(row) {
                        top.linkTo(genresFlowRow.bottom, margin = 8.dp)
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.duration),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${movie.runtime?.minutes}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        )
                }
                Column {
                    Text(
                        text = stringResource(R.string.release_date),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = movie.releaseDate,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                    )
                }
            }
            MovieDescription(
                description = "${movie.overview}",
                modifier = Modifier.constrainAs(description) {
                    top.linkTo(row.bottom, margin = 8.dp)
                    linkTo(start = parent.start, end = parent.end)
                }
            )
            MovieCast(
                cast = movie.credit.cast,
                modifier = Modifier.constrainAs(cast) {
                    top.linkTo(description.bottom, margin = 8.dp)
                    linkTo(start = parent.start, end = parent.end)
                }
            )
            MovieReviews(
                review = movie.reviews.results,
                modifier = Modifier.constrainAs(reviews) {
                    top.linkTo(cast.bottom, margin = 8.dp)
                    linkTo(start = parent.start, end = parent.end)
                }
            )

        }
        val (circularProgress, errorString) = createRefs()
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.constrainAs(circularProgress) {
                    linkTo(start = parent.start, end = parent.end)
                    linkTo(top = parent.top, bottom = parent.bottom)
                },
                color = Color.DarkGray
            )
        }
        if (state.error.isNotBlank()) {
            Text(text = state.error,
                modifier = Modifier.constrainAs(errorString) {
                    linkTo(start = parent.start, end = parent.end)
                    linkTo(top = parent.top, bottom = parent.bottom)
                })
        }

        }

    }
