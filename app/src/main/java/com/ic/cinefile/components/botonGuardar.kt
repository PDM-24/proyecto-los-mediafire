package com.ic.cinefile.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ic.cinefile.R
import com.ic.cinefile.ui.theme.light_yellow

@Composable
fun botonGuardar(
    onClick: () -> Unit,
    isBookmarked: Boolean
) {
    IconButton(
        onClick = onClick,
    ) {
        Box() {
            if (isBookmarked)
                Icon(
                    painterResource(id = R.drawable.bookmark_24),
                    contentDescription = "Saved",
                    tint = light_yellow
                )
            else {
                Icon(
                    painterResource(id = R.drawable.bookmark_border_24),
                    contentDescription = "Save",
                    tint = light_yellow
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun guardarPreview() {
    var isBookmarked by remember { mutableStateOf(false) }
    botonGuardar(
        onClick = { isBookmarked = !isBookmarked },
        isBookmarked = isBookmarked
    )
}