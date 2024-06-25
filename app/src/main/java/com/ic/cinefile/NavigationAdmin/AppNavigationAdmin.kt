//package com.ic.cinefile.NavigationAdmin
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.ic.cinefile.Navigation.screenRoute
//import com.ic.cinefile.screens.Administrador.HomeAdmin
//import com.ic.cinefile.screens.Login
//import com.ic.cinefile.viewModel.userCreateViewModel
//
//@Composable
//fun AppNavigationAdmin(
//    viewModel : userCreateViewModel,
//){
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = screenRouteAdmin.Login.route
//    ){
//
//        composable(route=screenRouteAdmin.Login.route) {
//            Login(viewModel, navController)
//        }
//
//        composable(route= screenRouteAdmin.HomeAdmin.route){
//            HomeAdmin(viewModel,navController)
//        }
////        composable(route= screenRoute.Buscador.route){
////            Buscador(viewModel,navController)
////        }
////        composable(
////            route = "${screenRoute.descripcionPeli.route}/{movieId}",
////            arguments = listOf(navArgument("movieId"){
////                type= NavType.IntType
////            })
////
////        ) { backStackEntry ->
////            val movieId = backStackEntry.arguments?.getInt("movieId") ?: throw IllegalArgumentException("Movie ID missing")
////
////            descripcionPeli(
////                onClick = {}, viewModel,navController, movieId)
////        }
////        composable(route= screenRoute.PerfilAnuncios.route){
////            PerfilAnuncios(viewModel,navController)
////        }
////        composable(route = screenRoute.Notificaciones.route){
////            Notificaciones(
////                viewModel = viewModel,
////                navController = navController,
////                onNotificationClick = { notification ->
////                    // Navegar a la pantalla del comentario
////                    navController.navigate("comentarioScreen/${notification.id}")
////                }
////            )
////
////        }
////        composable(
////            route = "${screenRoute.ResultadoBuscador.route}/{title}",
////            arguments = listOf(navArgument("title") {
////                type = NavType.StringType
////            })
////        ) { backStackEntry ->
////            val title = backStackEntry.arguments?.getString("title") ?: ""
////
////            Resultadobuscador(viewModel, navController, title)
////        }
////
//////        composable(
//////            route = screenRoute.ResultadoBuscador.route + "/{title}",
//////            arguments = listOf(
//////                navArgument("title") { type = NavType.StringType }
//////            )
//////        ) { backStackEntry ->
//////            val title = backStackEntry.arguments?.getString("title") ?: throw IllegalArgumentException("Title missing")
//////
//////            Resultadobuscador(
//////                viewModel ,
//////                navController,
//////                title
//////            )
//////        }
////
////        composable(route= screenRoute.Calificadas.route){
////            Calificadas()
////        }
////        composable(route= screenRoute.ListaDeseos.route){
////            Lista_deseos()
////        }
//
//    }
//
//
//
//
//
//
//}