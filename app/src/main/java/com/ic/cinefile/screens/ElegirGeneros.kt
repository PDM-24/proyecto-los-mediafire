package com.ic.cinefile.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ic.cinefile.activities.ElegirGeneroActivity
import com.ic.cinefile.components.botonGeneros
import com.ic.cinefile.components.gridGeneros
import com.ic.cinefile.components.valoresGeneros.generos
import com.ic.cinefile.ui.theme.black
import com.ic.cinefile.ui.theme.white


@Composable
fun ElegirGeneros(context: Context) {

    val activity = context as Activity
    val correo = activity.intent.getStringExtra("correo") ?: ""
    val contrasena = activity.intent.getStringExtra("contrasena") ?: ""
    val username = activity.intent.getStringExtra("username") ?: ""
    val birthday = activity.intent.getStringExtra("birthday") ?: ""
    val gender = activity.intent.getStringExtra("gender") ?: ""

    val generosSeleccionados = remember { mutableStateListOf<String>() }

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
                botonGeneros(
                    generos = genero,
                    selectedColor = selectedColor,
                    defaultColor = defaultColor,
                    onClick = {
                        if (generosSeleccionados.contains(genero.name)) {
                            generosSeleccionados.remove(genero.name)
                        } else {
                            generosSeleccionados.add(genero.name)
                        }
                    }
                )
            }
        }


        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {

                val intent = Intent(context, ElegirGeneroActivity::class.java).apply {
                    putExtra("correo", correo)
                    putExtra("contrasena", contrasena)
                    putExtra("username", username)
                    putExtra("birthday", birthday)
                    putExtra("gender", gender)
                    putStringArrayListExtra("generosSeleccionados", ArrayList(generosSeleccionados))
                }
                context.startActivity(intent)

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
    //val navController = rememberNavController()
    ElegirGeneros(LocalContext.current)
}

