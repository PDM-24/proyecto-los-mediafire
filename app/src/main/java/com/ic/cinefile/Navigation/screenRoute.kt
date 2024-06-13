package com.ic.cinefile.Navigation

sealed class screenRoute(var route: String) {
    object CrearCuenta: screenRoute("CrearCuenta")
    object CrearPerfil: screenRoute("CrearPerfil")
    object Genero: screenRoute("Genero")

    object ElegirGeneros:screenRoute("ElegirGeneros")
    object contentAvatar:screenRoute("contentAvatar")

}