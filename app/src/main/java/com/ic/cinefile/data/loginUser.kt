package com.ic.cinefile.data

import com.google.gson.annotations.SerializedName

class loginUserData(email:String,password:String) {
   @SerializedName("email")
    private var email: String

    @SerializedName("password")

    private var password: String

init {

this.email=email
this.password=password
}

public fun setEmail(email:String){

this.email=email
}
    public fun setPassword(password:String){

        this.password=password
    }

    public fun getEmail(): String {
        return email
    }
    public fun getPassword():String{
        return  password
    }
}