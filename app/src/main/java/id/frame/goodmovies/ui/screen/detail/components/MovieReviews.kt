package id.frame.goodmovies.ui.screen.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.frame.goodmovies.data.remote.reviews.Reviews

@Composable
fun MovieReviews(
    review: List<Reviews>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Reviews",
            modifier = Modifier
                .padding(start = 16.dp, top = 12.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
        )
        LazyColumn(
            modifier = modifier
                .padding(8.dp)
                .height(600.dp)
        ) {
            items(review) { reviewItem ->
                Column(modifier = Modifier.padding(vertical = 16.dp)) {
                    Text(
                        text = reviewItem.author,
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = reviewItem.content,
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }
        }

    }
}