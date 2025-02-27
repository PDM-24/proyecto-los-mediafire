package com.ic.cinefile.screens.Administrador

import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ic.cinefile.R
import com.ic.cinefile.components.ratingStars
import com.ic.cinefile.components.verTrailer
import com.ic.cinefile.screens.Administrador.SeccComentariosAdmin.comentariosAdmin
import com.ic.cinefile.ui.theme.dark_red
import com.ic.cinefile.ui.theme.light_yellow
import com.ic.cinefile.ui.theme.sky_blue
import com.ic.cinefile.ui.theme.white
import com.ic.cinefile.viewModel.AverageRatingState
import com.ic.cinefile.viewModel.MovieState
import com.ic.cinefile.viewModel.userCreateViewModel


@Composable
fun descripcionPeliAdmin(
    onClick: () -> Unit,
    viewModel: userCreateViewModel,
    navController: NavController,
    movieId: Int,
//    id: String,
//    imagePainter: Painter,
//    description: String
) {


    var isBookmarked by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val addScreenState = viewModel.uiState.collectAsState()

    val movieState by viewModel.movieState.collectAsState()


    val averageRating by viewModel.averageRatingState.collectAsState()


    LaunchedEffect(movieId) {
        viewModel.getMovieById(movieId)
        viewModel.getAverageRating(movieId)
        viewModel.getRatingForUser(movieId)

    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        when (movieState) {
            is MovieState.Loading -> {
                // Mostrar un indicador de carga mientras se obtienen los datos
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                )
            }

            is MovieState.Error -> {
                // Mostrar un mensaje de error en caso de fallo
                val message = (movieState as MovieState.Error).msg
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }

            is MovieState.Success -> {
                // Mostrar la información de la película una vez cargada
                val movie = (movieState as MovieState.Success).data

                //Modal de eliminar:
                val openAlertDialog = remember { mutableStateOf(false) }
                if (openAlertDialog.value) {
                    DeleteDialogAdmin(
                        //lo que hace si se da en "eliminar", logica de eliminar la peli
                        onConfirmation = { openAlertDialog.value = false },
                        //lo que hace si se le da "atrás"
                        onDismissRequest = { openAlertDialog.value = false },
                        dialogText = "Pelicula a eliminar: \n${movie.title}"
                    )
                }

                //Imagen de fondo
                AsyncImage(
                    model = movie.posterUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight(0.7f)
                        .fillMaxWidth()
                )

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

                        //Titulo y eliminar
                        item {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = movie.title ?:"s/n", // Mostrar el nombre de la película seleccionada
                                    fontSize = 28.sp,
                                    color = Color.White,
                                    modifier = Modifier.fillMaxWidth(0.85f)
                                )

                                IconButton(
                                    onClick = { openAlertDialog.value = true },
                                    modifier = Modifier.align(Alignment.Top)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null,
                                        tint = dark_red
                                    )
                                }
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
                                    text = formatDuration(movie.duration),
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
                                    text = when (val state = averageRating) {
                                        is AverageRatingState.Success -> {
                                            state.averageRating?.takeIf { it != 0.0 }
                                                ?.let { String.format("%.2f", it) } ?: "0.0"
                                        }

                                        else -> "0.0"
                                    },
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
                                    text = movie.releaseDate,
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
                                // Separar las categorías y mostrarlas individualmente
                                movie.genres.split(",").forEach { category ->
                                    Card(
                                        modifier = Modifier
                                            .wrapContentSize(),
                                        colors = CardDefaults.cardColors(containerColor = sky_blue)
                                    ) {
                                        Text(
                                            text = category.trim(), // Eliminar espacios en blanco alrededor
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
                                text = movie.description,
                                lineHeight = 20.sp,
                                color = Color.White
                            )
                        }

                        //TRAILER
                        item {
                            Spacer(modifier = Modifier.height(15.dp))
                            verTrailer(onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.trailerUrl))
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                context.startActivity(intent)
                            })
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
                            ratingStars(
                                rating = 1,
                                onRatingChanged = { rating ->
                                    viewModel.rateMovie(movieId, rating.toDouble())
                                }
                            )
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
                                items(movie.actors.size) { index ->
                                    val actor = movie.actors[index]
                                    Column(
                                        modifier = Modifier
                                            .wrapContentSize(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {

                                        if (!actor.profileUrl.isNullOrEmpty()) {
                                            AsyncImage(
                                                model = actor.profileUrl,
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .size(60.dp)
                                                    .clip(CircleShape)
                                            )
                                        } else {
                                            // Mostrar la imagen de reemplazo si no hay URL de imagen
                                            val placeholderActorImage =
                                                painterResource(id = R.drawable.sin_foto)
                                            Image(
                                                painter = placeholderActorImage,
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(60.dp)
                                                    .clip(CircleShape)
                                            )
                                        }

                                        Text(
                                            text = actor.name,
                                            color = Color.White,
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
                            comentariosAdmin(
                                viewModel = viewModel,
                                movieId = movieId
                            )
                        }
                    }
                }

            }

            else -> {}
        }
    }
}



@Composable
fun formatDuration(durationInMinutes: Int): String {
    val hours = durationInMinutes / 60
    val minutes = durationInMinutes % 60
    return "${hours}h ${minutes}min"
}




