    package com.ic.cinefile.viewModel

    import android.media.Image
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.ic.cinefile.API.apiServer
    import com.ic.cinefile.data.accountCreateData
    import com.ic.cinefile.data.loginUserData
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.launch
    class userCreateViewModel: ViewModel() {
        val username = MutableLiveData<String>()
        val email = MutableLiveData<String>()
        val password = MutableLiveData<String>()
        val year_nac = MutableLiveData<String>()
        val genere = MutableLiveData<String>()
        val movie_genere = MutableLiveData<List<String>>()
        val avatar = MutableLiveData<String>()


        fun createAccountUser(){
            viewModelScope.launch(Dispatchers.IO) {
                val accountData = accountCreateData(
                    username.value ?: "",
                    email.value ?: "",
                    password.value ?: "",
                    year_nac.value ?: "",
                    genere.value ?: "",
                    movie_genere.value ?: listOf(),
                    avatar.value
                )
                val response = apiServer.methods.createAccount(accountData)
                if (response.isSuccessful) {
                    response.body()
                    println("Se ha creado correctamente")

                } else {
                    println("Hubo un error al enviar los datos")

                }
            }

        }





    }