package com.ic.cinefile.screens.Administrador

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ic.cinefile.R
import com.ic.cinefile.components.botonGeneros
import com.ic.cinefile.components.gridGeneros
import com.ic.cinefile.components.valoresGeneros.generos
import com.ic.cinefile.ui.theme.black
import com.ic.cinefile.ui.theme.dark_red
import com.ic.cinefile.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarPeli(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black,
                    titleContentColor = white
                ),
                title = {
                    Text(
                        text = "Agregar una película",
                        modifier = Modifier.padding(start = 65.dp)
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable { navController.popBackStack() }
                            .padding(start = 10.dp),
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        tint = white
                    )
                }
            )
        }
    ) { innerPadding ->

        val titulo: MutableState<String> = remember { mutableStateOf("") }
        val sinopsis: MutableState<String> = remember { mutableStateOf("") }
        val duracion: MutableState<String> = remember { mutableStateOf("") }
        val buscador by remember { mutableStateOf("") }
        val generosSeleccionados = remember { mutableStateListOf<String>()}
        val context = LocalContext.current

        //PARA LA IMAGEN DEL POSTER
        var uri by remember {
            mutableStateOf<Uri?>(null)
        }
        val foto = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { resultUri: Uri? ->
                uri = resultUri
            }
        )

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //PARA SUBIR EL POSTER DE LA PELICULA A AGREGAR
            item {
                Box(
                    modifier = Modifier
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    if (uri == null) {
                        //IMAGEN POR DEFECTO SI NO HAY NADA SUBIDO
                        Image(
                            painter = painterResource(id = R.drawable.no_picture),
                            contentDescription = null,
                            modifier = Modifier.size(150.dp, 200.dp)
                        )
                    } else {
                        //IMAGEN SUBIDA
                        AsyncImage(
                            model = uri,
                            contentDescription = null,
                            modifier = Modifier.size(150.dp, 200.dp)

                        )
                    }
                    IconButton(
                        onClick = {
                            foto.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .clip(shape = RoundedCornerShape(2.dp)),
                        colors = IconButtonDefaults.iconButtonColors(dark_red)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = null,
                            tint = white
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
            item{
                Text(
                    text = "Título",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp),
                    style = TextStyle(
                        color = white,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    modifier = Modifier.width(300.dp),
                    value = titulo.value,
                    onValueChange = {
                        titulo.value = it
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = black,
                        focusedContainerColor = black,
                        unfocusedLabelColor = white,
                        focusedLabelColor = white,
                        focusedIndicatorColor = white,
                        cursorColor = white,
                        focusedTextColor = white
                    ),
                    placeholder = {
                        Text(
                            text = "Agregar titulo...",
                            style = TextStyle(
                                color = white,
                                fontSize = 15.sp,
                                letterSpacing = 0.1.em,
                                fontWeight = FontWeight.Normal,
                            )
                        )
                    },
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
            item{
                Text(
                    text = "Sinopsis",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp),
                    style = TextStyle(
                        color = white,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.Normal,
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    modifier = Modifier.width(300.dp),
                    value = sinopsis.value,
                    onValueChange = {
                        sinopsis.value = it
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = black,
                        focusedContainerColor = black,
                        unfocusedLabelColor = white,
                        focusedLabelColor = white,
                        focusedIndicatorColor = white,
                        cursorColor = white,
                        focusedTextColor = white
                    ),
                    placeholder = {
                        Text(
                            text = "Agregar sinopsis...",
                            style = TextStyle(
                                color = white,
                                fontSize = 15.sp,
                                letterSpacing = 0.1.em,
                                fontWeight = FontWeight.Normal,
                            )
                        )
                    },
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            //AGREGAR LA DURACIÓN DE LA PELICULA
            item{
                Text(
                    text = "Duración",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp),
                    style = TextStyle(
                        color = white,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.Normal,
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    modifier = Modifier.width(300.dp),
                    value = duracion.value,
                    onValueChange = {
                        duracion.value = it
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = black,
                        focusedContainerColor = black,
                        unfocusedLabelColor = white,
                        focusedLabelColor = white,
                        focusedIndicatorColor = white,
                        cursorColor = white,
                        focusedTextColor = white
                    ),
                    placeholder = {
                        Text(
                            text = "00:00",
                            style = TextStyle(
                                color = white,
                                fontSize = 15.sp,
                                letterSpacing = 0.1.em,
                                fontWeight = FontWeight.Normal,
                            )
                        )
                    },
                    //SI QUEREMOS QUE SOLO SE INGRESEN NUMEROS DESCOMENTAR LA LINEA DE ABAJO:
                    //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            //BUSQUEDA DE LOS ACTORES DISPONIBLES
            item {
                Text(
                    text = "Actores",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp),
                    style = TextStyle(
                        color = white,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.Normal,
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    modifier = Modifier.width(300.dp),
                    value = buscador,
                    onValueChange = {},
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.LightGray,
                        focusedContainerColor = Color.LightGray,
                        unfocusedLabelColor = black,
                        focusedLabelColor = black,
                        cursorColor = black,
                        focusedTextColor = black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = "Buscar",
                            style = TextStyle(
                                color = black,
                                fontSize = 15.sp,
                                letterSpacing = 0.1.em,
                                fontWeight = FontWeight.Normal,
                            )
                        )
                    },
                    leadingIcon = {
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = null
                            )
                        }
                    },
                    shape = RoundedCornerShape(15.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            //BOTONES PARA ELEGIR LAS CATEGORIAS A LA QUE PERTENECE LA PELICULA
            item {
                Text(
                    text = "Categorias",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp),
                    style = TextStyle(
                        color = white,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.Normal,
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                LazyVerticalGrid(
                    modifier = Modifier
                        .width(300.dp)
                        .height(480.dp),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(generos.entries) { genero ->
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
            }

            //BOTON PARA GUARDAR LA PELICULA AGREGADA
            item {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(300.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = white,
                        contentColor = black
                    ),
                ) {
                    Text(
                        text = "Guardar",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

        }
    }
}

@Preview
@Composable
fun AgregarPeliPreview() {
    AgregarPeli(navController = rememberNavController())
}