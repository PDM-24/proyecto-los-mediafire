package com.ic.cinefile


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.compose.rememberNavController
import com.ic.cinefile.screens.HomeAppScreen
import com.ic.cinefile.ui.theme.CineFileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeAppScreen()
        }
        }
    }




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    //val navController = rememberNavController()
    CineFileTheme {
        HomeAppScreen()
    }
}