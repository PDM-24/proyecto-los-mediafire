package com.ic.cinefile.API.Model.movies
import com.google.gson.annotations.SerializedName

data class mostViewMoviesResponse(

    @SerializedName("moviesMostViews")
    val moviesMostViews: List<moviesMostView>

)

data class moviesMostView(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster")
    val posterUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("duracion")
    val duration: Int,
    @SerializedName("fecha_lanzamiento")
    val releaseDate: String,
    @SerializedName("genero")
    val genres: String,
    @SerializedName("descripcion")
    val description: String,
    @SerializedName("trailer")
    val trailerUrl: String
)


///*data class mostViewMoviesResponse(
//
//    @SerializedName("movies")
//    val movies: Map<String, List<Movies>>
//
//)*/