package com.ic.cinefile.API.Model.movies

import com.google.gson.annotations.SerializedName

data class rateMoveResponse(
    @SerializedName("ratedMovies") val ratedMovies: List<Movie>

)

data class Movie(
    @SerializedName("movieId") val movieId: String,
    @SerializedName("title") val title: String,
    @SerializedName("poster") val poster: String,
    @SerializedName("releaseDate") val releaseDate:String,
    @SerializedName("genres") val genres:String,
    @SerializedName("averageRating") val averageRating: Double
)
