package com.ic.cinefile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ic.cinefile.R



@Composable
fun contentAvatar(navController: NavHostController) {

    val Avatarimg = listOf("avatar1","avatar2","avatar3","avatar3","avatar4","avatar5","avatar6")

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Elige tu avatar",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(120.dp))

        Row (verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(id = R.drawable.avatar1) ,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(110.dp))

            Image(painter = painterResource(id = R.drawable.avatar2) ,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(110.dp))

            Image(painter = painterResource(id = R.drawable.avatar3) ,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(110.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row (verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(id = R.drawable.avatar4) ,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(110.dp))

            Image(painter = painterResource(id = R.drawable.avatar5) ,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(110.dp))

            Image(painter = painterResource(id = R.drawable.avatar6) ,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(110.dp))
        }

        Spacer(modifier = Modifier.height(120.dp))

        Button(
            onClick = {},
            modifier = Modifier
                .width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
        ) {
            Text(
                text = "Finalizar",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                )
            )
        }

    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewSeleccionAvatarScreen() {
    val navController = rememberNavController()
    contentAvatar(navController)
}