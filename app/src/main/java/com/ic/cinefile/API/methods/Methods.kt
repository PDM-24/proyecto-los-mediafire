package com.ic.cinefile.API.methods

import com.ic.cinefile.API.Model.movies.AverageRatingResponse
import com.ic.cinefile.API.Model.movies.RatingResponse
import com.ic.cinefile.API.Model.movies.ReplyComment
import com.ic.cinefile.API.Model.movies.getCommentResponse
import com.ic.cinefile.API.Model.movies.homeUserResponse
import com.ic.cinefile.API.Model.movies.mostViewMoviesResponse
import com.ic.cinefile.API.Model.movies.moviesResponse
import com.ic.cinefile.API.Model.movies.postCommentResponse
import com.ic.cinefile.API.Model.movies.recentMoviesResponse
import com.ic.cinefile.API.Model.movies.searchMoviesResponse
import com.ic.cinefile.API.Model.movies.topMoviesResponse
import com.ic.cinefile.API.Model.movies.wishListResponse
import com.ic.cinefile.API.Model.movies.wishPostResponse
import com.ic.cinefile.API.Model.users.MarkAsReadResponse
import com.ic.cinefile.API.Model.users.NotificationResponse
import com.ic.cinefile.API.Model.users.UserLoginResponse
import com.ic.cinefile.API.Model.users.accountCreateResponse
import com.ic.cinefile.data.RatingData
import com.ic.cinefile.data.accountLoginData
import com.ic.cinefile.data.accountRegisterData
import com.ic.cinefile.data.commentData
import com.ic.cinefile.data.searchMoviesData
import com.ic.cinefile.data.witchListData

//import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
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
    @GET("api/movies/search/{title}")
    suspend fun searchMovies(
        @Header("Authorization") authorization: String,
        @Path("title") title: String,
        @Query("sortBy") sortBy: String?,
        @Query("genre") genre: String?
    ): Response<searchMoviesResponse>


    @Headers("Content-Type: application/json")
    @GET("api/movies/moviesId/{id}")
    suspend fun getMovieById(
        @Header("Authorization") authorization: String,
        @Path("id") movieId: Int
    ): Response<moviesResponse>


    @Headers("Content-Type: application/json")
    @POST("api/movies/moviesId/{id}/postComment")
    suspend fun postComment(
        @Header("Authorization") authorization: String,
        @Path("id") movieId: Int,
        @Query("parentId") parentId: String?, // Puede ser nulo si no hay parentId
        @Body commentData: commentData // Asegúrate de tener tu modelo de datos para el cuerpo del comentario definido
    ): Response<postCommentResponse>

    @Headers("Content-Type: application/json")
    @GET("api/movies/moviesId/{id}/comments")
    suspend fun getComments(
        @Header("Authorization") authorization: String,
        @Path("id") movieId: Int,

        ): Response<List<getCommentResponse>>


    @Headers("Content-Type: application/json")
    @GET("api/movies/moviesId/{id}/comments/{parentId}")
    suspend fun getRepliesToComment(
        @Header("Authorization") authorization: String,
        @Path("id") movieId: Int,
        @Path("parentId") parentId: String
    ): Response<List<ReplyComment>>  // Se espera una lista de respuestas a comentarios


    @Headers("Content-Type: application/json")
    @GET("api/account/user/notifications")
    suspend fun getNotifications(
        @Header("Authorization") authorization: String
    ): Response<List<NotificationResponse>>  // Asegúrate de tener tu modelo de datos para la respuesta de notificaciones definido

    @Headers("Content-Type: application/json")
    @PATCH("api/account/user/notifications/{id}")
    suspend fun markNotificationAsRead(
        @Header("Authorization") authorization: String,
        @Path("id") notificationId: String
    ): Response<MarkAsReadResponse>  // Asegúrate de tener tu modelo de datos para la respuesta de marcado como leído definido



    //para calificar
    @Headers("Content-Type: application/json")
    @POST("api/movies/moviesId/{id}/rate")
    suspend fun rateMovie(
        @Header("Authorization") authorization: String,
        @Path("id") movieId: Int,
        @Body ratingData: RatingData
    ): Response<RatingResponse>

    @Headers("Content-Type: application/json")
    @GET("api/movies/moviesId/{movieId}/average-rating")
    suspend fun getMovieAverageRating(
        @Header("Authorization") authorization: String,
        @Path("movieId") movieId: Int
    ): Response<AverageRatingResponse>


    @Headers("Content-Type: application/json")
    @GET("api/movies/topRatedMovies")
    suspend fun getTopRatedMovies(
        @Header("Authorization") authorization: String
    ): Response<topMoviesResponse>


    @POST("api/movies/moviesId/{id}/wishlist/add")
    suspend fun addToWishlist(
        @Header("Authorization") authorization: String,
       // @Path("id") movieId: Int,
        @Body wishlistData: witchListData
    ): Response<wishPostResponse>

    @GET("api/movies/wishlist")
    suspend fun getWishlist(
        @Header("Authorization") authorization: String
    ): Response<wishListResponse>

}



