package com.example.trabajopracticofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible
import com.example.trabajopracticofinal.dao.DBHelper
import com.example.trabajopracticofinal.models.Comida
import com.example.trabajopracticofinal.models.Usuario
import java.text.SimpleDateFormat
import java.util.*

class FormularioSaludableActivity : AppCompatActivity() {
    lateinit var usuario: Usuario
    lateinit var comida: Comida

    lateinit var rg_comidas : RadioGroup
    lateinit var rb_comida_desayuno : RadioButton
    lateinit var rb_comida_almuerzo : RadioButton
    lateinit var rb_comida_merienda : RadioButton
    lateinit var rb_comida_cena : RadioButton
    lateinit var comida_seleccionada : RadioButton
    lateinit var comida_principal : EditText
    lateinit var comida_secundaria : EditText
    lateinit var comida_bebida : EditText
    lateinit var rg_postre : RadioGroup
    lateinit var rb_isPostreSi : RadioButton
    lateinit var rb_isPostreNo : RadioButton
    lateinit var comida_postre : EditText
    lateinit var rg_isTentacion : RadioGroup
    lateinit var rb_isTentacionSi : RadioButton
    lateinit var rb_isTentacionNo : RadioButton
    lateinit var comida_tentacion : EditText
    lateinit var rg_isHambre : RadioGroup
    lateinit var rb_isHambreSi : RadioButton
    lateinit var rb_isHambreNo : RadioButton
    lateinit var comida_guardar : Button
    lateinit var volver_login : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_saludable)

        inicializar()

        rb_isPostreSi.setOnClickListener() {
            comida_postre.isVisible = true
        }

        rb_isPostreNo.setOnClickListener() {
            comida_postre.isVisible = false;
            comida_postre.setText("")
        }

        rb_isTentacionSi.setOnClickListener() {
            comida_tentacion.isVisible = true
        }

        rb_isTentacionNo.setOnClickListener() {
            comida_tentacion.isVisible = false
            comida_tentacion.setText("")
        }

        comida_guardar.setOnClickListener() {

            var isValid: Boolean = validarFormulario()

            if(!isValid) {
                return@setOnClickListener
            }


            llenarComida()

            var dbHelper: DBHelper = DBHelper(this,null)
            dbHelper.guardarComida(comida)

            Toast.makeText(this,"Comida se guardo con exito",Toast.LENGTH_LONG).show()

            borrarFormulario()

        }

        volver_login.setOnClickListener() {
            var intent:Intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }



    }

    private fun borrarFormulario() {
        comida = Comida("","","","","","","No","")
        var comidas: RadioGroup = findViewById(R.id.f_rg_comidas)
        comidas.clearCheck()

        var comida_principal:EditText = findViewById(R.id.f_comida_principal)
        comida_principal.clear()

        var comida_secundaria:EditText = findViewById(R.id.f_comida_secundaria)
        comida_secundaria.clear()

        var comida_bebida:EditText = findViewById(R.id.f_comida_bebida)
        comida_bebida.clear()

        var rg_postre:RadioGroup = findViewById(R.id.f_comida_rg_isPostre)
        rg_postre.clearCheck()

        var comida_postre:EditText = findViewById(R.id.f_comida_postre)
        comida_postre.clear()

        var rg_isTentacion:RadioGroup = findViewById(R.id.f_comida_rg_isTentacion)
        rg_isTentacion.clearCheck()

        var comida_tentacion:EditText = findViewById(R.id.f_comida_tentacion)
        comida_tentacion.clear()

        var rg_isHambre:RadioGroup = findViewById(R.id.f_comida_rg_isHambre)
        rg_isHambre.clearCheck()

    }

    private fun EditText.clear() {
        text.clear()
    }

    private fun llenarComida() {
        comida_seleccionada = findViewById(rg_comidas.checkedRadioButtonId)

        comida.tipoComida = comida_seleccionada.text.toString()
        comida.comidaPrincipal = comida_principal.getText().toString()
        comida.comidaSecundaria = comida_secundaria.getText().toString()
        comida.bebida = comida_bebida.getText().toString()

        if(rb_isPostreSi.isChecked) {
            comida.postre = comida_postre.getText().toString()
        }

        if(rb_isTentacionSi.isChecked) {
            comida.comidaTentacion = comida_tentacion.getText().toString()
        }

        if(rb_isHambreSi.isChecked) {
            comida.hambre = "Si"
        }

        comida.fechaLlenado = getCurrentDateTime().toString("dd/MM/yyyy HH:mm:ss")

    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    private fun validarFormulario(): Boolean {

        if(rg_comidas.checkedRadioButtonId == -1) {
            Toast.makeText(this,"Debe seleccionar un tipo de comida", Toast.LENGTH_LONG).show()
            return false
        }

        if(comida_principal.getText().toString().trim().equals("")){
            Toast.makeText(this,"Debe escribir su comida principal", Toast.LENGTH_LONG).show()
            return false;
        }

        if(comida_secundaria.getText().toString().trim().equals("")) {
            Toast.makeText(this,"Debe escribir su comida secundaria", Toast.LENGTH_LONG).show()
            return false;
        }

        if(comida_bebida.getText().toString().trim().equals("")) {
            Toast.makeText(this,"Debe escribir la bebida", Toast.LENGTH_LONG).show()
            return false;
        }

        if(rg_postre.checkedRadioButtonId == -1) {
            Toast.makeText(this,"Debe seleccionar si comio postre o no", Toast.LENGTH_LONG).show()
            return false;
        }

        if(rb_isPostreSi.isChecked && comida_postre.getText().toString().trim().equals("")) {
            Toast.makeText(this,"Debe escribir que postre comió", Toast.LENGTH_LONG).show()
            return false;
        }

        if(rg_isTentacion.checkedRadioButtonId == -1) {
            Toast.makeText(this,"Debe seleccionar si ha tenido tentacion de comer mas o no", Toast.LENGTH_LONG).show()
            return false;
        }

        if(rb_isTentacionSi.isChecked && comida_tentacion.getText().toString().trim().equals("")) {
            Toast.makeText(this,"Debe seleccionar si que comida tentacio comió", Toast.LENGTH_LONG).show()
            return false;
        }

        if(rg_isHambre.checkedRadioButtonId == -1) {
            Toast.makeText(this,"Debe seleccionar si ha tenido hambre o no", Toast.LENGTH_LONG).show()
            return false;
        }

        return true;
    }


    private fun inicializar() {
        usuario = intent.extras?.get("USUARIO") as Usuario
        comida = Comida("","","","","","","No","")
        rg_comidas = findViewById(R.id.f_rg_comidas)
        rb_comida_desayuno = findViewById(R.id.f_rb_comida_desayuno)
        rb_comida_almuerzo = findViewById(R.id.f_rb_comida_almuerzo)
        rb_comida_merienda = findViewById(R.id.f_rb_comida_merienda)
        rb_comida_cena = findViewById(R.id.f_rb_comida_cena)
        comida_principal = findViewById(R.id.f_comida_principal)
        comida_secundaria = findViewById(R.id.f_comida_secundaria)
        comida_bebida = findViewById(R.id.f_comida_bebida)
        rg_postre = findViewById(R.id.f_comida_rg_isPostre)
        rb_isPostreSi = findViewById(R.id.f_comida_rb_isPostreSi)
        rb_isPostreNo = findViewById(R.id.f_comida_rb_isPostreNo)
        comida_postre = findViewById(R.id.f_comida_postre)
        rg_isTentacion = findViewById(R.id.f_comida_rg_isTentacion)
        rb_isTentacionSi = findViewById(R.id.f_comida_rb_isTentacionSi)
        rb_isTentacionNo = findViewById(R.id.f_comida_rb_isTentacionNo)
        comida_tentacion = findViewById(R.id.f_comida_tentacion)
        rg_isHambre = findViewById(R.id.f_comida_rg_isHambre)
        rb_isHambreSi = findViewById(R.id.f_comida_rg_isHambreSi)
        rb_isHambreNo = findViewById(R.id.f_comida_rg_isHambreNo)
        comida_guardar = findViewById(R.id.f_b_comida_guardar)
        volver_login = findViewById(R.id.f_b_volverLogin)
    }


}