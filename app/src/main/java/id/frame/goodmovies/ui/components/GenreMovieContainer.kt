package id.frame.goodmovies.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import id.frame.goodmovies.common.Constants
import id.frame.goodmovies.data.remote.movie.Movie

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenreMovieContainer(
    movie: Movie,
    onClick: (Movie) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick(movie)
            }
    ) {
        CoilImage(
            imageModel = Constants.IMAGE_THUMBNAIL + movie.poster_path,
            contentScale = ContentScale.Crop,
            shimmerParams = ShimmerParams(
                baseColor = MaterialTheme.colorScheme.background,
                highlightColor = Color.LightGray.copy(alpha = 0.6f),
                durationMillis = 350, dropOff = 0.65f, tilt = 20f
            ),
            circularReveal = CircularReveal(duration = 350),
            failure = { Text(text = "Image request failed!") },
            modifier = Modifier
                .height(90.dp)
                .width(90.dp)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp)),
        )
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .height(90.dp),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star, contentDescription = "",
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.padding(start = 4.dp))
                Text(
                    text = "${movie.vote_average}/10",
                    color = Color.LightGray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

    }
}