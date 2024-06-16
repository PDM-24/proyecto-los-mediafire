package com.ic.cinefile.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ic.cinefile.screens.CrearCuenta
import com.ic.cinefile.screens.CrearPerfil
import com.ic.cinefile.screens.ElegirGeneros
import com.ic.cinefile.screens.Home
import com.ic.cinefile.screens.HomeAppScreen
import com.ic.cinefile.screens.Login
import com.ic.cinefile.screens.contentAvatar
import com.ic.cinefile.screens.contentGenero
import com.ic.cinefile.viewModel.userCreateViewModel

@Composable
fun AppNavigation(
    viewModel : userCreateViewModel,
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = screenRoute.HomeAppScreen.route
    ){
        composable(route = screenRoute.CrearCuenta.route){
            CrearCuenta(viewModel, navController)
        }
        composable(route = screenRoute.CrearPerfil.route){
            CrearPerfil(viewModel, navController)
        }
        composable(route=screenRoute.Genero.route){
            contentGenero(viewModel,navController)
        }
        composable(route=screenRoute.Genero.route){
            contentGenero(viewModel,navController)
        }
        composable(route=screenRoute.ElegirGeneros.route){
            ElegirGeneros(viewModel,navController)
        }
        composable(route=screenRoute.contentAvatar.route){
            contentAvatar(viewModel,navController)
        }
        composable(route=screenRoute.HomeAppScreen.route){
            HomeAppScreen(navController)
        }
        composable(route=screenRoute.Login.route){
            Login(viewModel,navController)
        }
        composable(route=screenRoute.Home.route){
            Home(viewModel,navController)
        }
//        composable(
//            route = "${screenRoute.Edit.route}/{code}",
//            arguments = listOf(
//                navArgument("code"){
//                    type = NavType.StringType
//                }
//            )
//        ){ backStackEntry ->
//            EditScreen(viewModel, navController, backStackEntry.arguments?.getString("code"))
//        }
    }
}