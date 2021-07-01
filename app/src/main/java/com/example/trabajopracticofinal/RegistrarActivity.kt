package com.example.trabajopracticofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.trabajopracticofinal.dao.DBHelper
import com.example.trabajopracticofinal.models.Usuario

class RegistrarActivity : AppCompatActivity() {
    lateinit var nombre:EditText
    lateinit var apellido:EditText
    lateinit var dni:EditText
    lateinit var rgroupsexo:RadioGroup
    lateinit var opcionFemenino:RadioButton
    lateinit var opcionMasculino:RadioButton
    lateinit var localidad:EditText
    lateinit var rgroupTratamiento:RadioGroup
    lateinit var opcionBulimia:RadioButton
    lateinit var opcionAnorexia:RadioButton
    lateinit var opcionObesidad:RadioButton
    lateinit var usuario:EditText
    lateinit var contraseña:EditText
    lateinit var guardar:Button
    lateinit var volverLogin:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        inicializar()

        guardar.setOnClickListener() {

            var isValid = validarUsuario()

            if(!isValid) {
                return@setOnClickListener
            }

            var usuario:Usuario = crearUsuario()


            val db = DBHelper(this,null)

            db.guardarUsuario(usuario)

            Toast.makeText(this, "Usuario creado exitosamente", Toast.LENGTH_LONG ).show()

            val intent: Intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USUARIO", usuario)

            startActivity(intent)

        }

        volverLogin.setOnClickListener() {

            val intent: Intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
        }
    }

    private  fun crearUsuario() : Usuario {
        opcionAnorexia=findViewById(rgroupTratamiento.checkedRadioButtonId)
        opcionBulimia=findViewById(rgroupTratamiento.checkedRadioButtonId)
        opcionObesidad=findViewById(rgroupTratamiento.checkedRadioButtonId)

        opcionFemenino=findViewById(rgroupsexo.checkedRadioButtonId)
        opcionMasculino=findViewById(rgroupsexo.checkedRadioButtonId)

        var tratamiento: String = ""
        var genero: String = ""

        if(opcionAnorexia.isEnabled()){
            tratamiento = opcionAnorexia.text.toString()
        }

        if(opcionBulimia.isEnabled()){
            tratamiento = opcionBulimia.text.toString()
        }

        if(opcionObesidad.isEnabled()){
            tratamiento = opcionObesidad.text.toString()
        }

        if(opcionFemenino.isEnabled()){
            genero = opcionFemenino.text.toString()
        }

        if(opcionMasculino.isEnabled()){
            genero = opcionMasculino.text.toString()
        }

        val usuarioACrear:Usuario = Usuario("NOUSER","",0,"","","","","")

        usuarioACrear.nombre = nombre.text.toString()
        usuarioACrear.apellido = apellido.text.toString()
        usuarioACrear.dni = dni.text.toString().toInt()
        usuarioACrear.tipoTratamiento = tratamiento
        usuarioACrear.genero = genero
        usuarioACrear.usuario = usuario.text.toString()
        usuarioACrear.contraseña = contraseña.text.toString()

        return usuarioACrear
    }


    private fun inicializar() {
        nombre = findViewById(R.id.r_Nombre)
        apellido = findViewById(R.id.r_apellido)
        dni = findViewById(R.id.r_dni)
        rgroupsexo = findViewById(R.id.r_rg_sexo)
        opcionFemenino = findViewById(R.id.r_rb_femenino)
        opcionMasculino = findViewById(R.id.r_rb_masculino)
        localidad = findViewById(R.id.r_localidad)
        rgroupTratamiento = findViewById(R.id.r_rg_tratamiento)
        opcionBulimia = findViewById(R.id.r_rb_bulimia)
        opcionAnorexia = findViewById(R.id.r_rb_anorexia)
        opcionObesidad = findViewById(R.id.r_rb_obesidad)
        usuario = findViewById(R.id.r_usuario)
        contraseña = findViewById(R.id.r_contraseña)
        guardar = findViewById(R.id.r_guardar)
        volverLogin = findViewById(R.id.r_volver)
    }

    private fun validarUsuario(): Boolean {

        if(nombre.equals("")){
            Toast.makeText(this,"Debe escribir el nombre", Toast.LENGTH_LONG).show()
            return false;
        }

        if(apellido.equals("")){
            Toast.makeText(this,"Debe escribir el apellido", Toast.LENGTH_LONG).show()
            return false;
        }

        if(dni.equals("")){
            Toast.makeText(this,"Debe escribir el dni", Toast.LENGTH_LONG).show()
            return false;
        }

        if(rgroupsexo.checkedRadioButtonId == -1){
            Toast.makeText(this,"Debe elegir su genero", Toast.LENGTH_LONG).show()
            return false;
        }

        if(localidad.equals("")){
            Toast.makeText(this,"Debe escribir la localidad", Toast.LENGTH_LONG).show()
            return false;
        }

        if(rgroupTratamiento.checkedRadioButtonId == -1){
            Toast.makeText(this,"Debe elegir el tratamiento por", Toast.LENGTH_LONG).show()
            return false;
        }

        if(usuario.equals("")){
            Toast.makeText(this,"Debe escribir el usuario", Toast.LENGTH_LONG).show()
            return false;
        }

        if(contraseña.equals("")){
            Toast.makeText(this,"Debe escribir la contraseña", Toast.LENGTH_LONG).show()
            return false;
        }

        return true;
    }
}