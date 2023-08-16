package id.frame.goodmovies.ui.screen.detail.components

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.ui.unit.sp
import id.frame.goodmovies.data.remote.genre.Genre

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieGenres(
    modifier: Modifier = Modifier,
    genres: List<Genre>,
//    navController: NavController
) {
    FlowRow(
        modifier = modifier,
    ) {
        genres.forEach { genre ->
            TextButton(
                onClick = {
//                    navController
//                        .navigate(Screen.MovieWithGenres.route + "/${genre.id}/${genre.name}")
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                ),
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
            ) {
                Text(text = genre.name, fontSize = 12.sp)
            }
        }
    }
}