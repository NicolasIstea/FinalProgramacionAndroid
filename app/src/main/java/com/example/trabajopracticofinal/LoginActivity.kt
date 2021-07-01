package com.example.trabajopracticofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.trabajopracticofinal.dao.DBHelper
import java.text.SimpleDateFormat
import java.util.*

class LoginActivity : AppCompatActivity() {
    lateinit var usuario: EditText
    lateinit var contraseña: EditText
    lateinit var loguearse: Button
    lateinit var registrarse: Button
    lateinit var informe: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        inicializar()

        registrarse.setOnClickListener(){

            var intent:Intent = Intent(this,RegistrarActivity::class.java)

            startActivity(intent)
        }

        loguearse.setOnClickListener(){

            var dbHelper:DBHelper = DBHelper(this,null)
            var usuarioLog = dbHelper.usuarioExiste(usuario.text.toString(), contraseña.text.toString())

            if(usuarioLog.nombre.equals("")){
                Toast.makeText(this,"Usuario o contraseña incorrecta", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else {
                Toast.makeText(this,"Bienvenido " + usuarioLog.nombre, Toast.LENGTH_LONG).show()

                var intent:Intent = Intent(this,FormularioSaludableActivity::class.java)
                intent.putExtra("USUARIO", usuarioLog)
                startActivity(intent)
            }
        }

        informe.setOnClickListener() {

            var intent:Intent = Intent(this,InformeActivity::class.java)
            startActivity(intent)
        }
    }



    fun inicializar() {
        usuario = findViewById(R.id.l_usuario)
        contraseña = findViewById(R.id.l_contraseña)
        loguearse = findViewById(R.id.l_login)
        registrarse = findViewById(R.id.l_registrar)
        informe = findViewById(R.id.l_informar)
    }
}