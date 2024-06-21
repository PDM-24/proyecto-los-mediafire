package com.ic.cinefile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.ic.cinefile.R
import com.ic.cinefile.ui.theme.black
import com.ic.cinefile.ui.theme.dark_blue
import com.ic.cinefile.ui.theme.grisComment
import com.ic.cinefile.ui.theme.light_yellow
import com.ic.cinefile.ui.theme.montserratFamily
import com.ic.cinefile.ui.theme.sky_blue
import com.ic.cinefile.ui.theme.white

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Resultadobuscador() {

    val peliculas: MutableList<Int> = remember { mutableListOf(R.drawable.garfield, R.drawable.godzilla, R.drawable.godzilla, R.drawable.godzilla) }
    val calificacion: MutableList<Int> = remember { mutableListOf(5, 4, 6, 7) }
    val titulo: MutableList<String> = remember { mutableListOf("Garfield", "Deadpool", "Deadpool", "Deadpool") }
    val categoria: MutableList<String> = remember { mutableListOf("Accion", "Comedia", "Terror", "Alegria") }
    val fechaLanzamiento: MutableList<String> = remember { mutableListOf("23/10/2012", "26/10/2012", "26/10/2012", "26/10/2012") }
    var buscador by remember { mutableStateOf("") }

    // Lista mutable para almacenar el estado de marcado de cada película
    val bookmarkedStates: MutableList<Boolean> = remember { mutableStateListOf(false, false, false, false) }

    var isMenuExpanded by remember { mutableStateOf(false) }
    var isGenreMenuExpanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }

    Scaffold(
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
                                tint = Color.White
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Perfil",
                                tint = Color.White
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
                    .padding(start = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.padding(6.dp, top = 16.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
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
                            textStyle = TextStyle(color = Color.Black),
                            singleLine = true
                        )
                    }
                }

                Box {
                    IconButton(onClick = { isMenuExpanded = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_checklist_24),
                            tint = Color.White,
                            contentDescription = "Filtros"
                        )
                    }

                    DropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissRequest = { isMenuExpanded = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        DropdownMenuItem(
                            text = { Text("Mejores calificadas") },
                            onClick = {
                                selectedOption = "Mejores calificadas"
                                isMenuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Recientes") },
                            onClick = {
                                selectedOption = "Recientes"
                                isMenuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Más viejas") },
                            onClick = {
                                selectedOption = "Más viejas"
                                isMenuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Género") },
                            onClick = {
                                isMenuExpanded = false
                                isGenreMenuExpanded = true
                            }
                        )
                    }

                    DropdownMenu(
                        expanded = isGenreMenuExpanded,
                        onDismissRequest = { isGenreMenuExpanded = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        DropdownMenuItem(
                            text = { Text("Terror") },
                            onClick = {
                                selectedOption = "Terror"
                                isGenreMenuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Comedia") },
                            onClick = {
                                selectedOption = "Comedia"
                                isGenreMenuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Familia") },
                            onClick = {
                                selectedOption = "Familia"
                                isGenreMenuExpanded = false
                            }
                        )
                    }
                }
            }

            Section(
                peliculas,
                calificacion,
                titulo,
                categoria,
                fechaLanzamiento,
                bookmarkedStates
            ) { index, isBookmarked ->
                // Actualizar el estado de marcado para la película específica
                bookmarkedStates[index] = isBookmarked
            }
        }
    }
}

@Composable
fun Section(
    peliculas: List<Int>,
    rating: List<Int>,
    titulo: List<String>,
    categoria: List<String>,
    fecha: List<String>,
    bookmarkedStates: MutableList<Boolean>,
    onBookmarkClick: (Int, Boolean) -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            items(peliculas.size) { index ->
                Peli(
                    poster = peliculas[index],
                    rating = rating[index],
                    titulo = titulo[index],
                    fechaLanzamiento = fecha[index],
                    categoria = categoria[index],
                    isBookmarked = bookmarkedStates[index],
                    onBookmarkClick = { isBookmarked ->
                        onBookmarkClick(index, isBookmarked)
                    }
                )
                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}

@Composable
fun Peli(poster: Int, rating: Int, titulo: String, fechaLanzamiento: String, categoria: String,isBookmarked: Boolean,
         onBookmarkClick: (Boolean) -> Unit) {
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

                IconButton(
                    onClick = { onBookmarkClick(!isBookmarked) }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_bookmark_24),
                        contentDescription = null,
                        tint = if (isBookmarked) Color.Yellow else Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ViewConteinerResultados(){
    Resultadobuscador()
}