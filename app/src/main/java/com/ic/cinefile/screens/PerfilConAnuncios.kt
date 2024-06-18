package com.ic.cinefile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ic.cinefile.R
import com.ic.cinefile.ui.theme.black
import com.ic.cinefile.ui.theme.grisComment
import com.ic.cinefile.ui.theme.light_red
import com.ic.cinefile.ui.theme.white


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilAnuncios(

) {
    var nombreUsuario = "Pepito Curioso"
    var avatarUsuario = painterResource(id = R.drawable.reynolds)

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black,
                    titleContentColor = white
                ),
                title = {},
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
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = Color.White
                            )
                        }
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

            //FOTO DE PERFIL DEL USUARIO Y NOMBRE
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = avatarUsuario,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Text(
                    color = white,
                    fontSize = 20.sp,
                    text = nombreUsuario,
                    modifier = Modifier
                        .padding(start = 12.dp)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            //BOTON PARA QUITAR ANUCIOS
            Button(
                onClick = { /*lo de quitar anuncios y actualizar el peril sin anuncios*/ },
                colors = ButtonDefaults.buttonColors(containerColor = grisComment),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.hide_source_24),
                    contentDescription = null,
                    tint = light_red
                )
                Text(
                    text = "Eliminar anuncios\nDisfruta una mejor experiencia \nsin anuncios",
                    modifier = Modifier.padding(start = 8.dp),
                    textAlign = TextAlign.Center,
                    color = white
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            //LISTAS
            Column (
                modifier = Modifier.padding(10.dp)
            ){
                //LISTA DE DESEOS
                Spacer(modifier = Modifier.height(20.dp))
                Section(
                    title = "Lista de deseos",
                    movies = listOf(R.drawable.deadpoll)
                )

                //LISTA DE CALIFICADAS
                Spacer(modifier = Modifier.height(40.dp))
                Section(
                    title = "Calificadas",
                    movies = listOf(R.drawable.deadpoll,R.drawable.deadpoll, R.drawable.deadpoll, R.drawable.deadpoll,R.drawable.deadpoll, R.drawable.deadpoll)
                )
            }
        }
    }
}

//para iterar los posters de pelis
@Composable
fun Section(title: String, movies: List<Int>) {
    Column () {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = white,
            modifier = Modifier.padding(start = 15.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies.size) { index ->
                Poster(movies[index])
            }
        }
    }
}

//Poster de la peli
@Composable
fun Poster(imageRes: Int) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = Modifier
            .size(100.dp, 150.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun perfilAnunciosPreview() {
    PerfilAnuncios()
}