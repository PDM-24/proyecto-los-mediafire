package com.ic.cinefile.screens

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ic.cinefile.Navigation.screenRoute

import com.ic.cinefile.R
//import com.ic.cinefile.activities.HomeAppActivity
//import com.ic.cinefile.activities.HomeAppActivity2
import com.ic.cinefile.ui.theme.black
import com.ic.cinefile.ui.theme.white

@Composable

//pasas como contexto parametro a la funcion
//que partira de navegacion home->login
fun HomeAppScreen(navController: NavController) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = "¡Bienvenido!",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                color = white,
                fontSize = 28.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(220.dp)
        )

        Spacer(
            modifier = Modifier
                .height(30.dp)
        )

        Button(
            onClick = {
//                //login
//                //aca es puero copiar pagar, nombreActivity::class.java
//                val intent = Intent(context, HomeAppActivity::class.java)
//                intent.putExtra("indexItem", 0)
//                context.startActivity(intent)
//                (context as Activity)

                navController.navigate(screenRoute.Login.route)


            },
            modifier = Modifier
                .width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = white,
                contentColor = black
            ),
        ) {
            Text(
                text = "Iniciar Sesión",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                )
            )
        }

        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        Button(
            onClick = {
//                val intent = Intent(context, HomeAppActivity2::class.java)
//                intent.putExtra("indexItem", 0)
//                context.startActivity(intent)
//                (context as Activity)

                //crear cuenta
                navController.navigate(screenRoute.CrearCuenta.route)

            },
            modifier = Modifier
                .width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = white,
                contentColor = black
            ),
        ) {
            Text(
                text = "Registrarse",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}


//@Preview(showSystemUi = true)
//@Composable
//fun PreviewHomeAppScreen() {
//    HomeAppScreen()
//}
