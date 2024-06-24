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
    object Calificadas:screenRoute("Calificadas")
<<<<<<< HEAD
    object Lista_deseos:screenRoute("Lista_deseos")



    object HomeAdmin:screenRoute("HomeAdmin")

    object descripcionPeliAdmin:screenRoute("descripcionPeliAdmin")

    object AgregarPeliAdmin:screenRoute("AgregarPeliAdmin")
=======
    object ListaDeseos:screenRoute("ListaDeseos")
    object Configuraciones:screenRoute("Configuraciones")
>>>>>>> 05660a4a2c49a792e3fc43333bede78250a4a628

}