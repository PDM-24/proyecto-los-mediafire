package com.ic.cinefile.viewModel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ic.cinefile.API.apiServer
import com.ic.cinefile.data.loginUserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse


class userViewModel() : ViewModel() {

    val showErrorSnackbar = mutableStateOf(false)
    val errorMessage = mutableStateOf("")

    fun loginUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val userData = loginUserData(email, password)
            val response = apiServer.methods.loginAccount(userData)
            if (response.isSuccessful) {
                response.body()

                println("Se ha enviado correctamente los datos")
            } else {
                setErrorMessage("Correo o contraseña incorrectos")
                println("Hubo un error al enviar los datos")

            }
        }
    }

    fun setErrorMessage(s: String) {
        errorMessage.value = s
        showErrorSnackbar.value = true
    }

    fun hideErrorSnackbar() {
        showErrorSnackbar.value = false
    }

}