package com.ic.cinefile.API.methods

import com.ic.cinefile.API.Model.movies.homeUserResponse
import com.ic.cinefile.API.Model.movies.mostViewMoviesResponse
import com.ic.cinefile.API.Model.movies.moviesResponse
import com.ic.cinefile.API.Model.movies.recentMoviesResponse
import com.ic.cinefile.API.Model.movies.searchMoviesResponse
import com.ic.cinefile.API.Model.users.UserLoginResponse
import com.ic.cinefile.API.Model.users.accountCreateResponse
import com.ic.cinefile.data.accountLoginData
import com.ic.cinefile.data.accountRegisterData
import com.ic.cinefile.data.searchMoviesData

//import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

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


    @Headers("Content-Type: application/json")
    @GET("api/account/user/home")
    suspend fun getUserHome(
        @Header("Authorization") authorization: String
    ): Response<homeUserResponse>


    @Headers("Content-Type: application/json")
    @GET("api/movies/mostViewed")
    suspend fun getMostViewMovies(
        @Header("Authorization") authorization: String
    ): Response<mostViewMoviesResponse>

    @Headers("Content-Type: application/json")
    @GET("api/movies/recentMovies")
    suspend fun getRecentMovies(
        @Header("Authorization") authorization: String
    ): Response<recentMoviesResponse>

    @Headers("Content-Type: application/json")
    @GET("api/movies/search/")
    suspend fun searchMovies(
        @Header("Authorization") authorization: String,
        @Query("title") title: String
    ): Response<searchMoviesResponse>


    @Headers("Content-Type: application/json")
    @GET("api/movies/moviesId/{id}")
    suspend fun getMovieById(
//        @Header("Authorization") authorization: String,
        @Path("id") movieId: Int
    ): Response<moviesResponse>

}