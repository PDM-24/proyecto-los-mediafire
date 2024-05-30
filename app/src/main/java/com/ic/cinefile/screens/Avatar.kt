package com.ic.cinefile.screens

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

    val Avatarimg =
        listOf("avatar1", "avatar2", "avatar3", "avatar3", "avatar4", "avatar5", "avatar6")

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

        Spacer(modifier = Modifier.height(120.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.avatar1),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(110.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.avatar2),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(110.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.avatar3),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(110.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.avatar4),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(110.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.avatar5),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(110.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.avatar6),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(110.dp)
            )
        }

        Spacer(modifier = Modifier.height(120.dp))

        Button(
            onClick = {

                val intent = Intent(context, BienvenidaActivity::class.java)
                intent.putExtra("correo", correo)
                intent.putExtra("contrasena", contrasena)
                intent.putExtra("username", username)
                intent.putExtra("birthday", birthday)
                intent.putExtra("gender", gender)
                intent.putStringArrayListExtra(
                    "generosSeleccionados", ArrayList(
                        generosSeleccionados!!
                    )
                )
                intent.putStringArrayListExtra("Avatar", ArrayList(Avatarimg))
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


@Preview(showSystemUi = true)
@Composable
fun PreviewSeleccionAvatarScreen() {
    //val navController = rememberNavController()
    contentAvatar()
}
