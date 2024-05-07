package com.ic.cinefile.API.methods

import com.ic.cinefile.API.classMethods.users.User

//import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import com.ic.cinefile.API.classMethods.users.UserResponse as UserResponse

interface Methods {
     @POST("api/account/register")
     suspend fun createAccount(
         @Body user: User
     ): Response<UserResponse>
}
