package com.ic.cinefile.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ic.cinefile.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.ic.cinefile.Navigation.screenRoute
import com.ic.cinefile.components.LoadingProgressDialog
import com.ic.cinefile.ui.theme.black
import com.ic.cinefile.ui.theme.montserratFamily
import com.ic.cinefile.ui.theme.white
import com.ic.cinefile.viewModel.UiState
import com.ic.cinefile.viewModel.UserDataState
import com.ic.cinefile.viewModel.userCreateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(viewModel: userCreateViewModel, navController: NavController) {
    var buscador by remember { mutableStateOf("") }
    val context = LocalContext.current
    val addScreenState = viewModel.uiState.collectAsState()
    val userDataState by viewModel.userDataState.collectAsState()
    var showReloadButton by remember { mutableStateOf(false) }

    LaunchedEffect(addScreenState.value) {
        when (addScreenState.value) {
            is UiState.Error -> {
                val message = (addScreenState.value as UiState.Error).msg
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                viewModel.setStateToReady()
            }
            UiState.Loading -> {
                // Puedes agregar algún indicador de carga general aquí si es necesario
            }
            UiState.Ready -> {}
            is UiState.Success -> {
                val token = (addScreenState.value as UiState.Success).token
                viewModel.fetchUserData(token) // Llama a getUserData para obtener la información del usuario
                viewModel.setStateToReady()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .padding(0.dp)
                    .height(50.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black
                ),
                title = {},
                navigationIcon =
                {
                    IconButton(
                        onClick = {
                            // Acción del botón de navegación
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "",
                            tint = white,
                            modifier = Modifier.size(40.dp)
                        )
                    }
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
                        IconButton(onClick = {navController.navigate(screenRoute.Home.route)}) {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Home",
                                tint = white
                            )
                        }
                        IconButton(onClick = { navController.navigate(screenRoute.PerfilAnuncios.route)
                        }) {
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
        if (userDataState is UserDataState.Loading) {
            LoadingAnimation()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(black)
                    .verticalScroll(rememberScrollState())
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Surface(
                        color = Color.White,
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier.padding(6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(screenRoute.Buscador.route)
                                    },
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Icon(
                                    modifier = Modifier.padding(6.dp),
                                    painter = painterResource(id = R.drawable.baseline_search_24),
                                    contentDescription = "Lupa"
                                )

                                TextField(
                                    value = buscador,
                                    onValueChange = { newBuscador -> buscador = newBuscador },
                                    colors = TextFieldDefaults.colors(
                                        unfocusedContainerColor = Color.Transparent,
                                        focusedContainerColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        focusedIndicatorColor = Color.Transparent,
                                        disabledIndicatorColor = Color.Transparent
                                    ),
                                    placeholder = {
                                        Text(
                                            text = "Buscar",
                                            style = TextStyle(
                                                color = Color.Gray,
                                                fontSize = 15.sp,
                                                letterSpacing = 0.1.em,
                                                fontWeight = FontWeight.Normal
                                            )
                                        )
                                    },
                                    textStyle = TextStyle(color = black),
                                    singleLine = true,
                                    modifier = Modifier.padding(start = 20.dp)
                                )
                            }
                        }


                        IconButton(onClick = {

                            navController.navigate(route = screenRoute.Notificaciones.route)


                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_notifications_24),
                                tint = Color.White,
                                contentDescription = "notificaciones"
                            )
                        }
                    }


                }

                when (userDataState) {
                    is UserDataState.Success -> {
                        val movieCategories = (userDataState as UserDataState.Success).userData.movies
                        movieCategories.forEach { (category, movies) ->
                            Box(modifier = Modifier.padding(8.dp)) {
                                Text(
                                    text = category,
                                    style = TextStyle(
                                        color = Color.White,
                                        textAlign = TextAlign.Start,
                                        fontFamily = montserratFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 20.sp
                                    )
                                )
                            }

                            LazyRow {
                                items(movies.size) { index ->
                                    val movie = movies[index]

                                    Box(
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .clickable {
                                                // Aquí navegas a la pantalla de descripción de la película
                                                navController.navigate(route = screenRoute.descripcionPeli.route + "/${movie.id}")
                                            }
                                    ) {
                                        val painter = rememberAsyncImagePainter(model = movie.posterUrl)
                                        val painterState = painter.state

                                        Box(
                                            modifier = Modifier
                                                .height(200.dp)
                                                .width(150.dp)
                                        ) {
                                            if (painterState is AsyncImagePainter.State.Error) {
                                                Text(
                                                    text = "Error al cargar la imagen",
                                                    color = Color.Red,
                                                    modifier = Modifier.padding(16.dp)
                                                )
                                            } else {
                                                AsyncImage(
                                                    model = movie.posterUrl,
                                                    contentDescription = null,
                                                    contentScale = ContentScale.Crop,
                                                    modifier = Modifier.fillMaxSize()
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    is UserDataState.Loading -> {
                        // Mostrar diálogo de carga o indicador de progreso
                        LoadingProgressDialog()
                    }
                    else -> {}
                }
            }
        }
    }
}

@Composable
fun LoadingAnimation() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.White,
            modifier = Modifier.size(36.dp)
        )
    }
}


@Preview
@Composable
fun HomePreview(){
    Home(viewModel = userCreateViewModel(), navController = rememberNavController())
}