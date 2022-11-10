package fr.leane.seguin.data.rest.model

import kotlinx.serialization.Serializable

@Serializable
class RestMoviesSearch(
    private val d: List<RestMovie>
) {
    fun toMovies() = d
}