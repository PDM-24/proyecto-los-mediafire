package com.ic.cinefile.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ic.cinefile.screens.Login

class CrearCuentaInicSesActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            /* esta es la  actividad que te lleva al inicio de sesion
            * desde la pantalla de Crear Cuenta */

            Login()

        }
    }

}