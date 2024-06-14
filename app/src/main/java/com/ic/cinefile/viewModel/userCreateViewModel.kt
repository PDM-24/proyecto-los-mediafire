package com.ic.cinefile.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ic.cinefile.API.apiServer
import com.ic.cinefile.data.accountLoginData
import com.ic.cinefile.data.accountRegisterData
import com.ic.cinefile.data.accountRegisterDataMovieGenere
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class userCreateViewModel: ViewModel() {

    // Estados que gestionan el estado de la interfaz
    private val _uiState = MutableStateFlow<UiState>(UiState.Ready)
    val uiState : StateFlow<UiState> = _uiState
    // Estados que gestionan la informacion de las screens
    private val _accountCreateAPI = mutableStateOf(accountRegisterData())
    val accountcreateAPIData: State<accountRegisterData> = _accountCreateAPI

    private val _accountLoginAPI = mutableStateOf(accountLoginData())
    val accountLoginAPIData: State<accountLoginData> = _accountLoginAPI

    fun updateAccountData(newData: accountRegisterData) {
        _accountCreateAPI.value = newData
        Log.i("userCreateViewModel", "Updated data: $newData")

    }

    fun updateMovieGenres(newGenres: List<accountRegisterDataMovieGenere>) {

        _accountCreateAPI.value = _accountCreateAPI.value.copy(
            movieGenereList = newGenres.toMutableList()
        )
        Log.i("userCreateViewModel", "Updated data: ${_accountCreateAPI.value.movieGenereList}")

    }
    fun createAccountUser(userregisterData: accountRegisterData){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val response = apiServer.methods.createAccount(userregisterData)
                Log.i("userCreateViewModel", response.toString())

                _uiState.value = UiState.Success("Uusario creado correctamente")

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

                _uiState.value = UiState.Success("Iniciar sesion correctamente correctamente")

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

sealed class UiState {
    data object Loading : UiState()
    data object Ready : UiState()
    data class Success (val msg : String) : UiState()
    data class Error (val msg : String) : UiState()
}




