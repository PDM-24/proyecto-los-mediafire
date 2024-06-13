package com.ic.cinefile.API.methods

import com.ic.cinefile.API.Model.users.UserLoginResponse
import com.ic.cinefile.API.Model.users.accountCreateResponse
import com.ic.cinefile.data.accountCreateData
import com.ic.cinefile.data.accountRegisterData
import com.ic.cinefile.data.loginUserData
import okhttp3.ResponseBody
import retrofit2.Call

//import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.Headers

interface Methods {
    @Headers(value=["Content-Type:application/json"])
    @POST("api/account/register")
    suspend fun createAccount(
        @Body userregisterData: accountRegisterData
    ): accountCreateResponse

    @POST("api/account/login")
    suspend fun loginAccount(
        @Body userData: loginUserData
    ):Response<UserLoginResponse>
}
