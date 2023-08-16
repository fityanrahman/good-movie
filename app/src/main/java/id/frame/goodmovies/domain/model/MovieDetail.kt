package id.frame.goodmovies.domain.model

import id.frame.goodmovies.data.remote.credits.CreditsResponse
import id.frame.goodmovies.data.remote.genre.Genre
import id.frame.goodmovies.data.remote.reviews.ReviewsResponse
import id.frame.goodmovies.data.remote.video.VideoResponse

data class MovieDetail (
    val id: Int,
    val backdropPath: String?,
    val genres: List<Genre>,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String,
    val runtime: Int?,
    val title: String,
    val voteAverage: Double,
    val trailer: VideoResponse,
    val reviews: ReviewsResponse,
    val credit: CreditsResponse,
)