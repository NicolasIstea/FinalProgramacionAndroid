package com.example.trabajopracticofinal.models

import java.util.*

data class Comida(
        var tipoComida: String,
        var comidaPrincipal:String,
        var comidaSecundaria:String,
        var bebida:String,
        var postre:String = "",
        var comidaTentacion:String = "",
        var hambre:String = "No",
        var fechaLlenado: String
) { }