package com.ic.cinefile.API.Model.movies

import com.google.gson.annotations.SerializedName

data class homeUserResponse(
    @SerializedName("user")
    val user: User,
    @SerializedName("movies")
    val movies: Map<String, List<moviesResponse>>

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


