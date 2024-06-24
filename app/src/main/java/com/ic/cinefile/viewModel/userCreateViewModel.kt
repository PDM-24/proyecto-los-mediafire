package com.ic.cinefile.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.annotations.SerializedName
import com.ic.cinefile.API.Model.movies.AverageRatingResponse
import com.ic.cinefile.API.Model.movies.ReplyComment
import com.ic.cinefile.API.Model.movies.actorNameResponse
import com.ic.cinefile.API.Model.movies.getCommentResponse
import com.ic.cinefile.API.Model.movies.homeUserResponse
import com.ic.cinefile.API.Model.movies.mostViewMoviesResponse
import com.ic.cinefile.API.Model.movies.movieResponseAdminResponse
import com.ic.cinefile.API.Model.movies.moviesResponse
import com.ic.cinefile.API.Model.movies.rateMoveResponse
import com.ic.cinefile.API.Model.movies.recentMoviesResponse
import com.ic.cinefile.API.Model.movies.searchMoviesResponse
import com.ic.cinefile.API.Model.movies.topMoviesResponse
import com.ic.cinefile.API.Model.movies.wishListResponse
import com.ic.cinefile.API.Model.users.NotificationResponse
import com.ic.cinefile.API.Model.users.UserLoginResponse
import com.ic.cinefile.API.apiServer
import com.ic.cinefile.data.Actor
import com.ic.cinefile.data.NotificationData
import com.ic.cinefile.data.RatingData
import com.ic.cinefile.data.accountLoginData
import com.ic.cinefile.data.accountRegisterData
import com.ic.cinefile.data.commentData
import com.ic.cinefile.data.createMovieData
import com.ic.cinefile.data.searchMoviesData
import com.ic.cinefile.data.witchListData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.text.Normalizer
import java.time.LocalDateTime
import java.util.Locale.Category

class userCreateViewModel: ViewModel() {

    // Estados que gestionan el estado de la interfaz para login
    private val _uiState = MutableStateFlow<UiState>(UiState.Ready)
    val uiState : StateFlow<UiState> = _uiState

    private val _uiState2 = MutableStateFlow<UiState2>(UiState2.Ready)
    val uiState2 : StateFlow<UiState2> = _uiState2



    // Estados que gestionan la informacion de las screens
    private val _accountCreateAPI = mutableStateOf(accountRegisterData())
    val accountcreateAPIData: State<accountRegisterData> = _accountCreateAPI

    private val _accountLoginAPI = mutableStateOf(accountLoginData())
    val accountLoginAPIData: State<accountLoginData> = _accountLoginAPI


    private val _userDataState = MutableStateFlow<UserDataState>(UserDataState.Ready)
    val userDataState: StateFlow<UserDataState> = _userDataState

    // Estado para manejar las películas más recientes
    private val _recentMoviesState = MutableStateFlow<RecentMoviestState>(RecentMoviestState.Ready)
    val recentMoviesState: StateFlow<RecentMoviestState> = _recentMoviesState

    // Estado para manejar las películas más recientes
    private val _mostViewsMoviesState = MutableStateFlow<MostViewsMoviestState>(MostViewsMoviestState.Ready)
    val mostViewsMoviesState: StateFlow<MostViewsMoviestState> = _mostViewsMoviesState

    // Estado para manejar los detalles de la película
    private val _movieState = MutableStateFlow<MovieState>(MovieState.Ready)
    val movieState: StateFlow<MovieState> = _movieState

    //Estado para manejar las los comentarios  enviados
    private val _postCommentState = mutableStateOf(commentData())
    val postCommentState: State<commentData> = _postCommentState

    //Estado para manejar las respuestas de los comentarios
    private val _repliesToCommentState = MutableStateFlow<RepliesToCommentState>(RepliesToCommentState.Ready)
    val repliesToCommentState: StateFlow<RepliesToCommentState> = _repliesToCommentState

    // Estado para manejar los comentarios obtenidos
    private val _commentsState = MutableStateFlow<CommentListState>(CommentListState.Ready)
    val commentsState: StateFlow<CommentListState> = _commentsState

    // Estado de busquedas recientes
    private val _recentSearches = MutableStateFlow<List<String>>(emptyList())
    val recentSearches: StateFlow<List<String>> = _recentSearches

    //estado de buscar pelicula por el titulo
    private val _searchState = MutableStateFlow<SearchState>(SearchState.Ready)
    val searchState: StateFlow<SearchState> = _searchState

    private val _notificationState = MutableStateFlow<NotificationState>(NotificationState.Ready)
    val notificationState: StateFlow<NotificationState> = _notificationState

    private val _markNotificationState = MutableStateFlow<MarkNotificationState>(MarkNotificationState.Ready)
    val markNotificationState: StateFlow<MarkNotificationState> = _markNotificationState

    // Estados que gestionan el estado de la interfaz para calificaciones
    private val _rateMovieState = MutableStateFlow<RateMovieState>(RateMovieState.Ready)
    val rateMovieState: StateFlow<RateMovieState> = _rateMovieState

    private val _averageRatingState = MutableStateFlow<AverageRatingState>(AverageRatingState.Ready)

    //peliculas en general
    val averageRatingState: StateFlow<AverageRatingState> get() = _averageRatingState

    //top peliculas
    private val _topMoviesState = MutableStateFlow<TopMoviestState>(TopMoviestState.Ready)
    val topMoviesState: StateFlow<TopMoviestState> = _topMoviesState

    private val _wishListGetState = MutableStateFlow<WishlistGetState>(WishlistGetState.Ready)
    val wishlistState: StateFlow<WishlistGetState> = _wishListGetState

    private val _wishlistState = MutableStateFlow<WishlistGetState>(WishlistGetState.Ready)
    val wishlisGetState : StateFlow<WishlistGetState> = _wishlistState

    private val _wishListPostState = mutableStateOf(witchListData())
    val wishListPostState: State<witchListData> = _wishListPostState


    //obtener peliculas individual
    private val _moviesReated = MutableStateFlow<MoviesReated>(MoviesReated.Ready)
    val moviesReatedState : StateFlow<MoviesReated> = _moviesReated

    private var authToken: String = "" // Propiedad para almacenar el token de autenticación
    private var userRole: String = "" // Propiedad para almacenar el rol del usuario



    private val _deleteCommentState = MutableStateFlow<DeleteCommentState>(DeleteCommentState.Ready)
    val deleteCommentState: StateFlow<DeleteCommentState> = _deleteCommentState

    private val _deleteReplyState = MutableStateFlow<DeleteReplyState>(DeleteReplyState.Ready)
    val deleteReplyState: StateFlow<DeleteReplyState> = _deleteReplyState



    //ADMIN
    private val _createMovie = mutableStateOf(createMovieData())
    val createMovie: State<createMovieData> = _createMovie

    private val _getMovieCreate = MutableStateFlow<GetMovieCreate>(GetMovieCreate.Ready)
    val getMovieCreate: StateFlow<GetMovieCreate> = _getMovieCreate

    // Estado para la búsqueda de actores por nombre
    private val _searchActorsState = MutableStateFlow<SearchActorsState>(SearchActorsState.Ready)
    val searchActorsState: StateFlow<SearchActorsState> = _searchActorsState






    fun updateAccountData(newData: accountRegisterData) {
        _accountCreateAPI.value = newData
        Log.i("userCreateViewModel", "Updated data: $newData")

    }


    fun updateMovieGenres(newGenres: List<String>) {
        _accountCreateAPI.value = _accountCreateAPI.value.copy(
            movieGenereList = newGenres.toMutableList()
        )
        Log.i("userCreateViewModel", "Updated data: ${_accountCreateAPI.value.movieGenereList}")
    }


    fun updateActors(newActors: List<Actor>) {
        _createMovie.value = _createMovie.value.copy(
            actors = newActors.toMutableList()
        )
    }

    fun addActor(actor: Actor) {
        _createMovie.value = _createMovie.value.copy(
            actors = _createMovie.value.actors.toMutableList().apply { add(actor) }
        )
    }

    // Función para agregar un actor a la lista de actores

    fun createAccountUser(userregisterData: accountRegisterData){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState2.value = UiState2.Loading
                val response = apiServer.methods.createAccount(userregisterData)
                Log.i("userCreateViewModel", response.toString())

                _uiState2.value = UiState2.Success("Uusario creado correctamente")

            }catch (e:Exception){
                when (e) {
                    is HttpException -> {
                        Log.i("userCreateViewModel", e.message())
                        _uiState2.value = UiState2.Error( e.message())
                    }
                    else -> {
                        Log.i("userCreateViewModel", e.toString())
                        _uiState2.value = UiState2.Error( "Error. Contacte con el servicio de soporte")

                    }
                }
            }
        }

    }


    fun setStateToReady() {
        _uiState.value = UiState.Ready
    }

    //iniciar sesion
    private val _showErrorToast = MutableStateFlow(false)
    val showErrorToast = _showErrorToast.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun loginUser(userLoginData: accountLoginData) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val response = apiServer.methods.loginAccount(userLoginData)
                Log.i("userLoginViewModel", response.toString())
                val token = response.token
                val role = response.role // Asegúrate de que el rol también se devuelva en la respuesta
                userRole = role // Guardar el rol del usuario

                authToken = token
                _uiState.value = UiState.Success(authToken)

                fetchUserData(authToken) // Obtener información del usuario utilizando el token
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("userCreateViewModel", e.message())
                        _uiState.value = UiState.Error(e.message())
                    }
                    else -> {
                        Log.i("userCreateViewModel", e.toString())
                        _uiState.value = UiState.Error("Verifica su conexion, Intentalo mas tarde")
                    }
                }
            }
        }
    }
    fun getUserRole(): String {
        return userRole
    }

    private fun setErrorMessage(message: String) {
        _errorMessage.value = message
        _showErrorToast.value = true
    }

    fun hideErrorToast() {
        _showErrorToast.value = false
    }

    //manejar token
    fun fetchUserData(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _userDataState.value = UserDataState.Loading
                val response = apiServer.methods.getUserHome("Bearer $token")
                if (response.isSuccessful) {
                    val userData = response.body()
                    _userDataState.value = userData?.let { UserDataState.Success(it) } ?: UserDataState.Error("Error: Datos del usuario no encontrados")
                } else {
                    _userDataState.value = UserDataState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("userCreateViewModel", e.message())
                        _userDataState.value = UserDataState.Error(e.message())
                    }

                }
            }
        }
    }


    //obtener peliculas recientes
    fun getRecentMoviesData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _recentMoviesState.value = RecentMoviestState.Loading
                val response = apiServer.methods.getRecentMovies("Bearer $authToken")
                if (response.isSuccessful) {
                    val data = response.body()
                    val uniqueMovies = data?.moviesRecent?.distinctBy { it.id } // Filtra duplicados por ID
                    _recentMoviesState.value = uniqueMovies?.let { RecentMoviestState.Success(recentMoviesResponse(it)) } ?: RecentMoviestState.Error("Error: Peliculas no encontradas")

                } else {
                    _recentMoviesState.value = RecentMoviestState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("userCreateViewModel", e.message())
                        _mostViewsMoviesState.value = MostViewsMoviestState.Error(e.message())
                    }

                }
            }
        }
    }



    //obtener peliculas mas vistas
    fun getMostViewMoviesData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _mostViewsMoviesState.value = MostViewsMoviestState.Loading
                val response = apiServer.methods.getMostViewMovies("Bearer $authToken")
                if (response.isSuccessful) {
                    val data = response.body()
                    val uniqueMovies = data?.moviesMostViews?.distinctBy { it.id } // Filtra duplicados por ID

                    _mostViewsMoviesState.value = uniqueMovies?.let { MostViewsMoviestState.Success(mostViewMoviesResponse(it)) } ?: MostViewsMoviestState.Error("Error: Peliculas no encontradas")
                } else {
                    _mostViewsMoviesState.value = MostViewsMoviestState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("userCreateViewModel", e.message())
                        _mostViewsMoviesState.value = MostViewsMoviestState.Error(e.message())
                    }

                }
            }
        }
    }


    //obtener por id
    fun getMovieById( movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _movieState.value = MovieState.Loading
                val response = apiServer.methods.getMovieById("Bearer $authToken", movieId)
                if (response.isSuccessful) {
                    response.body()?.let { movie ->
                        _movieState.value = MovieState.Success(movie)
                    } ?: run {
                        _movieState.value = MovieState.Error("Movie not found")
                    }
                } else {
                    _movieState.value = MovieState.Error("Error fetching movie: ${response.message()}")
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.e("UserCreateViewModel", e.message())
                        _movieState.value = MovieState.Error("Error fetching movie: ${e.message()}")
                    }
                    else -> {
                        Log.e("UserCreateViewModel", e.toString())
                        _movieState.value = MovieState.Error("Error fetching movie: ${e.message}")
                    }
                }
            }
        }
    }

    fun searchMoviesByTitle(title: String, sortBy: String? = null, genre: String? = null) {
        val normalizedTitle = normalizeString(title)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _searchState.value = SearchState.Loading
                val response = apiServer.methods.searchMovies("Bearer $authToken",normalizedTitle, sortBy, genre)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        if (responseBody is searchMoviesResponse) {
                            var movies = responseBody.moviesSearch // Suponiendo que `movies` es una lista de películas

                            // Aplicar filtro por género si se especifica
                            if (!genre.isNullOrEmpty()) {
                                movies = movies.filter { movie ->
                                    movie.genres.contains(genre)
                                }
                            }

                            // Ordenar según el criterio seleccionado
                            if (!sortBy.isNullOrEmpty()) {
                                movies = when (sortBy.toLowerCase()) {
                                    "recientes" -> movies.sortedByDescending { it.releaseDate ?: "1970-01-01" }
                                    "masviejas" -> movies.sortedBy { it.releaseDate ?: "1970-01-01" }
                                    else -> movies
                                }
                            }

                            _searchState.value = SearchState.Success(searchMoviesResponse(movies))
                            updateRecentSearches(title) // Actualizar búsquedas recientes
                        } else {
                            _searchState.value = SearchState.Error("Error: Expected a movie object but received something else.")
                        }
                    } else {
                        _searchState.value = SearchState.Error("Error: Empty response body.")
                    }
                } else {
                    _searchState.value = SearchState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                when (e) {
                    is IOException -> {
                        Log.e("UserCreateViewModel", "Error de red: ${e.message}")
                        _searchState.value = SearchState.Error("Error de red: ${e.message}")
                    }
                    is HttpException -> {
                        Log.e("UserCreateViewModel", "Error HTTP: ${e.message}")
                        _searchState.value = SearchState.Error("Error HTTP: ${e.message}")
                    }
                    else -> {
                        Log.e("UserCreateViewModel", "Error desconocido: ${e.message}")
                        _searchState.value = SearchState.Error("Error desconocido: ${e.message}")
                    }
                }
            }
        }
    }

    fun normalizeString(str: String): String {
        return Normalizer.normalize(str, Normalizer.Form.NFD)
            .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
            .toLowerCase()
    }



    private fun updateRecentSearches(title: String) {
        val currentList = _recentSearches.value.toMutableList()
        if (!currentList.contains(title)) {
            currentList.add(0, title) // Agregar solo el título
            _recentSearches.value = currentList.take(5) // Limitar a los últimos 5 títulos
        }
    }
    //publicar comentario
    // Función para publicar un comentario
    fun postComment(movieId: Int, commentData: commentData, parentId: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiServer.methods.postComment("Bearer $authToken", movieId,parentId?: "", commentData)
                if (response.isSuccessful) {
                    getComments(movieId)
                    _commentsState.value = CommentListState.Success(emptyList()) // Puedes actualizar el estado según tu lógica de UI
                } else {
                    _commentsState.value = CommentListState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.e("userCreateViewModel", "Error HTTP: ${e.message()}")
                        _commentsState.value = CommentListState.Error("Error HTTP: ${e.message()}")
                    }
                    else -> {
                        Log.e("userCreateViewModel", "Error: ${e.message}")
                        _commentsState.value = CommentListState.Error("Error: ${e.message}")
                    }
                }
            }
        }
    }

    fun getComments(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _commentsState.value = CommentListState.Loading
                val response = apiServer.methods.getComments("Bearer $authToken", movieId)
                if (response.isSuccessful) {
                    val comments = response.body()
                    _commentsState.value = CommentListState.Success(comments ?: emptyList())
                } else {
                    _commentsState.value = CommentListState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.e("userCreateViewModel", "Error HTTP: ${e.message()}")
                        _commentsState.value = CommentListState.Error("Error HTTP: ${e.message()}")
                    }
                    else -> {
                        Log.e("userCreateViewModel", "Error: ${e.message}")
                        _commentsState.value = CommentListState.Error("Error: ${e.message}")
                    }
                }
            }
        }
    }


    fun getRepliesToComment(movieId: Int, parentId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _repliesToCommentState.value = RepliesToCommentState.Loading
                //val response = apiServer.methods.getRepliesToComment("Bearer $authToken", movieId, parentId?: "" )
                val response = apiServer.methods.getRepliesToComment("Bearer $authToken", movieId, parentId?: "" )

                if (response.isSuccessful) {
                    val replies = response.body()
                    _repliesToCommentState.value = RepliesToCommentState.Success(replies ?: emptyList())
                } else {
                    _repliesToCommentState.value = RepliesToCommentState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.e("userCreateViewModel", "Error HTTP: ${e.message()}")
                        _repliesToCommentState.value = RepliesToCommentState.Error("Error HTTP: ${e.message()}")
                    }
                    else -> {
                        Log.e("userCreateViewModel", "Error: ${e.message}")
                        _repliesToCommentState.value = RepliesToCommentState.Error("Error: ${e.message}")
                    }
                }
            }
        }
    }

    // Enviar notificaciones
    fun getNotifications() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _notificationState.value = NotificationState.Loading
                val response = apiServer.methods.getNotifications("Bearer $authToken")
                if (response.isSuccessful) {
                    val notifications = response.body()
                    _notificationState.value = NotificationState.Success(notifications ?: emptyList())
                } else {
                    _notificationState.value = NotificationState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.e("UserCreateViewModel", "Error HTTP: ${e.message()}")
                        _notificationState.value = NotificationState.Error("Error HTTP: ${e.message()}")
                    }
                    else -> {
                        Log.e("UserCreateViewModel", "Error: ${e.message}")
                        _notificationState.value = NotificationState.Error("Error: ${e.message}")
                    }
                }
            }
        }
    }

    fun markNotificationAsRead(notificationId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _markNotificationState.value = MarkNotificationState.Loading
                val response = apiServer.methods.markNotificationAsRead("Bearer $authToken", notificationId)
                if (response.isSuccessful) {
                    _markNotificationState.value = MarkNotificationState.Success("Notification marked as read")
                    getNotifications() // Refresh notifications after marking as read
                } else {
                    _markNotificationState.value = MarkNotificationState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.e("UserCreateViewModel", "Error HTTP: ${e.message()}")
                        _markNotificationState.value = MarkNotificationState.Error("Error HTTP: ${e.message()}")
                    }
                    else -> {
                        Log.e("UserCreateViewModel", "Error: ${e.message}")
                        _markNotificationState.value = MarkNotificationState.Error("Error: ${e.message}")
                    }
                }
            }
        }
    }





    //calificar
    // Método para calificar una película
    fun rateMovie(movieId: Int, rating: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _rateMovieState.value = RateMovieState.Loading
                val ratingData = RatingData(movieId, rating)
                val response = apiServer.methods.rateMovie("Bearer $authToken", movieId, ratingData)
                if (response.isSuccessful) {
                    val message = response.body()?.message ?: "Movie rated successfully"
                    _rateMovieState.value = RateMovieState.Success(message)
                    getAverageRating(movieId)
                } else {
                    _rateMovieState.value = RateMovieState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.e("userCreateViewModel", "Error HTTP: ${e.message()}")
                        _rateMovieState.value = RateMovieState.Error("Error HTTP: ${e.message()}")
                    }

                    else -> {
                        Log.e("userCreateViewModel", "Error: ${e.message}")
                        _rateMovieState.value = RateMovieState.Error("Error: ${e.message}")
                    }
                }
            }
        }
    }

    // Método para obtener la calificación promedio de una película(general)
    fun getAverageRating(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _averageRatingState.value = AverageRatingState.Loading
                val response = apiServer.methods.getMovieAverageRating("Bearer $authToken", movieId)
                if (response.isSuccessful) {
                    val averageRating = response.body()?.averageRating ?: 0.0
                    _averageRatingState.value = AverageRatingState.Success(averageRating)
                } else {
                    _averageRatingState.value = AverageRatingState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.e("userCreateViewModel", "Error HTTP: ${e.message()}")
                        _averageRatingState.value = AverageRatingState.Error("Error HTTP: ${e.message()}")
                    }
                    else -> {
                        Log.e("userCreateViewModel", "Error: ${e.message}")
                        _averageRatingState.value = AverageRatingState.Error("Error: ${e.message}")
                    }
                }
            }
        }
    }

    // Función para resetear el estado a Ready
    fun setStateToReady1() {
        _averageRatingState.value = AverageRatingState.Ready
    }

    fun TopMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _topMoviesState.value = TopMoviestState.Loading
                val response = apiServer.methods.getTopRatedMovies("Bearer $authToken")
                if (response.isSuccessful) {
                    val data = response.body()
                    val uniqueMovies = data?.topRatedMovies?.distinctBy { it.id } // Filtra duplicados por ID
                    _topMoviesState.value = uniqueMovies?.let { TopMoviestState.Success(
                        topMoviesResponse(it)
                    ) }
                        ?:TopMoviestState.Error("Error: Películas no encontradas")
                } else {
                    _topMoviesState.value =TopMoviestState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.e("UserCreateViewModel", "Error HTTP: ${e.message()}")
                        _topMoviesState.value = TopMoviestState.Error("Error HTTP: ${e.message()}")
                    }
                    else -> {
                        Log.e("UserCreateViewModel", "Error: ${e.message}")
                        _topMoviesState.value = TopMoviestState.Error("Error: ${e.message}")
                    }
                }
            }
        }
    }


    //agregar a witchList
    // Método para añadir a la lista de deseos
    fun addToWishlist(witchListData: witchListData) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiServer.methods.addToWishlist("Bearer $authToken", witchListData)
                if (response.isSuccessful) {
                  //  _wishListGetState.value = WishlistGetState.Success("success") // Puedes actualizar el estado según tu lógica de UI
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        _wishListGetState.value = WishlistGetState.Error("Error HTTP: ${e.message()}")
                    }
                    else -> {
                        _wishListGetState.value = WishlistGetState.Error("Error: ${e.message}")
                    }
                }
            }
        }
    }


    // Método para obtener la lista de deseos
    fun getWishlist() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _wishlistState.value = WishlistGetState.Loading
                val response = apiServer.methods.getWishlist("Bearer $authToken")
                if (response.isSuccessful) {
                    val wishlistItems = response.body()
                    wishlistItems?.let {
                        _wishlistState.value = WishlistGetState.Success(it)
                    } ?: run {
                        _wishlistState.value = WishlistGetState.Error("Lista de deseos vacía")
                    }
                } else {
                    _wishlistState.value = WishlistGetState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        _wishlistState.value = WishlistGetState.Error("Error HTTP: ${e.message()}")
                    }
                    else -> {
                        _wishlistState.value = WishlistGetState.Error("Error: ${e.message}")
                    }
                }
            }
        }
    }


//calificadas por usuario individual
    fun getMoviesReated() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _moviesReated.value = MoviesReated.Loading
                val response = apiServer.methods.getRatedMovies("Bearer $authToken")
                if (response.isSuccessful) {
                    val moviesReatedItems = response.body()
                    moviesReatedItems?.let {
                        _moviesReated.value = MoviesReated.Success(it)
                    } ?: run {
                        _moviesReated.value = MoviesReated.Error("Lista de Calificados vacía")

                    }
                } else {
                    _moviesReated.value = MoviesReated.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        _moviesReated.value = MoviesReated.Error("Error HTTP: ${e.message()}")
                    }
                    else -> {
                        _moviesReated.value = MoviesReated.Error("Error: ${e.message}")
                    }
                }
            }
        }
    }

    fun resetDeleteCommentState() {
        _deleteCommentState.value = DeleteCommentState.Ready
    }


    // Eliminar comentario principal
    fun deleteComment(commentId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _deleteCommentState.value = DeleteCommentState.Loading
                val response = apiServer.methods.deleteComment("Bearer $authToken", commentId)
                if (response.isSuccessful) {
                    _deleteCommentState.value = DeleteCommentState.Success("Comment deleted successfully")
                } else {
                    _deleteCommentState.value = DeleteCommentState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _deleteCommentState.value = DeleteCommentState.Error(e.message ?: "Unknown error")
            }
        }
    }

    // Eliminar respuesta específica
    fun deleteReply(replyId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _deleteReplyState.value = DeleteReplyState.Loading
                val response = apiServer.methods.deleteReply("Bearer $authToken", replyId)
                if (response.isSuccessful) {
                    _deleteReplyState.value = DeleteReplyState.Success("Reply deleted successfully")
                } else {
                    _deleteReplyState.value = DeleteReplyState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _deleteReplyState.value = DeleteReplyState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun createMovie(createMovieData: createMovieData){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiServer.methods.createMovie("Bearer $authToken",createMovieData)
                Log.i("userCreateViewModel", response.toString())


            }catch (e:Exception){
                when (e) {
                    is HttpException -> {
                        Log.i("userCreateViewModel", e.message())
                    }
                    else -> {
                        Log.i("userCreateViewModel", e.toString())

                    }
                }
            }
        }
    }


    fun getMovieCreate() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _getMovieCreate.value = GetMovieCreate.Loading
                val response = apiServer.methods.getMovieCreate("Bearer $authToken")
                if (response.isSuccessful) {
                    val userData = response.body()
                    _getMovieCreate.value = userData?.let { GetMovieCreate.Success(it) } ?: GetMovieCreate.Error("Error: Datos de la pelicula no encontrado")
                } else {
                    _getMovieCreate.value = GetMovieCreate.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("datos:", e.message())
                        _getMovieCreate.value = GetMovieCreate.Error(e.message())
                    }

                }
            }
        }
    }


    // Método para buscar actores por nombre
    fun searchActorsByName(actorName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _searchActorsState.value = SearchActorsState.Loading

                // Llamar al método de la API para buscar actores por nombre
                val response = apiServer.methods.searchActorsByName("Bearer $authToken" , actorName)

                if (response.isSuccessful) {
                    val actorsResponse = response.body()
                    actorsResponse?.let {
                        _searchActorsState.value = SearchActorsState.Success(it)
                    } ?: run {
                        _searchActorsState.value = SearchActorsState.Error("Respuesta nula del servidor")
                    }
                } else {
                    _searchActorsState.value = SearchActorsState.Error("Error ${response.code()}: ${response.message()}")
                }
            } catch (e: Exception) {
                _searchActorsState.value = SearchActorsState.Error("Error: ${e.message}")
            }
        }
    }

    // Método para limpiar el estado de búsqueda de actores
    fun clearSearchActorsState() {
        _searchActorsState.value = SearchActorsState.Ready
    }

}

sealed class UiState2 {
    data object Loading : UiState2()
    data object Ready : UiState2()
    data class Success (val msg : String) : UiState2()
    data class Error (val msg : String) : UiState2()
}


sealed class UiState {
    data object Loading : UiState()
    data object Ready : UiState()
    data class Success(val token: String): UiState()
    data class Error (val msg : String) : UiState()
}



sealed class UserDataState {
    data object Loading : UserDataState()
    data object Ready : UserDataState()
    data class Success(val userData: homeUserResponse) : UserDataState()
    data class Error(val msg: String) : UserDataState()
}



sealed class RecentMoviestState {
    data object Loading : RecentMoviestState()
    data object Ready : RecentMoviestState()
    data class Success(val data: recentMoviesResponse) : RecentMoviestState()
    data class Error(val msg: String) : RecentMoviestState()
}

sealed class MostViewsMoviestState {
    data object Loading : MostViewsMoviestState()
    data object Ready : MostViewsMoviestState()
    data class Success(val data: mostViewMoviesResponse) : MostViewsMoviestState()
    data class Error(val msg: String) : MostViewsMoviestState()
}
sealed class MovieState {
    object Loading : MovieState()
    object Ready :  MovieState()

    data class Success(val data: moviesResponse) : MovieState()
    data class Error(val msg: String) : MovieState()
}


sealed class CommentPostState {
    object Loading : CommentPostState()
    object Ready : CommentPostState()
    data class Success(val message: String) : CommentPostState()
    data class Error(val message: String) : CommentPostState()
}

// Selladas para manejar los estados de la lista de comentarios
sealed class CommentListState {
    object Loading : CommentListState()
    object Ready : CommentListState()
    data class Success(val comments: List<getCommentResponse>) : CommentListState()
    data class Error(val message: String) : CommentListState()
}

sealed class RepliesToCommentState {
    object Loading : RepliesToCommentState()
    object Ready : RepliesToCommentState()
    data class Success(val replies: List<ReplyComment>) : RepliesToCommentState()
    data class Error(val message: String) : RepliesToCommentState()
}


sealed class NotificationState {
    object Loading : NotificationState()
    object Ready : NotificationState()
    data class Success(val notifications: List<NotificationResponse>) : NotificationState()
    data class Error(val message: String) : NotificationState()
}

sealed class MarkNotificationState {
    object Loading : MarkNotificationState()
    object Ready : MarkNotificationState()
    data class Success(val message: String) : MarkNotificationState()
    data class Error(val message: String) : MarkNotificationState()
}


sealed class RateMovieState {
    object Loading : RateMovieState()
    object Ready : RateMovieState()
    data class Success(val message: String) : RateMovieState()
    data class Error(val message: String) : RateMovieState()
}

sealed class AverageRatingState {
    object Loading : AverageRatingState()
    object Ready : AverageRatingState()
    data class Success(val averageRating: Double) : AverageRatingState()
    data class Error(val message: String) : AverageRatingState()
}


sealed class SearchState {
    object Loading : SearchState()
    object Ready : SearchState()
    data class Success(val data: searchMoviesResponse) : SearchState()
    data class Error(val message: String) : SearchState()
}


sealed class TopMoviestState {
    data object Loading : TopMoviestState()
    data object Ready : TopMoviestState()
    data class Success(val data: topMoviesResponse) : TopMoviestState()
    data class Error(val msg: String) : TopMoviestState()
}



sealed class WishlistPostState {
    object Loading : WishlistPostState()
    object Ready : WishlistPostState()
    data class Success(val message: String) : WishlistPostState()
    data class Error(val errorMessage: String) : WishlistPostState()
}

sealed class WishlistGetState {
    object Loading :  WishlistGetState()
    object Ready :  WishlistGetState()
    data class Success(val data: wishListResponse) :  WishlistGetState()
    data class Error(val errorMessage: String) :  WishlistGetState()
}

sealed class MoviesReated {
    object Loading :  MoviesReated()
    object Ready :  MoviesReated()
    data class Success(val data: rateMoveResponse) :  MoviesReated()
    data class Error(val errorMessage: String) :  MoviesReated()
}


sealed class DeleteCommentState {
    object Loading : DeleteCommentState()
    object Ready : DeleteCommentState()
    data class Success(val message: String) : DeleteCommentState()
    data class Error(val errorMessage: String) : DeleteCommentState()
}

sealed class DeleteReplyState {
    object Loading : DeleteReplyState()
    object Ready : DeleteReplyState()
    data class Success(val message: String) : DeleteReplyState()
    data class Error(val errorMessage: String) : DeleteReplyState()
}


//admin
sealed class CreateMovie {
    data object Loading : CreateMovie()
    data object Ready : CreateMovie()
    data class Success (val data:createMovieData) : CreateMovie()
    data class Error (val msg : String) : CreateMovie()
}


sealed class GetMovieCreate {
    object Loading :  GetMovieCreate()
    object Ready :  GetMovieCreate()
    data class Success(val data: movieResponseAdminResponse) :  GetMovieCreate()
    data class Error(val errorMessage: String) :  GetMovieCreate()
}

sealed class SearchActorsState {
    object Loading : SearchActorsState()
    object Ready : SearchActorsState()
    data class Success(val actors: actorNameResponse) : SearchActorsState()
    data class Error(val errorMessage: String) : SearchActorsState()
}



