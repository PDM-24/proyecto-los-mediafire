package com.ic.cinefile.API.Model.movies

import com.google.gson.annotations.SerializedName

data class topMoviesResponse(
    @SerializedName(value= "topRatedMovies")
    val topRatedMovies: List<MovieDetails>

)

data class MovieDetails(

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
    val trailerUrl: String,

    @SerializedName("averageRating")
    val averageRating: Double,

    @SerializedName("actors")
    val actors: List<ActorResponse>
)

