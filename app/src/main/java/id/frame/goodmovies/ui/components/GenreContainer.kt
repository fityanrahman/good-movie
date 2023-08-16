package id.frame.goodmovies.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import id.frame.goodmovies.data.remote.genre.Genre

@Composable
fun GenreContainer(
    genre: Genre,
    modifier: Modifier = Modifier,
    onClick: (Genre) -> Unit
) {
    TextButton(
        onClick = { onClick(genre) },
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Text(
            genre.name,
            style = MaterialTheme.typography.labelMedium
        )
    }
}