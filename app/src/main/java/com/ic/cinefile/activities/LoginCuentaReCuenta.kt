package com.ic.cinefile.activities

import com.ic.cinefile.screens.RestablecerContra
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext


class LoginCuentaReCuenta : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Llama al composable de la pantalla "CrearPerfil"
            RestablecerContra(LocalContext.current)
        }
    }
}