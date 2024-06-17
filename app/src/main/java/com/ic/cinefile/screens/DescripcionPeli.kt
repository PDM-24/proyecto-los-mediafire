package com.ic.cinefile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ic.cinefile.R
import com.ic.cinefile.components.botonGuardar
import com.ic.cinefile.components.comentariosSecc.comentarios
import com.ic.cinefile.components.ratingStars
import com.ic.cinefile.components.verTrailer
import com.ic.cinefile.ui.theme.black
import com.ic.cinefile.ui.theme.light_yellow
import com.ic.cinefile.ui.theme.sky_blue
import com.ic.cinefile.ui.theme.white


@Composable
fun descripcionPeli(
    onClick: () -> Unit,
    id: String,
    imagePainter: Painter,
    description: String
) {

    var isBookmarked by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        //Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.deadpoll),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth()
        )

        //Para volver atrás
        IconButton(
            onClick = {/*navController.popBackStack() para volver atrás*/ },
            colors = IconButtonDefaults.iconButtonColors(black)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "",
                tint = white
            )
        }

        //Card de información de la peli
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.Black),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                //Titulo y guardar
                item {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Deadpool",
                            fontSize = 28.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(200.dp))
                        //guardado o no
                        botonGuardar(
                            onClick = {
                                isBookmarked = !isBookmarked
                            },
                            isBookmarked = isBookmarked
                        )
                    }
                }

                //Tiempo, calificacion y fecha de peli
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painterResource(id = R.drawable.baseline_time_24),
                            contentDescription = "Time",
                            tint = white
                        )
                        Text(
                            text = "2h",
                            color = white,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(start = 6.dp, end = 8.dp)
                        )
                        Icon(
                            painterResource(id = R.drawable.baseline_star_rate_24),
                            contentDescription = "rate",
                            tint = light_yellow
                        )
                        Text(
                            text = "4",
                            color = white,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(start = 4.dp, end = 8.dp)
                        )
                        Icon(
                            painterResource(id = R.drawable.baseline_calendar_24),
                            contentDescription = "Calendar",
                            tint = white
                        )
                        Text(
                            text = "02/20/23",
                            color = white,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(start = 6.dp, end = 8.dp)
                        )
                    }
                }

                //Categorias
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        //iteración de categorias dentro del row
                        repeat(3) { categoriaIndex ->
                            Card(
                                modifier = Modifier
                                    .wrapContentSize(),
                                colors = CardDefaults.cardColors(containerColor = sky_blue)
                            ) {
                                Text(
                                    text = "Acción",
                                    color = Color.Black,
                                    modifier = Modifier.padding(6.dp)
                                )
                            }
                        }
                    }
                }

                //DESCRIPCION PELI
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                        lineHeight = 20.sp,
                        color = Color.White
                    )
                }

                //TRAILER
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                    verTrailer(onClick)
                }

                //CALIFICACION
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Calificar",
                        color = white,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                    ratingStars(rating = 1)
                }

                //ACTORES
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Actores",
                        lineHeight = 20.sp,
                        color = white,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(8) { actoresIndex ->
                            Column(
                                modifier = Modifier
                                    .wrapContentSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.reynolds),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(CircleShape)
                                )
                                Text(
                                    text = "Ryan \nReynolds",
                                    color = white,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(top = 6.dp)
                                )
                            }
                        }
                    }
                }

                //COMENTARIOS
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    comentarios(id = id, description = description, imagePainter = imagePainter)
                }
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun descripcionPeliPreview() {
    var isBookmarked by remember { mutableStateOf(false) }
    descripcionPeli(onClick = { isBookmarked = !isBookmarked })
}*/