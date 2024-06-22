package com.ic.cinefile.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ic.cinefile.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.ic.cinefile.ui.theme.black
import com.ic.cinefile.ui.theme.montserratFamily
import com.ic.cinefile.ui.theme.white


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun PantallaCategoria(context: Context){
    var buscador by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black,
                    titleContentColor = white
                ),
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Comedia",
                            color = white
                        )
                    }
                },
                navigationIcon = {
                    Icon(
                        //modifier = Modifier.clickable { /navController.popBackStack() para volver atrás/ },
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

    ){innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black)
                .verticalScroll(rememberScrollState()),

            ) {

            Spacer(modifier = Modifier.size(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val imageModifier = Modifier
                    .padding(4.dp)
                    .size(150.dp)  // Asegura que todas las imágenes tengan el mismo tamaño

                Box(
                    modifier = Modifier.weight(1f)  // Para que cada Box ocupe el mismo espacio horizontal
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dunc),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.godzilla),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.migration),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.garfield),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }
            }

            Spacer(modifier = Modifier.size(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val imageModifier = Modifier
                    .padding(4.dp)
                    .size(150.dp)  // Asegura que todas las imágenes tengan el mismo tamaño

                Box(
                    modifier = Modifier.weight(1f)  // Para que cada Box ocupe el mismo espacio horizontal
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dunc),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.godzilla),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.migration),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.garfield),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }
            }

            Spacer(modifier = Modifier.size(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val imageModifier = Modifier
                    .padding(4.dp)
                    .size(150.dp)  // Asegura que todas las imágenes tengan el mismo tamaño

                Box(
                    modifier = Modifier.weight(1f)  // Para que cada Box ocupe el mismo espacio horizontal
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dunc),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.godzilla),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.migration),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.garfield),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }
            }



            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val imageModifier = Modifier
                    .padding(4.dp)
                    .size(150.dp)  // Asegura que todas las imágenes tengan el mismo tamaño

                Box(
                    modifier = Modifier.weight(1f)  // Para que cada Box ocupe el mismo espacio horizontal
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dunc),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.godzilla),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.migration),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.garfield),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }
            }



            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val imageModifier = Modifier
                    .padding(4.dp)
                    .size(150.dp)  // Asegura que todas las imágenes tengan el mismo tamaño

                Box(
                    modifier = Modifier.weight(1f)  // Para que cada Box ocupe el mismo espacio horizontal
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dunc),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.godzilla),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.migration),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.garfield),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val imageModifier = Modifier
                    .padding(4.dp)
                    .size(150.dp)  // Asegura que todas las imágenes tengan el mismo tamaño

                Box(
                    modifier = Modifier.weight(1f)  // Para que cada Box ocupe el mismo espacio horizontal
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dunc),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.godzilla),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.migration),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.garfield),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }
            }

        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun ViewConteinerPantallaCategoria(){
    PantallaCategoria(LocalContext.current)
}