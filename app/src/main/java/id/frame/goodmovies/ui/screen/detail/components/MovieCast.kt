package id.frame.goodmovies.ui.screen.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import id.frame.goodmovies.common.Constants
import id.frame.goodmovies.data.remote.credits.Cast

@Composable
fun MovieCast(
    cast: List<Cast>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Cast",
            modifier = Modifier
                .padding(start = 16.dp, top = 12.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
        )
        LazyRow(modifier = modifier.padding(8.dp)) {
            items(cast) { castItem ->
                Column(
                    modifier = modifier.height(150.dp).width(120.dp).padding(4.dp),
                ) {
                    CoilImage(
                        imageModel = Constants.IMAGE_THUMBNAIL + castItem.profile_path,
                        circularReveal = CircularReveal(duration = 350),
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .size(85.dp)
                            .clip(shape = CircleShape), alignment = Alignment.Center
                    )
                    Text(text = castItem.name, overflow = TextOverflow.Ellipsis)
                    Text(
                        text = castItem.character, color = Color.LightGray,
                        fontSize = 12.sp,
                    )
                }
            }
        }
    }
}