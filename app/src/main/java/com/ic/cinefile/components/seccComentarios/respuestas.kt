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

@Composable
fun respuestas(idComentario: String?, imagePainter: Painter?, description: String?) {
    //val comment = "Comentario $idComentario"

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
            if (imagePainter != null) {
                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
            }
            if (description != null) {
                Column {
                    /*Text(
                        text = username, // Mostrar el nombre de usuario
                        fontWeight = FontWeight.Bold,
                        color = white,
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )*/
                    Text(
                        text = description,
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )
                    /*Text(
                        text = "Publicado el $formattedDateTime", // Mostrar fecha y hora de publicación
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = white
                        ),
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )*/
                }
            }

        }
    }
}