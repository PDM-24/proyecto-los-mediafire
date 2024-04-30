package com.ic.cinefile.API.methods

import com.ic.cinefile.API.classMethods.users.User
import com.ic.cinefile.API.classMethods.users.UserResponse

import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Methods {
     @POST("api/account/register")
     suspend fun createAccount(
         @Body user: User
     ): Response<UserResponse>
}