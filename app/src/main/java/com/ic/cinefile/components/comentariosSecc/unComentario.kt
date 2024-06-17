package com.ic.cinefile.components.comentariosSecc

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ic.cinefile.R
import com.ic.cinefile.ui.theme.grisComment
import com.ic.cinefile.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun unComentario(
    id: String,
    description: String,
    imagePainter: Painter,
) {
    var showResponses by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState()
    var respuesta by remember { mutableStateOf("") }
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
            Text(
                text = description,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

        IconButton(
            onClick = { showResponses = !showResponses /*agregar una respuesta al comentario*/ },
            modifier = Modifier.padding(start = 35.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_comment_24),
                contentDescription = "",
                tint = white,
                modifier = Modifier.size(18.dp)

            )
        }
        if (showResponses) {
            ModalBottomSheet(
                onDismissRequest = { showResponses = false },
                sheetState = bottomSheetState,
                containerColor = Color.Gray,
                modifier = Modifier.fillMaxHeight(0.5f)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Respuestas",
                        fontSize = 18.sp,
                        color = white
                    )
                }
                respuestas(
                    idComentario = id,
                    imagePainter = imagePainter,
                    description = description
                )
                LazyColumn(
                    modifier = Modifier
                        .padding(16.dp)
                ) {

                    //lista de respuestas del comentario
                    items(3) { comentarios ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(start = 20.dp),
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
                                Text(
                                    text = description,
                                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
                                )
                            }
                        }
                    }

                    //Agregar una respuesta
                    item {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Divider(Modifier.padding(bottom = 6.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                //Avatar del usuario
                                Image(
                                    painter = painterResource(id = R.drawable.reynolds),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                //Campo para escribir el comentario
                                TextField(
                                    modifier = Modifier.fillMaxWidth(0.9f),
                                    value = respuesta,
                                    onValueChange = { respuesta = it },
                                    colors = TextFieldDefaults.colors(
                                        unfocusedContainerColor = grisComment,
                                        focusedContainerColor = grisComment,
                                        unfocusedLabelColor = white,
                                        focusedLabelColor = white,
                                        focusedIndicatorColor = white,
                                        cursorColor = white,
                                        focusedTextColor = white
                                    ),
                                    placeholder = {
                                        Text(
                                            text = "Responder comentario...",
                                            style = TextStyle(
                                                color = white,
                                                fontWeight = FontWeight.Normal,
                                            )
                                        )
                                    }
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                IconButton(onClick = { /*Para subir el comentario*/ }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_send_24),
                                        contentDescription = null,
                                        tint = white
                                    )
                                }

                            }
                        }
                    }
                }
            }

        }
    }
}