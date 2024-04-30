package com.ic.cinefile.API.methods

import com.ic.cinefile.API.classMethods.User
import com.ic.cinefile.API.classMethods.UserResponse
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface POSTMethods {
     @POST("api/account/register")
     suspend fun createAccount(
         @Body user: User
     ): Response<UserResponse>
}