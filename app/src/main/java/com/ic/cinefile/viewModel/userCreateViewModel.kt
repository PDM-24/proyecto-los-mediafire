package com.ic.cinefile.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.annotations.SerializedName
import com.ic.cinefile.API.Model.movies.getCommentResponse
import com.ic.cinefile.API.Model.movies.homeUserResponse
import com.ic.cinefile.API.Model.movies.mostViewMoviesResponse
import com.ic.cinefile.API.Model.movies.moviesResponse
import com.ic.cinefile.API.Model.movies.recentMoviesResponse
import com.ic.cinefile.API.Model.movies.searchMoviesResponse
import com.ic.cinefile.API.Model.users.UserLoginResponse
import com.ic.cinefile.API.apiServer
import com.ic.cinefile.data.accountLoginData
import com.ic.cinefile.data.accountRegisterData
import com.ic.cinefile.data.commentData
import com.ic.cinefile.data.searchMoviesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
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

    // Estado para manejar las busquedas

    private val _moviesSearchState = MutableStateFlow<MoviesSearchState>(MoviesSearchState.Ready)
    val moviesSearchState: StateFlow<MoviesSearchState> = _moviesSearchState


    private val _recentSearches = MutableStateFlow<List<searchMoviesResponse>>(emptyList())
    val recentSearches: StateFlow<List<searchMoviesResponse>> = _recentSearches

    // Estado para manejar los detalles de la película
    private val _movieState = MutableStateFlow<MovieState>(MovieState.Ready)
    val movieState: StateFlow<MovieState> = _movieState


    private val _postCommentState = mutableStateOf(commentData())
    val postCommentState: State<commentData> = _postCommentState






    // Estado para manejar los comentarios obtenidos
    private val _commentsState = MutableStateFlow<CommentListState>(CommentListState.Ready)
    val commentsState: StateFlow<CommentListState> = _commentsState


    private var authToken: String = "" // Propiedad para almacenar el token de autenticación


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
                authToken = token
                _uiState.value = UiState.Success(authToken)



            fetchUserData(authToken) // Obtener información del usuario utilizando el token
                getRecentMoviesData(authToken)
                    getMostViewMoviesData(authToken)
            }catch (e:Exception){
                when (e) {
                    is HttpException -> {
                        Log.i("userCreateViewModel", e.message())
                        _uiState.value = UiState.Error( e.message())
                    }
                    else -> {
                        Log.i("userCreateViewModel", e.toString())
                        _uiState.value = UiState.Error( "Verifica su conexion, Intentalo mas tarde")

                    }
                }
            }
        }
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
    fun getRecentMoviesData(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _recentMoviesState.value = RecentMoviestState.Loading
                val response = apiServer.methods.getRecentMovies("Bearer $token")
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
    fun getMostViewMoviesData(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _mostViewsMoviesState.value = MostViewsMoviestState.Loading
                val response = apiServer.methods.getMostViewMovies("Bearer $token")
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




    // Buscar películas por título
    fun searchMovie(token: String, titleMovie: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _moviesSearchState.value = MoviesSearchState.Loading
                val response = apiServer.methods.searchMovies("Bearer $token", titleMovie)
                if (response.isSuccessful) {
                    val searchData = response.body()
                    _moviesSearchState.value =
                        searchData?.let { MoviesSearchState.Success(it) }
                            ?: MoviesSearchState.Error("Error: Datos de búsqueda de películas no encontrados")
                    searchData?.let { updateRecentSearches(it) } // Actualiza las búsquedas recientes


                } else {
                    _moviesSearchState.value = MoviesSearchState.Error("Error: ${response.message()}")
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







    fun updateRecentSearches(searchResult: searchMoviesResponse) {
        val currentList = _recentSearches.value.toMutableList()
        currentList.add(0, searchResult) // Agrega el nuevo resultado al inicio de la lista
        _recentSearches.value = currentList.take(5) // Limita la lista a un máximo de resultados
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





    // Función para publicar un comentario
    fun postComment(movieId: Int, commentData: commentData, parentId: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiServer.methods.postComment("Bearer $authToken", movieId, parentId ?: "", commentData)
                if (response.isSuccessful) {
                    getComments(movieId) // Después de publicar el comentario con éxito, actualizamos la lista de comentarios
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


sealed class MoviesSearchState {
    object Loading : MoviesSearchState()
    object Ready :  MoviesSearchState()
    data class Success(val searchData: searchMoviesResponse) :  MoviesSearchState()
    data class Error(val message: String) :  MoviesSearchState()
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