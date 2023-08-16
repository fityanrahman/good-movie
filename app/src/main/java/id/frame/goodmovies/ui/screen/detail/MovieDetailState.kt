package id.frame.goodmovies.ui.screen.detail

import id.frame.goodmovies.domain.model.MovieDetail

data class MovieDetailState(
    val movie: MovieDetail? = null,
    val isLoading: Boolean = false,
    val error: String = "",
    val isBookmarked: Boolean = false
)