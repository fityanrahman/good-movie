package id.frame.goodmovies.ui.screen.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.frame.goodmovies.data.paging.MoviePagingSource
import id.frame.goodmovies.data.remote.movie.Movie
import id.frame.goodmovies.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieGenresViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    fun moviesWithGenres(genreId: Int): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                MoviePagingSource(
                    networkRepository,
                    MoviePagingSource.Source.MovieWithGenres(genreId = genreId)
                )
            }
        ).flow.cachedIn(viewModelScope)
    }
}