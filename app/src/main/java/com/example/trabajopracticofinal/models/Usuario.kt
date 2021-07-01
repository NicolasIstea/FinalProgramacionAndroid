package com.example.trabajopracticofinal.models

import java.io.Serializable

data class Usuario(
    var nombre:String, var apellido:String, var dni:Int,
    var genero:String, var localidad:String, var usuario:String,
    var contraseña:String, var tipoTratamiento:String): Serializable {
}