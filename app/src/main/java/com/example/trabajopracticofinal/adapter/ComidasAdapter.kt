package com.example.trabajopracticofinal.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajopracticofinal.models.Comida
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.trabajopracticofinal.R

class ComidasAdapter(val dataset: List<Comida>) : RecyclerView.Adapter<ComidasAdapter.ViewHolder>() {

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tipoComida: TextView
        val comidaPrincipal:TextView
        val comidaSecundaria:TextView
        val bebida:TextView
        val postre:TextView
        val comidaTentacion:TextView
        val hambre:TextView
        val fechaLlenado:TextView

        init {
            tipoComida = view.findViewById(R.id.ci_tipoComida)
            comidaPrincipal = view.findViewById(R.id.ci_comidaPrincipal)
            comidaSecundaria = view.findViewById(R.id.ci_comidaSecundaria)
            bebida = view.findViewById(R.id.ci_bebida)
            postre = view.findViewById(R.id.ci_Postre)
            comidaTentacion = view.findViewById(R.id.ci_comidaTentacion)
            hambre = view.findViewById(R.id.ci_Hambre)
            fechaLlenado = view.findViewById(R.id.ci_fechaLlenado)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComidasAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comida_item,parent,false)

        return ComidasAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.count()
    }

    override fun onBindViewHolder(holder: ComidasAdapter.ViewHolder, position: Int) {
        holder.tipoComida.setText("Tipo de Comida: " + dataset[position].tipoComida)
        holder.comidaPrincipal.setText("Comida Principal: " + dataset[position].comidaPrincipal)
        holder.comidaSecundaria.setText("Comida Secundaria: " + dataset[position].comidaSecundaria)
        holder.bebida.setText("Bebida: " + dataset[position].bebida)
        holder.postre.setText("Postre: " + dataset[position].postre)
        holder.comidaTentacion.setText("Comida Tentacion: " + dataset[position].comidaTentacion)
        holder.hambre.setText("Hambre?: " + dataset[position].hambre)
        holder.fechaLlenado.setText("Fecha Completado: " + dataset[position].fechaLlenado)

    }

}