package com.ic.cinefile.viewModel


import android.media.Image
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ic.cinefile.API.apiServer
import com.ic.cinefile.data.accountCreateData
import com.ic.cinefile.data.loginUserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate


class userViewModel() : ViewModel() {


    fun loginUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val userData = loginUserData(email, password)
            val response = apiServer.methods.loginAccount(userData)
            if (response.isSuccessful) {
                response.body()

                println("Se ha enviado correctamente los datos")
            } else {

                println("Hubo un error al enviar los datos")


            }


        }
    }





}

