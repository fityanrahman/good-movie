package id.frame.goodmovies.data.remote

import id.frame.goodmovies.common.Constants
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.GENRE_MOVIE)
    suspend fun getGenres()
}