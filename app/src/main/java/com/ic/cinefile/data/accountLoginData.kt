package com.ic.cinefile.data

import com.google.gson.annotations.SerializedName

data class accountLoginData(


    @SerializedName(value="email")
    var email:String="",

    @SerializedName(value="password")
    var password:String="",


)
