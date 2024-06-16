package com.ic.cinefile.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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




    private var authToken: String? = null



    // Estado para manejar la información del usuario y sus películas favoritas

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


                _uiState.value = UiState.Success(response.token)

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




