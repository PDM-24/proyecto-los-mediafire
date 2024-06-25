package com.ic.cinefile.API.Model.movies

import com.google.gson.annotations.SerializedName

data class ActorName(
    @SerializedName("name")
    val name: String,

    @SerializedName("profileUrl")
    val profileUrl: String
)

data class actorNameResponse(
    @SerializedName("actors")
    val actors: List<ActorName>
)