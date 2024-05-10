package com.ic.cinefile.API.Model.users

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(

    //aca van los campos al obtener /o al crear usuario
    @SerializedName("email")
    var email:String,
    @SerializedName("password")
    var password:String,

)
