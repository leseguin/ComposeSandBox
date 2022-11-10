package fr.leane.seguin.data.rest.model

import com.google.gson.annotations.SerializedName
import fr.leane.seguin.data.vo.Movie
import kotlinx.serialization.Serializable

@Serializable
data class RestMovie(
    val id: String? = null,
    @SerializedName("l")
    val title: String? = null,
    @SerializedName("q")
    val type: String? = null,
    @SerializedName("i")
    val image: RestMovieImage? = null
) {
    fun toMovie() =
        if (!id.isNullOrBlank() && !title.isNullOrBlank() && !type.isNullOrBlank() && image?.imageUrl != null) {
            Movie(id, title, type, image.imageUrl)
        } else null
}

@Serializable
data class RestMovieImage(
    val imageUrl: String? = null
)