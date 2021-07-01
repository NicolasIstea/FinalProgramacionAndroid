package com.example.trabajopracticofinal

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajopracticofinal.adapter.ComidasAdapter
import com.example.trabajopracticofinal.dao.DBHelper
import com.example.trabajopracticofinal.models.Comida

class InformeActivity : AppCompatActivity() {

    lateinit var volverLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informe)

        volverLogin = findViewById(R.id.i_volverLogin)

        volverLogin.setOnClickListener(){
            val intent: Intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
        }


        val comidas:ArrayList<Comida> = traerComidas()
        setearRecycleViewComidas(comidas)
    }

    private fun traerComidas(): ArrayList<Comida> {
        val db = DBHelper(this,null)

        return db.traerTodasLasComidas()
    }

    @SuppressLint("WrongConstant")
    private fun setearRecycleViewComidas(comidas:ArrayList<Comida>) {

        val recycleView: RecyclerView = findViewById(R.id.i_rw_comidas)
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false)

        val adapterComidas = ComidasAdapter(comidas)

        recycleView.adapter=adapterComidas
    }

}