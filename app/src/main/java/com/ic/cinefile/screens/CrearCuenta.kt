package com.ic.cinefile.screens

import android.content.Context
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.ic.cinefile.R
import com.ic.cinefile.activities.CrearCuentaActivity
import com.ic.cinefile.activities.CrearPerfilActivity
import kotlinx.coroutines.flow.internal.NoOpContinuation.context
import kotlin.coroutines.jvm.internal.CompletedContinuation.context

@Composable
fun CrearCuenta() {

    val context = LocalContext.current

    val correoState = remember { mutableStateOf("") }
    val contrasenaState = remember { mutableStateOf("") }


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

        Spacer(
            modifier = Modifier
                .height(70.dp)
        )

        TextField(
            value = "",
            onValueChange = { newValue ->
                correoState.value = newValue
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(R.color.black),
                unfocusedLabelColor = Color(R.color.white),
                cursorColor = Color(R.color.white)
            ),
            placeholder = {

                Text(
                    text = "Correo",
                    style = androidx.compose.ui.text.TextStyle(
                        color = Color.White,
                        fontSize = 15.sp,
                        letterSpacing = 0.1.em,
                        fontWeight = FontWeight.Normal,
                    )
                )
            },
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = "",
            onValueChange = { newValue ->
                contrasenaState.value = newValue
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(R.color.black),
                unfocusedLabelColor = Color(R.color.white),
                cursorColor = Color(R.color.white)
            ),
            placeholder = {
                Text(
                    text = "Contraseña",
                    style = androidx.compose.ui.text.TextStyle(
                        color = Color.White,
                        fontSize = 15.sp,
                        letterSpacing = 0.1.em,
                        fontWeight = FontWeight.Normal,
                    ),
                )
            },
        )
        Spacer(modifier = Modifier.height(35.dp))
        Button(
            onClick = {

                val correo = correoState.value
                val contrasena = contrasenaState.value
                val intent = Intent(context, CrearPerfilActivity::class.java).apply {
                    putExtra("correo", correo)
                    putExtra("contrasena", contrasena)
                }
                context.startActivity(intent)

            },
            modifier = Modifier
                .width(300.dp),
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
            modifier = Modifier
                .clickable { }
        )


    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCrearCuentaScreen() {
    CrearCuenta()
}
