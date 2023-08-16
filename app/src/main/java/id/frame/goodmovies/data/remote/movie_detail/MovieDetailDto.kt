package id.frame.goodmovies.data.remote.movie_detail

import id.frame.goodmovies.data.remote.credits.CreditsResponse
import id.frame.goodmovies.data.remote.genre.Genre
import id.frame.goodmovies.data.remote.reviews.ReviewsResponse
import id.frame.goodmovies.data.remote.video.VideoResponse
import id.frame.goodmovies.domain.model.MovieDetail

data class MovieDetailDto(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val reviews: ReviewsResponse,
    val videos: VideoResponse,
    val credits: CreditsResponse
)

fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        id = id,
        backdropPath = backdrop_path,
        genres = genres,
        overview = overview,
        posterPath = poster_path,
        releaseDate = release_date,
        runtime = runtime,
        title = title,
        voteAverage = vote_average,
        trailer = videos,
        reviews = reviews,
        credit = credits
    )
}