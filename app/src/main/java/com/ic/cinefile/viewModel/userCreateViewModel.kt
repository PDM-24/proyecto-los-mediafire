package com.ic.cinefile.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ic.cinefile.API.Model.movies.homeUserResponse
import com.ic.cinefile.API.Model.movies.mostViewMoviesResponse
import com.ic.cinefile.API.Model.movies.recentMoviesResponse
import com.ic.cinefile.API.Model.users.UserLoginResponse
import com.ic.cinefile.API.apiServer
import com.ic.cinefile.data.accountLoginData
import com.ic.cinefile.data.accountRegisterData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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


                _uiState.value = UiState.Success(response.token)
                fetchUserData(token) // Obtener información del usuario utilizando el token
                getRecentMoviesData(token)
                    getMostViewMoviesData(token)
            }catch (e:Exception){
                when (e) {
                    is HttpException -> {
                        Log.i("userCreateViewModel", e.message())
                        _uiState.value = UiState.Error( e.message())
                    }
                    else -> {
                        Log.i("userCreateViewModel", e.toString())
                        _uiState.value = UiState.Error( "Error. Contacte con el servicio de soporte")

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
