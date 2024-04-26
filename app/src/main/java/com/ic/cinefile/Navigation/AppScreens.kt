package com.ic.cinefile.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ic.cinefile.screens.CrearCuenta
import com.ic.cinefile.screens.HomeAppScreen

sealed class AppScreens(val route: String) {
    @Composable
    abstract fun content(navController: NavController)

    object HomeApp : AppScreens("HomeApp") {
        @Composable
        override fun content(navController: NavController) {
            HomeAppScreen(navController)
        }
    }

    object CrearCuenta : AppScreens("CrearCuenta") {
        @Composable
        override fun content(navController: NavController) {
            CrearCuenta(navController)
        }
    }
}
