package com.ic.cinefile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ic.cinefile.R
import com.ic.cinefile.ui.theme.black
import com.ic.cinefile.ui.theme.grisComment
import com.ic.cinefile.ui.theme.light_yellow
import com.ic.cinefile.ui.theme.sky_blue
import com.ic.cinefile.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calificadas(
    //navController: NavController
) {
    //Listas de valores para prueba
    val peliculas: MutableList<Int> = remember { mutableListOf(R.drawable.deadpoll, R.drawable.deadpoll) }
    val calificacion: MutableList<Int> = remember { mutableListOf(5,4) }
    val titulo: MutableList<String> = remember { mutableListOf("Deadpool", "Deadpool") }
    val categoria: MutableList<String> = remember { mutableListOf("Accion", "Comedia") }
    val fechaLanzamiento: MutableList<String> = remember { mutableListOf("23/10/2012", "26/10/2012") }


    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black,
                    titleContentColor = white
                ),
                title = {
                    Row(
                        modifier = Modifier.padding(start = 100.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_star_rate_24),
                            contentDescription = null,
                            tint = light_yellow
                        )
                        Text(text = "Calificadas")
                    }
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable { /*navController.popBackStack() para volver atrás*/ },
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        tint = white
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Black,
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Home",
                                tint = white
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Perfil",
                                tint = white
                            )
                        }
                    }
                }
            )
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //
            Section(peliculas,calificacion,titulo,categoria,fechaLanzamiento)

        }
    }

}

//para iterar los bloques de las pelis que el usuario ya tiene calificadas
@Composable
//se le pasa una lista de peliculas y una lista de numeros que sería el número de estrellas que le ha dado el usuario a la película
//también las categorias de la peli, el titulo y la fecha de lanzamiento
fun Section(pelicula: List<Int>, rating: List<Int>, titulo: List<String>, categoria: List<String>, fecha: List<String>) {
    Column {
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            //se agregan las películas dentro de la lista que entra a los posters y se van agregando a la columna
            items(pelicula.size) { index ->
                BloquePeli(
                    pelicula[index],
                    rating[index],
                    titulo[index],
                    fecha[index],
                    categoria[index]
                )
                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}


//Contenedor de la peli y su info básica
@Composable
fun BloquePeli(poster: Int, rating: Int, titulo: String, fechaLanzamiento: String, categoria: String) {
    Row(
        modifier = Modifier
            .background(black),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        //Poster de la peli
        Box(
            modifier = Modifier
                .size(100.dp, 150.dp)
        ) {
            Image(
                painter = painterResource(id = poster),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(
                        grisComment,
                        shape = RoundedCornerShape(bottomStart = 8.dp, topStart = 8.dp)
                    )
                    .clickable {
                        //navController.navigate(route = screenRoute.descripcionPeli.route + "/${movie.id}")
                    }
                    .padding(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_star_rate_24),
                        contentDescription = null,
                        tint = light_yellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = rating.toString(),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(15.dp))
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = titulo,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                //iteración de categorias dentro del row, están en repetición
                repeat(2) { categoriaIndex ->
                    Card(
                        modifier = Modifier
                            .wrapContentSize(),
                        colors = CardDefaults.cardColors(containerColor = sky_blue)
                    ) {
                        Text(
                            text = categoria,
                            color = Color.Black,
                            modifier = Modifier.padding(6.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_calendar_24),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = fechaLanzamiento,
                    color = white,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun calificadasPreview() {
    Calificadas()
}