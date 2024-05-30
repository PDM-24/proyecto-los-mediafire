package com.ic.cinefile.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.ic.cinefile.activities.RestContraActivity
import com.ic.cinefile.ui.theme.black
import com.ic.cinefile.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestablecerContra(context:Context) {
    val correo: MutableState<String> = remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = black
                ),
                title = {},
                navigationIcon =
                {
                    IconButton(
                        onClick = {

                            val intent = Intent(context, RestContraActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                            context.startActivity(intent)
                            (context as Activity).finish()

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "",
                            tint = white
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Ingresa tu correo",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    color = white,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                )
            )

            Spacer(modifier = Modifier.height(70.dp))

            Text(
                text = "Se te enviará un correo electrónico con las " +
                        "indicaciones para restablecer tu contraseña",
                modifier = Modifier.width(300.dp),
                style = TextStyle(
                    color = white,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.height(50.dp))

            TextField(
                modifier = Modifier.width(300.dp),
                value = correo.value,
                onValueChange = {
                    correo.value = it
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = black,
                    focusedContainerColor = black,
                    unfocusedLabelColor = white,
                    focusedLabelColor = white,
                    focusedIndicatorColor = white,
                    cursorColor = white,
                    focusedTextColor = white
                ),
                placeholder = {
                    Text(
                        text = "Correo",
                        style = androidx.compose.ui.text.TextStyle(
                            color = white,
                            fontSize = 15.sp,
                            letterSpacing = 0.1.em,
                            fontWeight = FontWeight.Normal,
                        )
                    )
                },

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

            Spacer(modifier = Modifier.height(100.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(300.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = white,
                    contentColor = black
                ),
            ) {
                Text(
                    text = "Enviar",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RestablecerContraPreview() {
    //val navController = rememberNavController()
    RestablecerContra(LocalContext.current)
}