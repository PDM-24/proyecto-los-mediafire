package com.ic.cinefile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ic.cinefile.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Configuraciones(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                ),
                title = {
                    Text(
                        text = "Configuraciones",
                        color = Color.White,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(6.dp)
                            .clickable { navController.popBackStack() },
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
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
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_output_24),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }

                        Text(
                            text = "Cerrar sesion",
                            color = Color.White,
                            modifier = Modifier.padding(start = 16.dp)
                        )

                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Terminos()
        }
    }
}

@Composable
fun Terminos() {

    var expandedTerminos by remember { mutableStateOf(false) }
    var expandedPrivacidad by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Términos de uso
        Text(
            text = "Términos de uso",
            color = Color.White,
            fontSize = 18.sp,

            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable {
                    expandedTerminos = !expandedTerminos
                }
        )

        // Texto expandido de Términos de uso
        if (expandedTerminos) {
            Text(
                text = "Ejemplo de los términos de uso.",
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        // Políticas de privacidad
        Text(
            text = "Políticas de privacidad",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable {
                    expandedPrivacidad = !expandedPrivacidad // Cambiar el estado al hacer clic
                }
        )

        if (expandedPrivacidad) {
            Text(
                text = "Ejemplo de políticas de privacidad.",
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun ConfiguracionPreview() {
    Configuraciones(navController = rememberNavController())
}
