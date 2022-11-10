package fr.leane.seguin.data.repository

import fr.leane.seguin.data.rest.ImdbApi
import fr.leane.seguin.data.vo.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val imdbApi: ImdbApi
) {
    suspend fun getMovieForSearch(search: String): List<Movie> {
        return imdbApi.getMoviesWithSearch(search).toMovies().mapNotNull { it.toMovie() }
    }
}