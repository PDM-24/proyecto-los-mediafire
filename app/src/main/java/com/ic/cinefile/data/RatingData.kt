package com.ic.cinefile.data

import com.google.gson.annotations.SerializedName

data class RatingData(
    @SerializedName(value= "movieId")
    val movieId: Int=0,

    @SerializedName(value="rating")
    val rating: Double=0.0

)
