package id.frame.goodmovies.data.remote

import id.frame.goodmovies.common.Constants
import id.frame.goodmovies.data.remote.credits.CreditsResponse
import id.frame.goodmovies.data.remote.genre.GenreResponse
import id.frame.goodmovies.data.remote.movie.MovieResponse
import id.frame.goodmovies.data.remote.movie_detail.MovieDetailDto
import id.frame.goodmovies.data.remote.reviews.ReviewsResponse
import id.frame.goodmovies.data.remote.video.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.GENRE_MOVIE)
    suspend fun getGenres(): GenreResponse

    @GET("${Constants.MOVIE}${Constants.POPULAR_MOVIE}")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): MovieResponse

    @GET(Constants.DISCOVER_MOVIE)
    suspend fun getMovieWithGenres(
        @Query("page") page: Int,
        @Query("with_genres") genreId: Int,
    ): MovieResponse

    @GET(Constants.MOVIE)
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
    ): MovieDetailDto

    @GET(Constants.MOVIE)
    suspend fun getVideo(
        @Path("movie_id") movieId: Int,
        @Path("video") video: String = Constants.TRAILER_MOVIE,
    ): VideoResponse

    @GET(Constants.MOVIE)
    suspend fun getReviews(
        @Path("movie_id") movieId: Int,
        @Path("reviews") video: String = Constants.REVIEW_MOVIE,
        @Query("page") page: Int,
    ): ReviewsResponse

    @GET(Constants.MOVIE)
    suspend fun getCredits(
        @Path("movie_id") movieId: Int,
        @Path("credits") video: String = Constants.CAST_MOVIE,
    ): CreditsResponse

}