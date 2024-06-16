package com.ic.cinefile.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ic.cinefile.Navigation.screenRoute
import com.ic.cinefile.R
import com.ic.cinefile.viewModel.userCreateViewModel
import java.util.regex.Pattern

@Composable
fun CrearCuenta(viewModel: userCreateViewModel, navController: NavController) {

    val context = LocalContext.current

    fun isValidEmail(email: String): Boolean {
        val emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"
        return Pattern.compile(emailPattern).matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=!])(?=\\S+\$).{8,}\$"
        val passwordMatcher = Regex(passwordPattern)
        return passwordMatcher.matches(password)
    }

    val accountData by viewModel.accountcreateAPIData
    var email by remember { mutableStateOf(accountData.email) }
    var password by remember { mutableStateOf(accountData.password) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¡Bienvenido!",
            modifier = Modifier.fillMaxWidth(),
            style = androidx.compose.ui.text.TextStyle(
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
            )
        )

        Spacer(modifier = Modifier.height(70.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(R.color.white),
                unfocusedLabelColor = Color(R.color.white),
                cursorColor = Color(R.color.white)
            ),
            placeholder = {
                Text(
                    text = "Correo (ejemplo@dominio.com )",
                    style = androidx.compose.ui.text.TextStyle(
                        color = Color.White,
                        fontSize = 15.sp,
                        letterSpacing = 0.1.em,
                        fontWeight = FontWeight.Normal,
                    )
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
            ),
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = password,
            onValueChange = {
<<<<<<< HEAD
                // Validación para que la contraseña no sea mayor a 8 caracteres
                password = it.take(8)
=======
                if (it.length <= 8) {
                    password = it
                }
>>>>>>> 00b07ca4594b442a7fff3a4e57131ffa8fc2ea09
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(R.color.black),
                unfocusedLabelColor = Color(R.color.white),
                cursorColor = Color(R.color.white)
            ),
            placeholder = {
                Text(
                    text = "Contraseña (máximo 8 caracteres)",
                    style = androidx.compose.ui.text.TextStyle(
                        color = Color.White,
                        fontSize = 15.sp,
                        letterSpacing = 0.1.em,
                        fontWeight = FontWeight.Normal,
                    ),
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = VisualTransformation.None // Contraseña visible
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "La contraseña debe contener al menos ocho caracteres, al menos un número, letras mayúsculas e minúsculas y caracteres especiales",
            color = Color.White,
            modifier = Modifier.width(300.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val correo = email
                val contrasena = password

                if (correo.isEmpty() || contrasena.isEmpty()) {
                    Toast.makeText(context, "No dejes los campos vacíos", Toast.LENGTH_SHORT).show()
                } else if (!isValidEmail(correo)) {
                    Toast.makeText(context, "Formato de correo inválido", Toast.LENGTH_SHORT).show()
                } else if (!isValidPassword(contrasena)) {
                    Toast.makeText(context, "Contraseña no válida", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.updateAccountData(accountData.copy(email = email, password = password))
                    navController.navigate(screenRoute.CrearPerfil.route)
                }
            },
            modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
        ) {
            Text(
                text = "Registrarse",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                )
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.googlee),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Iniciar sesión con Google",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = "¿Ya tienes una cuenta?",
            style = androidx.compose.ui.text.TextStyle(
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.clickable {
                navController.navigate(screenRoute.LoginCuenta.route)
            }
        )
    }
}
