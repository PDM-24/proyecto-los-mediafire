package com.ic.cinefile.API.Model.users

import com.google.gson.annotations.SerializedName
import com.ic.cinefile.API.Model.movies.UserResponse

data class NotificationResponse(
    @SerializedName(value= "_id") val id: String="",
    @SerializedName(value= "userId") val user: UserResponse,
    @SerializedName(value= "message") val message: String="",
    @SerializedName(value= "read") val read: Boolean,
    @SerializedName(value= "createdAt") val createdAt: String="",
    @SerializedName(value= "__v") val version: Int=0
)

data class MarkAsReadResponse(
    val message: String
)