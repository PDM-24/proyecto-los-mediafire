package com.ic.cinefile.API.Model.movies

import com.google.gson.annotations.SerializedName

data class recentMoviesResponse(
    @SerializedName("moviesRecent")
    val moviesRecent: List<recentMovies>
)

data class recentMovies(

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
