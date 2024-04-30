package com.ic.cinefile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ic.cinefile.API.apiServer
import com.ic.cinefile.API.classMethods.users.User
import com.ic.cinefile.API.methods.Methods
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class userViewModel:ViewModel() {
//listado de usuarios
    var userNameList:ArrayList<User> by mutableStateOf(arrayListOf())


    fun createUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            val response=apiServer.methods.reateAccountc(user)
            withContext(Dispatchers.Main){
    if(response.body()!!.code=="200"){


    }
            }
        }
    }
}