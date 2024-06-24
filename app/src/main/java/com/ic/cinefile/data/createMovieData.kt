package com.ic.cinefile.data

import com.google.gson.annotations.SerializedName

data class createMovieData(

    @SerializedName(value="title")
    val title: String="",

    @SerializedName(value="synopsis")
    val synopsis: String="",

    @SerializedName(value="duration")
    val duration: String="",

    @SerializedName(value="actors")
    val actors: List<Actor> = listOf(),

    @SerializedName(value="coverPhoto")
    val coverPhoto: String="",

    @SerializedName(value="categories")
    val categories: List<String> = listOf(),
)


data class Actor(

    @SerializedName(value="name")
    val name: String="",

    @SerializedName(value="profileUrl")
    val profileUrl: String=""
)