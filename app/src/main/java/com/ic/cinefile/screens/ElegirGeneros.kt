package com.ic.cinefile.screens

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ic.cinefile.Navigation.screenRoute
import com.ic.cinefile.components.botonGeneros
import com.ic.cinefile.components.gridGeneros
import com.ic.cinefile.components.valoresGeneros.generos
import com.ic.cinefile.ui.theme.black
import com.ic.cinefile.ui.theme.white
import com.ic.cinefile.viewModel.userCreateViewModel


@Composable
fun ElegirGeneros(viewModel: userCreateViewModel, navController : NavController) {

    val context = LocalContext.current

    val accountData by viewModel.accountcreateAPIData
    val generosSeleccionados = remember { mutableStateListOf<String>().apply { addAll(accountData.movieGenereList) } }
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
            items(generos.values().toList()) { genero ->
                val (defaultColor, selectedColor) = gridGeneros(genero)
                val isGenreSelected = generosSeleccionados.contains(genero.name)
                val isMaxReached = generosSeleccionados.size >= 6

                // Deshabilitar el botón si se ha alcanzado el límite de géneros
                val isEnabled = !isMaxReached || isGenreSelected

                botonGeneros(
                    generos = genero,
                    selectedColor = if (isGenreSelected) selectedColor else defaultColor,
                    defaultColor = defaultColor,
                    onClick =  {
                        if (isGenreSelected) {
                            generosSeleccionados.remove(genero.name)
                        } else {
                            if (!isMaxReached) {
                                if (!generosSeleccionados.contains(genero.name)) {
                                    generosSeleccionados.add(genero.name)
                                }
                            } else {
                                // Mostrar mensaje de error al usuario (Toast)
                                Toast.makeText(context, "¡Máximo 6 géneros!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },

                    isEnabled = isEnabled// Pasar el estado de habilitación al botón
                )
            }
        }


        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {

                if (generosSeleccionados.size >= 3) {
                    viewModel.updateMovieGenres(generosSeleccionados)

                    navController.navigate(screenRoute.contentAvatar.route)
                    Log.d("generos_movies","generos:${accountData.movieGenereList}")
                    Log.d("generos_movies","genere:${accountData}")

                } else {
                    Toast.makeText(
                        context,
                        "Selecciona al menos 3 géneros cinematrográficos",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            },
            modifier = Modifier
                .width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = white,
                contentColor = black
            ),

            enabled = generosSeleccionados.size >= 5 // Habilitar el botón si se han seleccionado al menos 5 géneros

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


//@Preview(showBackground = true)
//@Composable
//fun ElegirGenerosPreview() {
//    //val navController = rememberNavController()
//    ElegirGeneros()
//}

