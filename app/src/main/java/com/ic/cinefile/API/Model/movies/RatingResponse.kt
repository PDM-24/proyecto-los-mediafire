package com.ic.cinefile.API.Model.movies

import com.google.gson.annotations.SerializedName

data class RatingResponse(
    @SerializedName(value="message")
    val message: String

)

data class AverageRatingResponse(
    @SerializedName(value="averageRating")
    val averageRating: Double
)
