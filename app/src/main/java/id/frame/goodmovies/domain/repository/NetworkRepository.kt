package id.frame.goodmovies.domain.repository

import id.frame.goodmovies.common.Resource
import id.frame.goodmovies.data.remote.credits.CreditsResponse
import id.frame.goodmovies.data.remote.genre.GenreResponse
import id.frame.goodmovies.data.remote.movie.MovieResponse
import id.frame.goodmovies.data.remote.reviews.ReviewsResponse
import id.frame.goodmovies.data.remote.video.VideoResponse
import id.frame.goodmovies.domain.model.HomeList
import id.frame.goodmovies.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    suspend fun getGenres(): GenreResponse

    suspend fun getPopularMovies(page: Int): MovieResponse

    suspend fun getMovieWithGenres(page: Int, genreId: Int): MovieResponse

    fun getMovieById(id: Int): Flow<Resource<MovieDetail>>

    suspend fun getVideo(id: Int): VideoResponse

    suspend fun getReviews(id: Int, page: Int): ReviewsResponse

    suspend fun getCredits(id: Int): CreditsResponse

    fun getHomeMoviews(): Flow<Resource<List<HomeList>>>

}