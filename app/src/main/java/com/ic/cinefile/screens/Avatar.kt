package com.ic.cinefile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ic.cinefile.Navigation.AppScreens
import com.ic.cinefile.R
import com.ic.cinefile.viewModel.userCreateViewModel


@Composable
fun contentAvatar(navController: NavController,userCreateViewModel: userCreateViewModel) {

    val Avatarimg = listOf(R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3)
    val Avatarimg2 = listOf(R.drawable.avatar4, R.drawable.avatar5, R.drawable.avatar6)
    var avatar by remember { mutableStateOf("") }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
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

        Row (verticalAlignment = Alignment.CenterVertically) {
            Avatarimg.forEach { img ->

                Image(
                    painter = painterResource(id = img),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(110.dp)
                        .clickable {
                            val selectedAvatarUrl = "drawable://" + img.toString()

                            avatar=selectedAvatarUrl
                            println("Avatar seleccionado: $avatar")

                        }
                )

            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row (verticalAlignment = Alignment.CenterVertically){
            Avatarimg2.forEach { img ->

                Image(
                    painter = painterResource(id = img),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(110.dp)
                        .clickable {
                            val selectedAvatarUrl = "drawable://" + img.toString()

                                       avatar=selectedAvatarUrl
                                println("Avatar seleccionado: $avatar")

                        }
                )

            }
        }
        Spacer(modifier = Modifier.height(120.dp))

        Button(
            onClick = {
                userCreateViewModel.avatar.value = avatar
                userCreateViewModel.createAccountUser()

                println("Datos del userCreateViewModel:")
                println("Username: ${userCreateViewModel.username.value}")
                println("Email: ${userCreateViewModel.email.value}")
                println("Password: ${userCreateViewModel.password.value}")
                println("Año de nacimiento: ${userCreateViewModel.year_nac.value}")
                println("Género: ${userCreateViewModel.genere.value}")
                println("Géneros de películas: ${userCreateViewModel.movie_genere.value}")
                println("Avatar URL: ${userCreateViewModel.avatar.value}")

                navController.navigate(AppScreens.CrearPerfil.route)
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
    val navController = rememberNavController()
    val userCreateViewModel = userCreateViewModel()

    contentAvatar(navController,userCreateViewModel)
}