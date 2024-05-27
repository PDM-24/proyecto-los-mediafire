package com.ic.cinefile.activities


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.ic.cinefile.screens.Content
import com.ic.cinefile.screens.contentAvatar

class ElegirGeneroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            contentAvatar(LocalContext.current,,,,,,)
        }
    }
}
