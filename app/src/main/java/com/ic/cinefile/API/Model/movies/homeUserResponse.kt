package com.ic.cinefile.API.Model.movies

import com.google.gson.annotations.SerializedName

data class homeUserResponse(
    @SerializedName("user")
    val user: User,
    @SerializedName("movies")
    val movies: Map<String, List<Movies>>

)

data class User(
    @SerializedName("username")
    val username: String,
    @SerializedName("year_nac")
    val yearOfBirth: String,
    @SerializedName("genere")
    val gender: String,
    @SerializedName("movie_genere")
    val movieGenres: List<String>,
    @SerializedName("avatar")
    val avatarUrl: String
)


data class Movies(
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
