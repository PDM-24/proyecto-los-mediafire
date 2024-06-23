package com.ic.cinefile.API.Model.movies

import com.google.gson.annotations.SerializedName

data class wishListResponse(
    @SerializedName("wishlist")
    val wishlist: List<WishlistItem>
)


data class WishlistItem(
    @SerializedName("movieId")
    val movieId: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("poster")
    val poster: String?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("dateAdded")
    val dateAdded: String?
)


data class wishPostResponse(
    @SerializedName("message")
    val msg:String=""
)