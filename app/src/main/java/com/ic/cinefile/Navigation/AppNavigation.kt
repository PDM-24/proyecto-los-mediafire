package com.ic.cinefile.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
//import com.ic.cinefile.screens.Administrador.HomeAdmin
import coil.compose.rememberImagePainter
import com.ic.cinefile.components.seccComentarios.unComentario
import com.ic.cinefile.screens.Administrador.AgregarPeliAdmin
//import com.ic.cinefile.screens.Administrador.AgregarPeliAdmin
import com.ic.cinefile.screens.Administrador.descripcionPeliAdmin
import com.ic.cinefile.screens.Buscador
import com.ic.cinefile.screens.Calificadas
import com.ic.cinefile.screens.Configuraciones
import com.ic.cinefile.screens.CrearCuenta
import com.ic.cinefile.screens.CrearPerfil
import com.ic.cinefile.screens.ElegirGeneros
import com.ic.cinefile.screens.HomeAdmin
//import com.ic.cinefile.screens.Home
import com.ic.cinefile.screens.HomeAppScreen
import com.ic.cinefile.screens.Lista_deseos
import com.ic.cinefile.screens.Login
import com.ic.cinefile.screens.Notificaciones
import com.ic.cinefile.screens.PerfilAnuncios
import com.ic.cinefile.screens.Resultadobuscador
import com.ic.cinefile.screens.contentAvatar
import com.ic.cinefile.screens.contentGenero
import com.ic.cinefile.screens.descripcionPeli
import com.ic.cinefile.viewModel.userCreateViewModel

@Composable
fun AppNavigation(
    viewModel : userCreateViewModel,
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = screenRoute.HomeAppScreen.route
    )
    {
        composable(route = screenRoute.CrearCuenta.route){
            CrearCuenta(viewModel, navController)
        }
        composable(route = screenRoute.CrearPerfil.route){
            CrearPerfil(viewModel, navController)
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

        /*composable(route=screenRoute.Home.route){
            Home(viewModel,navController)
        }*/

        composable(route=screenRoute.Buscador.route){
            Buscador(viewModel,navController)
        }
        composable(
            route = "${screenRoute.descripcionPeli.route}/{movieId}",
                arguments = listOf(navArgument("movieId"){
                    type= NavType.IntType
                })

        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: throw IllegalArgumentException("Movie ID missing")

            descripcionPeli(
               onClick = {}, viewModel,navController, movieId)
        }
        composable(route=screenRoute.PerfilAnuncios.route){
            PerfilAnuncios(viewModel,navController)
        }
        composable(route = screenRoute.Notificaciones.route){
            Notificaciones(
                viewModel = viewModel,
                navController = navController,


            )

        }
        composable(
            route = "${screenRoute.ResultadoBuscador.route}/{title}",
            arguments = listOf(navArgument("title") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""

            Resultadobuscador(viewModel, navController, title)
        }


//        composable(
//            route = screenRoute.ResultadoBuscador.route + "/{title}",
//            arguments = listOf(
//                navArgument("title") { type = NavType.StringType }
//            )
//        ) { backStackEntry ->
//            val title = backStackEntry.arguments?.getString("title") ?: throw IllegalArgumentException("Title missing")
//
//            Resultadobuscador(
//                viewModel ,
//                navController,
//                title
//            )
//        }

        composable(route=screenRoute.Calificadas.route){
            Calificadas(viewModel,navController)
        }
        composable(route=screenRoute.Lista_deseos.route){
            Lista_deseos(viewModel,navController)
        }
        composable(route=screenRoute.Configuraciones.route){
            Configuraciones(navController)
        }


        composable(
            route = "${screenRoute.unComentario.route}/{parentId}",
            arguments = listOf(
                navArgument("parentId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val parentId = backStackEntry.arguments?.getString("parentId") ?: throw IllegalArgumentException("Parent ID missing")

            // Aquí debes obtener los otros parámetros necesarios para la pantalla unComentario
            // Puedes obtenerlos de tu ViewModel, base de datos, o de donde estés almacenando la información.
            val movieId = 1 // Ejemplo, debes obtener el movieId correspondiente al parentId
            val id = "123" // Ejemplo, debes obtener el id correspondiente al parentId
            val username = "John Doe" // Ejemplo, debes obtener el username correspondiente al parentId
            val description = "Descripción del comentario" // Ejemplo, debes obtener la descripción correspondiente al parentId
            val createdAt = "2024-06-24T10:00:00Z" // Ejemplo, debes obtener el createdAt correspondiente al parentId
            val imagePainter = rememberImagePainter(data = "URL de la imagen") // Ajusta según tu fuente de imagen

            unComentario(
                movieId = movieId,
                id = id,
                username = username,
                parentId = parentId,
                description = description,
                imagePainter = imagePainter,
                createdAt = createdAt,
                viewModel = viewModel
            )
        }



        //admin
        composable(route=screenRoute.HomeAdmin.route){
            HomeAdmin(viewModel,navController)
        }

        composable(
            route = "${screenRoute.descripcionPeliAdmin.route}/{movieId}",
            arguments = listOf(navArgument("movieId"){
                type= NavType.IntType
            })

        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: throw IllegalArgumentException("Movie ID missing")

            descripcionPeliAdmin(
                onClick = {}, viewModel,navController, movieId)
        }




        composable(route=screenRoute.AgregarPeliAdmin.route){
            AgregarPeliAdmin(viewModel,navController)
        }



    }






    }


