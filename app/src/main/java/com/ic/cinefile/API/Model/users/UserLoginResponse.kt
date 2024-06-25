package com.ic.cinefile.API.Model.users

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(
    @SerializedName(value = "message")
    val message: String = "",

    @SerializedName(value = "token")
    val token: String = "",

    @SerializedName(value="role")
    val role : String=""
)