package com.ic.cinefile.activities


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.ic.cinefile.screens.contentAvatar
import com.ic.cinefile.screens.contentGenero

class CrearPerfilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            contentGenero()
        }
    }
}
