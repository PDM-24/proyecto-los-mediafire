package com.ic.cinefile.API.Model.movies

import com.google.gson.annotations.SerializedName

data class getCommentResponse(
@SerializedName(value= "_id") val id: String="",
@SerializedName(value= "movieId") val movieId: Int,
@SerializedName(value= "userId") val user: UserResponse,
@SerializedName(value= "parentId") val parentId: String? = null,

@SerializedName(value= "commentText") val commentText: String="",
@SerializedName(value= "createdAt") val createdAt: String, // Manteniendo como String si se parsea a LocalDateTime después
@SerializedName("updatedAt") val updatedAt: String, // Manteniendo como String si se parsea a LocalDateTime después
@SerializedName("__v") val version: Int
)

data class UserResponse(
    @SerializedName("_id") val id: String,
    @SerializedName(value="username") val username: String="",
    @SerializedName(value="avatar") val avatar: String=""
)



data class ReplyComment(
    @SerializedName("_id") val id: String,
    @SerializedName("movieId") val movieId: Int,
    @SerializedName("userId") val user: User,
    @SerializedName("commentText") val commentText: String,
    @SerializedName("parentId") val parentId: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("__v") val version: Int
)

