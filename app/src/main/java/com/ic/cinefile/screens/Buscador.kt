package com.ic.cinefile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.ic.cinefile.R
import com.ic.cinefile.ui.theme.black
import com.ic.cinefile.ui.theme.montserratFamily
import com.ic.cinefile.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Buscador() {
    var buscador by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        containerColor = Color.Black,
        topBar = {
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .padding(6.dp)
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(6.dp)
                ) {
                    if (isSearching) {
                        // Mostrar la flecha hacia atrás solo si estamos en modo de búsqueda
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black,
                            modifier = Modifier
                                .padding(6.dp)
                                .clickable {
                                    isSearching = false
                                    focusManager.clearFocus() // Liberar el enfoque del TextField y cerrar el teclado
                                    keyboardController?.hide() // Cerrar el teclado si está visible
                                }
                        )
                    } else {
                        // Mostrar la lupa solo en la pantalla principal
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_search_24),
                            contentDescription = "Lupa",
                            modifier = Modifier.padding(6.dp)
                        )
                    }

                    TextField(
                        value = buscador,
                        onValueChange = { newBuscador ->
                            buscador = newBuscador
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
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
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                isSearching = true
                                focusManager.clearFocus() // Liberar el enfoque del TextField y cerrar el teclado
                                keyboardController?.hide() // Cerrar el teclado si está visible
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester) // Asociar el FocusRequester al TextField
                            .onFocusChanged { focusState ->
                                isSearching = focusState.isFocused
                            }
                    )
                }
            }
        },
        bottomBar = {
            if (!isSearching) {
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
                                    contentDescription = "Person",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        // Contenido del Scaffold
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black)
        ) {
            if (isSearching) {
                // Mostrar la pantalla de historial de búsqueda dentro de la columna del Scaffold
                SearchHistoryScreen(onBackClick = { isSearching = false })
            } else {
                // Contenido principal
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Box(modifier = Modifier.padding(8.dp) ){
                        Text(text = "Nuevos lanzamientos",
                            style = TextStyle(
                                color = Color.White,
                                textAlign =  TextAlign.Start,
                                fontFamily = montserratFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp

                            )
                        )
                    }

                    LazyRow {

                        item {
                            Image(
                                painter = painterResource(id = R.drawable.dunc),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .height(200.dp),
                            )
                        }

                        item {
                            Image(
                                painter = painterResource(id = R.drawable.godzilla),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .height(200.dp),
                            )
                        }

                        item {
                            Image(
                                painter = painterResource(id = R.drawable.migration),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .height(200.dp),
                            )
                        }

                        item {
                            Image(
                                painter = painterResource(id = R.drawable.garfield),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .height(200.dp),
                            )
                        }
                    }

                    Box(modifier = Modifier.padding(8.dp) ){
                        Text(text = "Lo mas vistos",
                            style = TextStyle(
                                color = Color.White,
                                textAlign =  TextAlign.Start,
                                fontFamily = montserratFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp

                            )
                        )
                    }

                    LazyRow {

                        item {
                            Image(
                                painter = painterResource(id = R.drawable.dunc),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .height(200.dp),
                            )
                        }

                        item {
                            Image(
                                painter = painterResource(id = R.drawable.godzilla),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .height(200.dp),
                            )
                        }

                        item {
                            Image(
                                painter = painterResource(id = R.drawable.migration),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .height(200.dp),
                            )
                        }

                        item {
                            Image(
                                painter = painterResource(id = R.drawable.garfield),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .height(200.dp),
                            )
                        }
                    }

                    Box(modifier = Modifier.padding(8.dp) ){
                        Text(text = "Valoracion 5 estrellas",
                            style = TextStyle(
                                color = Color.White,
                                textAlign =  TextAlign.Start,
                                fontFamily = montserratFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp

                            )
                        )
                    }

                    LazyRow {

                        item {
                            Image(
                                painter = painterResource(id = R.drawable.dunc),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .height(200.dp),
                            )
                        }

                        item {
                            Image(
                                painter = painterResource(id = R.drawable.godzilla),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .height(200.dp),
                            )
                        }

                        item {
                            Image(
                                painter = painterResource(id = R.drawable.migration),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .height(200.dp),
                            )
                        }

                        item {
                            Image(
                                painter = painterResource(id = R.drawable.garfield),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .height(200.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchHistoryScreen(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Historial de Búsqueda",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Historial de búsqueda quemado
        val searchHistory = listOf("Animacion", "Rapidos y furiosos", "Panda")
        for (search in searchHistory) {
            Text(
                text = search,
                color = Color.Gray,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun ViewConteinerBuscador(){
    Buscador()
}