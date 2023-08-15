package id.frame.goodmovies.data.remote.reviews

data class ReviewsResponse(
    val id: Int,
    val page: Int,
    val results: List<Reviews>,
    val total_pages: Int,
    val total_results: Int
)