package id.frame.goodmovies.data.remote.credits

data class CreditsResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)