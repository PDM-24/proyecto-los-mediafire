package com.ic.cinefile.API.Model.movies

import com.google.gson.annotations.SerializedName

data class recentMoviesResponse(
    @SerializedName("moviesRecent")
    val moviesRecent: List<moviesResponse>
)


