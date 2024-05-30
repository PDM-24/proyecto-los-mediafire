package com.ic.cinefile.screens

//import androidx.navigation.compose.rememberNavController
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.ic.cinefile.R
import com.ic.cinefile.activities.LoginCuentaReCuenta
import com.ic.cinefile.viewModel.userViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Login() {
//    val email: MutableState<String> = remember{ mutableStateOf("") }
//    val password: MutableState<String> = remember{ mutableStateOf("") }

    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val userViewModel = userViewModel()

    val showErrorSnackbar by userViewModel.showErrorSnackbar
    val errorMessage by userViewModel.errorMessage


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center


    ) {
        Text(
            text = "¡Bienvenido \n de nuevo!",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = email,
            onValueChange = {
                email = it
            },
            colors = TextFieldDefaults.textFieldColors(

                unfocusedIndicatorColor = Color.White,
                focusedIndicatorColor = Color.White,
                cursorColor = Color.Gray,

                ),
            placeholder = {
                Text(
                    text = "Correo",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 15.sp,
                        letterSpacing = 0.1.em,
                        fontWeight = FontWeight.Normal,
                    ),
                )
            },

            modifier = Modifier.width(300.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Lógica cuando se presiona Done
                },

                )

        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            colors = TextFieldDefaults.textFieldColors(

                unfocusedIndicatorColor = Color.White,
                focusedIndicatorColor = Color.White,
                cursorColor = Color.Gray,
            ),
            placeholder = {
                Text(
                    text = "Contraseña",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 15.sp,
                        letterSpacing = 0.1.em,
                        fontWeight = FontWeight.Normal,
                    ),
                )
            },

            modifier = Modifier.width(300.dp),

            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Lógica cuando se presiona Done
                },
            )

        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                if (email.isNotEmpty() && password.isNotEmpty()) {

                    userViewModel.loginUser(email, password)

                } else {

                    userViewModel.setErrorMessage("Por favor, completa todos los campos")

                }
            },
            modifier = Modifier
                .width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
        ) {
            Text(
                text = "Inicio sesion",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                // Acción para iniciar sesión con Google

            },
            modifier = Modifier
                .width(300.dp),
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
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "¿Has olvidado la contraseña?",
            style = TextStyle(
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.clickable {

                val intent = Intent(context, LoginCuentaReCuenta::class.java)
                intent.putExtra("indexItem", 0)
                context.startActivity(intent)

            }
        )
        Spacer(modifier = Modifier.height(10.dp))

        if (showErrorSnackbar) {
            Snackbar(modifier = Modifier.padding(16.dp),
                action = {
                    TextButton(onClick = { userViewModel.hideErrorSnackbar() }) {
                        Text("Cerrar")
                    }
                }
            ) {
                Text(errorMessage, color = Color.White)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewLoginCuentaScreen() {
    Login()
}
