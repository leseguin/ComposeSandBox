package fr.leane.seguin.data.rest

import fr.leane.seguin.data.rest.model.RestMoviesSearch
import retrofit2.http.GET
import retrofit2.http.Query

interface ImdbApi {

    @GET("./auto-complete")
    suspend fun getMoviesWithSearch(@Query("q") search: String): RestMoviesSearch
}