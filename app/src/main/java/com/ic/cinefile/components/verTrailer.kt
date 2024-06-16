package com.ic.cinefile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ic.cinefile.R
import com.ic.cinefile.ui.theme.light_red
import com.ic.cinefile.ui.theme.white

@Composable
fun verTrailer(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(light_red),

        ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(id = R.drawable.baseline_play_arrow_24),
                contentDescription = "Play",
                tint = white
            )
            Text(
                text = "Trailer",
                modifier = Modifier
                    .padding(start = 6.dp),
                textAlign = TextAlign.Center

            )
        }
    }
}

@Preview
@Composable
fun verTrailerPreview(){
    var isBookmarked by remember { mutableStateOf(false) }
    verTrailer(onClick = {isBookmarked = !isBookmarked })
}