package com.ic.cinefile.API.Model.users

import com.google.gson.annotations.SerializedName
import com.ic.cinefile.API.Model.movies.UserResponse

data class NotificationResponse(
    @SerializedName("_id") val id: String="",
    @SerializedName("userId") val user: UserId,
    @SerializedName("message") val message: String="",
    @SerializedName("read") val read: Boolean,
    @SerializedName("avatar") val avatar: String="",
    @SerializedName("parentId") val parentId: String="",
    @SerializedName("createdAt") val createdAt: String="",

    @SerializedName("__v") val version: Int=0
)

data class MarkAsReadResponse(
    val message: String
)


data class UserId(
    @SerializedName("_id")
    val id: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("avatar")
    val avatar: String // Debes reemplazar "insertar foto" con la URL real de la foto
)

