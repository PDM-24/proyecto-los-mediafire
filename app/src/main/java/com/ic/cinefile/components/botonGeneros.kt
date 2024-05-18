package com.ic.cinefile.components

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.ic.cinefile.components.valoresGeneros.generos
import androidx.compose.material3.Button as Button

@Composable
fun botonGeneros(
    generos: generos,
    selectedColor: Color,
    defaultColor: Color,
    onClick: (String,Boolean) -> Unit
) {
    var isSelected by remember { mutableStateOf(false) }

    val currentColor = when {
        isSelected -> selectedColor
        else -> defaultColor
    }

    Button(
        onClick = {
            isSelected = !isSelected
            onClick(generos.name,isSelected)
        },
        colors = ButtonDefaults.buttonColors(containerColor = currentColor)
    ) {
        Text(generos.name)
    }
}
