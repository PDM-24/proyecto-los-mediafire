package com.ic.cinefile.API.Model.movies

import com.google.gson.annotations.SerializedName

data class moviesResponse(

    @SerializedName("id")
    val id: Int,
    @SerializedName("poster")
    val posterUrl: String,
    @SerializedName("title")
    val title: String?,
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
    @SerializedName("actors")
val actors: List<ActorResponse>,

    @SerializedName("averageRating")
    val averageRating: Double,
    @SerializedName("userRating") val userRating: Int?

)


data class ActorResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("profileUrl")
    val profileUrl: String?
)
