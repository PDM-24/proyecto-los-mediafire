package com.ic.cinefile.screens

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ic.cinefile.R
import com.ic.cinefile.activities.BienvenidaActivity
import com.ic.cinefile.activities.RestContraActivity
import com.ic.cinefile.ui.theme.black
import com.ic.cinefile.ui.theme.white


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun contentAvatar() {

    val context = LocalContext.current

    val activity = context as Activity
    val correo = activity.intent.getStringExtra("correo") ?: ""
    val contrasena = activity.intent.getStringExtra("contrasena") ?: ""
    val username = activity.intent.getStringExtra("username") ?: ""
    val birthday = activity.intent.getStringExtra("birthday") ?: ""
    val gender = activity.intent.getStringExtra("gender") ?: ""
    val generosSeleccionados = activity.intent.getStringArrayListExtra("generosSeleccionados")


    val avatarSeleccionado = remember { mutableStateOf<String?>(null) }


    //val Avatarimg =
    //  listOf("avatar1", "avatar2", "avatar3", "avatar3", "avatar4", "avatar5", "avatar6")
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
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
    ){innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Elige tu avatar",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            val avatars = listOf(
                R.drawable.avatar1 to "avatar1",
                R.drawable.avatar2 to "avatar2",
                R.drawable.avatar3 to "avatar3",
                R.drawable.avatar4 to "avatar4",
                R.drawable.avatar5 to "avatar5",
                R.drawable.avatar6 to "avatar6"
            )


            // Mostrar avatares en filas
            avatars.chunked(3).forEach { rowAvatars ->
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    rowAvatars.forEach { (avatarRes, avatarName) ->
                        Image(
                            painter = painterResource(id = avatarRes),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(8.dp)
                                .size(110.dp)
                                .border(
                                    width = 2.dp,
                                    color = if (avatarSeleccionado.value == avatarName) Color.Gray else Color.Transparent
                                )
                                .clickable {
                                    if (avatarSeleccionado.value == avatarName) {
                                        avatarSeleccionado.value = null
                                    } else {
                                        avatarSeleccionado.value = avatarName
                                    }
                                }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            Button(
                onClick = {

                    if (avatarSeleccionado.value == null) {
                        Toast.makeText(context, "Por favor, selecciona un avatar", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val intent = Intent(context, BienvenidaActivity::class.java).apply {
                            putExtra("correo", correo)
                            putExtra("contrasena", contrasena)
                            putExtra("username", username)
                            putExtra("birthday", birthday)
                            putExtra("gender", gender)
                            putStringArrayListExtra(
                                "generosSeleccionados",
                                ArrayList(generosSeleccionados)
                            )

                            //putStringArrayListExtra("generosSeleccionados", ArrayList(generosSeleccionados))
                            putExtra("avatarSeleccionado", avatarSeleccionado.value)
                        }
                        context.startActivity(intent)
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
                    text = "Finalizar",
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


@Preview(showSystemUi = true)
@Composable
fun PreviewSeleccionAvatarScreen() {
    //val navController = rememberNavController()
    contentAvatar()
}
