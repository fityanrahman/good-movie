package id.frame.goodmovies.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun Header(
    header: String,
    more: String? = null,
    onClickSeeMore: (Unit) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = header,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        )
        more?.let {
            Text(
                text = more,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal),
                modifier = Modifier
                    .clickable {
                        onClickSeeMore(Unit)
                    }
            )
        }
    }

}