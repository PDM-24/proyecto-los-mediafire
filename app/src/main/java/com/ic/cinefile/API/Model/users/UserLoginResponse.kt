package com.ic.cinefile.API.Model.users

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(
    @SerializedName(value="message")
    val successfull:String="",

)
