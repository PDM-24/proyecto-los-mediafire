package com.ic.cinefile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ic.cinefile.Navigation.AppScreens
import com.ic.cinefile.components.botonGeneros
import com.ic.cinefile.components.gridGeneros
import com.ic.cinefile.components.valoresGeneros.generos
import com.ic.cinefile.data.accountCreateData
import com.ic.cinefile.ui.theme.black
import com.ic.cinefile.ui.theme.white
import com.ic.cinefile.viewModel.userCreateViewModel


@Composable
fun ElegirGeneros(navController: NavController,userCreateViewModel: userCreateViewModel) {
    var genre_movie by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = "Elige tus géneros cinematrográficos favoritas",
            modifier = Modifier
                .fillMaxWidth(),
            style = TextStyle(
                color = white,
                fontSize = 28.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
            )
        )

        Spacer(modifier = Modifier.height(40.dp))


        LazyVerticalGrid(
            modifier = Modifier.width(300.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(generos.entries.size) { index ->
                val genero = generos.entries[index]
                val (defaultColor, selectedColor) = gridGeneros(genero)
                botonGeneros(
                    generos = genero,
                    selectedColor =selectedColor,
                    defaultColor = defaultColor
                ) {genreName, isSelected ->

                    if (isSelected) {
                        userCreateViewModel.movie_genere.value = userCreateViewModel.movie_genere.value?.plus(genreName) ?: listOf(genreName)
                    } else {
                        userCreateViewModel.movie_genere.value = userCreateViewModel.movie_genere.value?.minus(genreName)
                    }
                    println(userCreateViewModel.movie_genere.value)
            }
            }
        }


        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                userCreateViewModel.movie_genere.value=userCreateViewModel.movie_genere.value
                navController.navigate(AppScreens.Avatar.route)
                println("Datos del userCreateViewModel:")
                println("Username: ${userCreateViewModel.username.value}")
                println("Email: ${userCreateViewModel.email.value}")
                println("Password: ${userCreateViewModel.password.value}")
                println("Año de nacimiento: ${userCreateViewModel.year_nac.value}")
                println("Género: ${userCreateViewModel.genere.value}")
                println("Géneros de películas: ${userCreateViewModel.movie_genere.value}")
                println("Avatar URL: ${userCreateViewModel.avatar.value}")
            },
            modifier = Modifier
                .width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = white,
                contentColor = black
            ),
        ) {
            Text(
                text = "Siguiente",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ElegirGenerosPreview() {
    val navController = rememberNavController()
    val userCreateViewModel = userCreateViewModel()

    ElegirGeneros(navController,userCreateViewModel)
}