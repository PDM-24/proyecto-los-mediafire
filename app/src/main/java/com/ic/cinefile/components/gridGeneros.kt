package com.ic.cinefile.components

import androidx.compose.runtime.Composable
import com.ic.cinefile.components.valoresGeneros.generos
import com.ic.cinefile.ui.theme.dark_blue
import com.ic.cinefile.ui.theme.dark_red
import com.ic.cinefile.ui.theme.dark_yellow
import com.ic.cinefile.ui.theme.light_red
import com.ic.cinefile.ui.theme.light_yellow
import com.ic.cinefile.ui.theme.sky_blue

@Composable
fun gridGeneros(generos: generos): Pair<androidx.compose.ui.graphics.Color, androidx.compose.ui.graphics.Color> {
    val colores = listOf(
        Pair(light_red, dark_red),
        Pair(sky_blue, dark_blue),
        Pair(light_yellow, dark_yellow)
    )
    val index = generos.ordinal % colores.size
    return colores[index]
}