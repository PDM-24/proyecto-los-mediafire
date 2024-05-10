package com.ic.cinefile.API.methods

import com.ic.cinefile.API.Model.users.UserLoginResponse
import com.ic.cinefile.data.loginUserData
import okhttp3.ResponseBody
import retrofit2.Call

//import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

interface Methods {
     @POST("api/account/register")
     suspend fun createAccount(
         @Body userData: loginUserData
     ): Response<loginUserData>


     @POST("api/account/login")
     suspend fun loginAccount(
         @Body userData: loginUserData
     ):Response<UserLoginResponse>
}
