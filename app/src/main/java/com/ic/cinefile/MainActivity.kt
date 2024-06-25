package com.ic.cinefile


import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ic.cinefile.Navigation.AppNavigation
//import androidx.navigation.compose.rememberNavController
import com.ic.cinefile.screens.HomeAppScreen
import com.ic.cinefile.ui.theme.CineFileTheme
import com.ic.cinefile.viewModel.userCreateViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : userCreateViewModel by viewModels()

        setContent {
           AppNavigation(viewModel)
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        }
    }



//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    //val navController = rememberNavController()
//    CineFileTheme {
//        HomeAppScreen()
//    }
//}