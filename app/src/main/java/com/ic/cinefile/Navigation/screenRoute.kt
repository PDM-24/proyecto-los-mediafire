package com.ic.cinefile.Navigation

sealed class screenRoute(var route: String) {
    object CrearCuenta: screenRoute("CrearCuenta")
    object CrearPerfil: screenRoute("CrearPerfil")
    object Genero: screenRoute("Genero")

    object ElegirGeneros:screenRoute("ElegirGeneros")
    object contentAvatar:screenRoute("contentAvatar")

    object HomeAppScreen:screenRoute("HomeAppScreen")
    object Login:screenRoute("Login")

    object Home:screenRoute("Home")

    object Buscador:screenRoute("Buscador")

    object descripcionPeli:screenRoute("descripcionPeli")
    object Notificaciones:screenRoute("Notificaciones")
    object PerfilAnuncios:screenRoute("PerfilAnuncios")

    object unComentario:screenRoute("unComentario")

    object ResultadoBuscador:screenRoute("ResultadoBuscador")

}