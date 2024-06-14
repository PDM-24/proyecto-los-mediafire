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
import androidx.navigation.NavController
import com.ic.cinefile.ui.theme.black
import com.ic.cinefile.ui.theme.montserratFamily
import com.ic.cinefile.ui.theme.white


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Home(){
    var buscador by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(0.dp)
                    .height(50.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black
                ),
                title = {},
                navigationIcon =
                {
                    IconButton(
                        onClick = {


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
                                contentDescription = "",
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
            Row (
                modifier = Modifier
                    .padding(start = 12.dp),

                verticalAlignment = Alignment.CenterVertically

            ){
                Surface (
                    color = Color.White,
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.padding(6.dp)
                ){
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier.padding(6.dp),
                            painter = painterResource(id = R.drawable.baseline_search_24 ),
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
                                disabledIndicatorColor = Color.Transparent),
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

                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_notifications_24 ),
                        tint = Color.White,
                        contentDescription = "notificaciones"
                    )
                }

            }
            Box(modifier = Modifier.padding(8.dp) ){
                Text(text = "Animacion",
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
                Text(text = "Drama",
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
                Text(text = "Comedia",
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

@Preview(showSystemUi = true)
@Composable
fun ViewConteinerHome(){
    Home()
}