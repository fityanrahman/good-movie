package id.frame.goodmovies.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.frame.goodmovies.data.remote.reviews.Reviews
import id.frame.goodmovies.domain.repository.NetworkRepository
import retrofit2.HttpException
import java.io.IOException

class ReviewPagingSource(
    private val networkRepository: NetworkRepository,
    private val source: Source
) : PagingSource<Int, Reviews>() {
    override fun getRefreshKey(state: PagingState<Int, Reviews>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Reviews> {
        return try {
            val nextPageNumber = params.key ?: 1

            val response = when (source) {
                is Source.Reviews -> networkRepository.getReviews(
                    page = nextPageNumber,
                    id = source.movieId ?: 0
                )
            }

            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = response.page.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    sealed class Source(val movieId: Int? = null) {
        class Reviews(movieId: Int) : Source(movieId = movieId)
    }
}