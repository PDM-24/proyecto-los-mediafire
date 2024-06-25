package com.ic.cinefile.screens

import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ic.cinefile.Navigation.screenRoute
import com.ic.cinefile.R
import com.ic.cinefile.ui.theme.black
import com.ic.cinefile.ui.theme.white


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilOtroUsuario() {

    val context = LocalContext.current


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
                        modifier = Modifier
                            .clickable { /*navController.popBackStack() para volver atrás*/ }
                            .padding(start = 10.dp),
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
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Home",
                                tint = white
                            )
                        }
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "User",
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


            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.avatar1),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Column {
                    Text(
                        color = white,
                        fontSize = 20.sp,
                        text = "pepe",
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        color = white,
                        fontSize = 14.sp,
                        text = "Hombre",
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )

                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            // LISTAS
            LazyColumn(
                modifier = Modifier.padding(10.dp)
            ) {
                //LISTA DE DESEOS
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Lista de deseos",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = white,
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .clickable { /*que abra la lista de todas las pelis en lista de deseos*/ }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    SectionOtroUsuario(movies = listOf(R.drawable.deadpoll))

                    //LISTA DE CALIFICADAS
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = "Calificadas",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = white,
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .clickable { /*que abra la lista de todas las pelis calificadas*/ }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    SectionSinanuncios(
                        movies = listOf(
                            R.drawable.deadpoll,
                            R.drawable.deadpoll,
                            R.drawable.deadpoll,
                            R.drawable.deadpoll,
                            R.drawable.deadpoll,
                            R.drawable.deadpoll
                        )
                    )
                }
            }
        }
    }
}

//para iterar los posters de pelis
@Composable
fun SectionOtroUsuario(movies: List<Int>) {
    Column() {
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies.size) { index ->
                Postersinanuncios(movies[index])
            }
        }
    }
}

//Poster de la peli
@Composable
fun PosterOtrousuario(imageRes: Int) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = Modifier
            .size(150.dp, 230.dp)
            .clickable { /*que te lleve a la descripcion de esa peli */ },
        contentScale = ContentScale.Crop
    )
}

fun getAvatarResourcesotrousuario(avatarName: String): Int {
    return when (avatarName) {
        "avatar1" -> R.drawable.avatar1
        "avatar2" -> R.drawable.avatar2
        "avatar3" -> R.drawable.avatar3
        "avatar4" -> R.drawable.avatar4
        "avatar5" -> R.drawable.avatar5
        "avatar6" -> R.drawable.avatar6
        else -> R.drawable.avatar4 // Imagen por defecto si el nombre del avatar no coincide
    }
}

@Preview
@Composable
fun perfilOtroUsuario() {
    PerfilOtroUsuario()
}

