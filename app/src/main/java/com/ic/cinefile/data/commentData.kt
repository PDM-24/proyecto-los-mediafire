package com.ic.cinefile.data

import com.google.gson.annotations.SerializedName

data class commentData(

    @SerializedName(value= "movieId")
    val movieId: Int=0,

    @SerializedName(value= "commentText")
    val commentText: String=""

)
