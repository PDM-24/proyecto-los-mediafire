package com.ic.cinefile.components.seccComentarios

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ic.cinefile.ui.theme.white
import com.ic.cinefile.viewModel.userCreateViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun respuestas2(
    movieId: Int,
    id: String,
    parentId:String?,
    username: String,
    description: String,
    imagePainter: Painter,
    createdAt: String, // Fecha y hora de creación del comentario,
    viewModel: userCreateViewModel,


) {
    //val comment = "Comentario $idComentario"
    // Formato de entrada para parsear la fecha y hora
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

    // Formato de salida para mostrar la fecha y hora en un formato legible
    val outputFormat = SimpleDateFormat("dd 'de' MMMM 'de' yyyy hh:mm a", Locale.getDefault())

    // Parsear la fecha y hora del comentario
    val parsedDate = inputFormat.parse(createdAt)
    val formattedDateTime = outputFormat.format(parsedDate)

    //El comentario que se responde
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )

            Column {
                Text(
                    text = username, // Mostrar el nombre de usuario
                    fontWeight = FontWeight.Bold,
                    color = white,
                    modifier = Modifier
                        .padding(start = 12.dp)
                )
                Text(
                    text = description,
                    modifier = Modifier
                        .padding(start = 12.dp)
                )
                Text(
                    text = "Publicado el $formattedDateTime", // Mostrar fecha y hora de publicación
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = white
                    ),
                    modifier = Modifier
                        .padding(start = 12.dp)
                )
            }

        }
    }
}