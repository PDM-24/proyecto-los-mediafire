package com.ic.cinefile.activities


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ic.cinefile.screens.ElegirGeneros

class ElegirGeneroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //ElegirGeneros()
        }
    }
}
