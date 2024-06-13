package com.ic.cinefile.API.Model.users

import com.google.gson.annotations.SerializedName


data class accountCreateResponse(

    @SerializedName(value="message")
    val successfull:String="",

    @SerializedName(value="message")
    val error:String=""
)
