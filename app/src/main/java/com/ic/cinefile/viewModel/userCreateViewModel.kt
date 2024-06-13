package com.ic.cinefile.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ic.cinefile.API.apiServer
import com.ic.cinefile.data.accountCreateData
import com.ic.cinefile.data.accountRegisterData
import com.ic.cinefile.data.accountRegisterDataMovieGenere
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class userCreateViewModel: ViewModel() {

    // Estados que gestionan el estado de la interfaz
    private val _uiState = MutableStateFlow<UiState>(UiState.Ready)
    val uiState : StateFlow<UiState> = _uiState
    // Estados que gestionan la informacion de las screens
    private val _accountCreateAPI = mutableStateOf(accountRegisterData())
    val accountcreateAPIData: State<accountRegisterData> = _accountCreateAPI


    fun updateAccountData(newData: accountRegisterData) {
        _accountCreateAPI.value = newData
        Log.i("userCreateViewModel", "Updated data: $newData")

    }

    fun updateMovieGenres(newGenres: List<accountRegisterDataMovieGenere>) {
        Log.i("userCreateViewModel", "Updated data: $newGenres ")

        _accountCreateAPI.value.movieGenereList.addAll(newGenres)
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
}

sealed class UiState {
    data object Loading : UiState()
    data object Ready : UiState()
    data class Success (val msg : String) : UiState()
    data class Error (val msg : String) : UiState()
}





