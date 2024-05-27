package com.ic.cinefile.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ic.cinefile.screens.Login

//creas una clase tipo mainActivity para cada vista
class HomeAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            //llamas la screen a la vista que querres navegar, es decir, aca digo que de la b
            // bienvenida ir al login
            Login()

        }
    }
}



