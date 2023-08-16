package id.frame.goodmovies.data.repository

import id.frame.goodmovies.common.Resource
import id.frame.goodmovies.data.remote.ApiService
import id.frame.goodmovies.data.remote.credits.CreditsResponse
import id.frame.goodmovies.data.remote.genre.GenreResponse
import id.frame.goodmovies.data.remote.movie.MovieResponse
import id.frame.goodmovies.data.remote.movie_detail.toMovieDetail
import id.frame.goodmovies.data.remote.reviews.ReviewsResponse
import id.frame.goodmovies.data.remote.video.VideoResponse
import id.frame.goodmovies.domain.model.HomeList
import id.frame.goodmovies.domain.model.MovieDetail
import id.frame.goodmovies.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val api: ApiService,
) : NetworkRepository {

    override suspend fun getGenres(): GenreResponse {
        return api.getGenres()
    }

    override suspend fun getPopularMovies(page: Int): MovieResponse {
        return api.getPopularMovies(page = page)
    }

    override suspend fun getMovieWithGenres(page: Int, genreId: Int): MovieResponse {
        return api.getMovieWithGenres(page = page, genreId = genreId)
    }

    override suspend fun getVideo(id: Int): VideoResponse {
        return api.getVideo(movieId = id)
    }

    override suspend fun getReviews(id: Int, page: Int): ReviewsResponse {
        return api.getReviews(movieId = id, page = page)
    }

    override suspend fun getCredits(id: Int): CreditsResponse {
        return api.getCredits(movieId = id)
    }

    override fun getMovieById(id: Int): Flow<Resource<MovieDetail>> = flow {
        emit(Resource.Loading())
        try {
            val movie = api.getMovieDetail(movieId = id).toMovieDetail()
            emit(Resource.Success(movie))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        }

    }

    override fun getHomeMovies(): Flow<Resource<List<HomeList>>> = flow {
        emit(Resource.Loading())
        try {
            val genres = api.getGenres().genres
            val popular = api.getPopularMovies(page = 1).results

            val list = listOf(HomeList.Genres(genres), HomeList.Popular(popular))

            emit(Resource.Success(list))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An uncexpected error occured"))
        }
    }
}