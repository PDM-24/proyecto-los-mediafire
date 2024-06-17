package com.ic.cinefile.API.Model.movies
import com.google.gson.annotations.SerializedName

data class mostViewMoviesResponse(

    @SerializedName("moviesMostViews")
    val moviesMostViews: List<moviesResponse>

)

///*data class mostViewMoviesResponse(
//
//    @SerializedName("movies")
//    val movies: Map<String, List<Movies>>
//
//)*/