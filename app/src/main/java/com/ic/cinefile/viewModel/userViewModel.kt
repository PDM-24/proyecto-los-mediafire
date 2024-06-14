//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.ic.cinefile.API.apiServer
//import com.ic.cinefile.data.loginUserData
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.launch
//import java.io.IOException
//
//class userViewModel : ViewModel() {
//
//    private val _showErrorToast = MutableStateFlow(false)
//    val showErrorToast = _showErrorToast.asStateFlow()
//
//    private val _errorMessage = MutableStateFlow("")
//    val errorMessage = _errorMessage.asStateFlow()
//
//    fun loginUser(email: String, password: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val userData = loginUserData(email, password)
//                val response = apiServer.methods.loginAccount(userData)
//                if (response.isSuccessful) {
//                    response.body()
////                    println("Se ha enviado correctamente los datos")
//                } else {
//                    when (response.code()) {
//                        404 -> setErrorMessage("Correo no registrado")
//                        401 -> setErrorMessage("Contraseña incorrecta")
//                        else -> setErrorMessage("Error desconocido")
//                    }
//                    println("Hubo un error al enviar los datos")
//                }
//            } catch (e: IOException) {
//                setErrorMessage("Error de conexión")
//                println("Error de conexión: ${e.message}")
//            }
//        }
//    }
//
//    private fun setErrorMessage(message: String) {
//        _errorMessage.value = message
//        _showErrorToast.value = true
//    }
//
//    fun hideErrorToast() {
//        _showErrorToast.value = false
//    }
//}
