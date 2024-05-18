package com.ic.cinefile.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ic.cinefile.data.accountCreateData
import com.ic.cinefile.screens.CrearCuenta
import com.ic.cinefile.screens.CrearPerfil
import com.ic.cinefile.screens.ElegirGeneros
import com.ic.cinefile.screens.HomeAppScreen
import com.ic.cinefile.screens.RestablecerContra
import com.ic.cinefile.screens.Login
import com.ic.cinefile.screens.contentAvatar
import com.ic.cinefile.screens.contentGenero
import com.ic.cinefile.viewModel.userCreateViewModel


//se usa en esta funcion de Appscreen
sealed class AppScreens(val route: String) {
    @Composable
    abstract fun content(navController: NavController)

    object HomeApp : AppScreens("HomeApp") {
        @Composable
        override fun content(navController: NavController) {
            HomeAppScreen(navController)
        }
    }

    object LoginCuenta : AppScreens("LoginCuenta") {
        @Composable
        override fun content(navController: NavController) {
            Login(navController)
        }
    }

    object CrearCuenta : AppScreens("CrearCuenta") {
        @Composable
        override fun content(navController: NavController) {
            val viewModel: userCreateViewModel= viewModel()

            CrearCuenta(navController)
        }
    }

    object CrearPerfil : AppScreens("CrearPerfil") {
        @Composable
        override fun content(navController: NavController) {

            CrearPerfil(navController)
        }
    }

    object ElegirGeneros : AppScreens("ElegirGeneros") {
        @Composable
        override fun content(navController: NavController) {
            val viewModel: userCreateViewModel= viewModel()

            ElegirGeneros(navController,viewModel)
        }
    }
    object Genero : AppScreens("Genero") {
        @Composable
        override fun content(navController: NavController) {

            contentGenero(navController)
        }
    }
    object Avatar : AppScreens("Avatar") {
        @Composable
        override fun content(navController: NavController) {
            val viewModel: userCreateViewModel= viewModel()

            contentAvatar(navController,viewModel)
        }
    }
    object RestablecerContra : AppScreens("RestablecerContra") {
        @Composable
        override fun content(navController: NavController) {
            RestablecerContra(navController)
        }
    }
}

