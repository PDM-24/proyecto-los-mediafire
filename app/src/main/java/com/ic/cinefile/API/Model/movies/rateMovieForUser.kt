package com.ic.cinefile.API.Model.movies

import com.google.gson.annotations.SerializedName

// Data class for the rating
data class UserRating(
    @SerializedName("userId") val userId: String,
    @SerializedName("rating") val rating: Int
)

// Data class for the response
data class UserRatingResponse(
    @SerializedName("userRating") val userRating: UserRating
)