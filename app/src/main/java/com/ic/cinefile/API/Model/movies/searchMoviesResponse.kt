package com.ic.cinefile.API.Model.movies

import com.google.gson.annotations.SerializedName

data class searchMoviesResponse(
    @SerializedName("moviesA")
    val moviesSearch: List<moviesResponse>
)
