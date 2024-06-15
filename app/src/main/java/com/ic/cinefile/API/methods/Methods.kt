package com.ic.cinefile.API.methods

import com.ic.cinefile.API.Model.movies.homeUserResponse
import com.ic.cinefile.API.Model.users.UserLoginResponse
import com.ic.cinefile.API.Model.users.accountCreateResponse
import com.ic.cinefile.data.accountLoginData
import com.ic.cinefile.data.accountRegisterData

//import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface Methods {
    @Headers(value=["Content-Type:application/json"])
    @POST("api/account/register")
    suspend fun createAccount(
        @Body userregisterData: accountRegisterData
    ): Response<accountCreateResponse>

    @Headers(value=["Content-Type:application/json"])
    @POST("api/account/login")
    suspend fun loginAccount(
        @Body userLoginData: accountLoginData
    ):UserLoginResponse

    @GET("api/account/user/home")
    @Headers(value = ["Content-Type:application/json"])  // Fijo para Content-Type
    suspend fun getUserHome(
        @Header("Authorization") token: String            // Dinámico para Authorization
    ): Response<homeUserResponse>

}
