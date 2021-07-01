package com.example.trabajopracticofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trabajopracticofinal.models.Usuario

class MainActivity : AppCompatActivity() {
    lateinit var usuarioLogueado: Usuario
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usuarioLogueado = Usuario("","",0,"","","","","")
        var usuarioLogueado = getIntent().getStringExtra("USUARIO")

        irALogin()
    }



    private fun irALogin() {

        if(usuarioLogueado.usuario.equals("")){


            var intent:Intent = Intent(this, LoginActivity::class.java)

            startActivity(intent);
        }
    }
}