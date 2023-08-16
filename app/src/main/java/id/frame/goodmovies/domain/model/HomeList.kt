package id.frame.goodmovies.domain.model

import id.frame.goodmovies.data.remote.genre.Genre
import id.frame.goodmovies.data.remote.movie.Movie

sealed class HomeList {
    data class Genres(val genres: List<Genre>) : HomeList()
    data class Popular(val popular: List<Movie>) : HomeList()
}