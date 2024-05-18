package com.ic.cinefile.API.Model.users

import android.media.Image
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class accountCreateResponse(
    @SerializedName("username")
    var username:String,

    @SerializedName("email")
    var email:String,

    @SerializedName("password")
    var password:String,

    @SerializedName("year_nac")
    var year_nac:String,

    @SerializedName("genere")
    var genere:String,

    @SerializedName("movie_genere")
    var movie_genere:List<String>,

    @SerializedName("avatar")
    var avatar: String,
)
