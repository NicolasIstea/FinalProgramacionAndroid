package com.example.trabajopracticofinal.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.trabajopracticofinal.models.Comida
import com.example.trabajopracticofinal.models.Usuario

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object{
        private val DATABASE_NAME: String = "Tratamiento.db"
        private val DATABASE_VERSION: Int = 4
        val TABLA_USUARIOS = "Usuarios"
        val COLUMN_ID = "id"
        val COLUMN_NOMBRE = "nombre"
        val COLUMN_APELLIDO = "apellido"
        val COLUMN_DNI = "dni"
        val COLUMN_GENERO = "genero"
        val COLUMN_LOCALIDAD = "localidad"
        val COLUMN_TRATAMIENTO = "tratamiento"
        val COLUMN_USUARIO = "usuario"
        val COLUMN_CONTRASEÑA = "contraseña"

        val TABLA_COMIDAS = "Comidas"
        val COMIDAS_COLUMN_TIPOCOMIDA = "tipoComida"
        val COMIDAS_COLUMN_COMIDA_PRINCIPAL = "comidaPrincipal"
        val COMIDAS_COLUMN_COMIDA_SECUNDARIA = "comidaSecundaria"
        val COMIDAS_COLUMN_BEBIDA = "Bebida"
        val COMIDAS_COLUMN_POSTRE = "Postre"
        val COMIDAS_COLUMN_COMIDA_TENTACION = "ComidaTentacion"
        val COMIDAS_COLUMN_HAMBRE= "Hambre"
        val COMIDAS_COLUMN_COMIDA_FECHA_LLENADO = "fechaLlenado"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        // las tablas en nuestra base de datos
        val CREATE_TABLA_USUARIOS = ("CREATE TABLE " +
                TABLA_USUARIOS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NOMBRE + " TEXT,"
                + COLUMN_APELLIDO + " TEXT,"
                + COLUMN_DNI + " INT,"
                + COLUMN_GENERO + " TEXT,"
                + COLUMN_LOCALIDAD + " TEXT,"
                + COLUMN_TRATAMIENTO + " TEXT,"
                + COLUMN_USUARIO + " TEXT,"
                + COLUMN_CONTRASEÑA + " TEXT"
                + ")")

        db?.execSQL(CREATE_TABLA_USUARIOS);

        val CREATE_TABLA_COMIDAS = ("CREATE TABLE " +
                TABLA_COMIDAS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COMIDAS_COLUMN_TIPOCOMIDA + " TEXT,"
                + COMIDAS_COLUMN_COMIDA_PRINCIPAL + " TEXT,"
                + COMIDAS_COLUMN_COMIDA_SECUNDARIA + " TEXT,"
                + COMIDAS_COLUMN_BEBIDA + " TEXT,"
                + COMIDAS_COLUMN_POSTRE + " TEXT,"
                + COMIDAS_COLUMN_COMIDA_TENTACION + " TEXT,"
                + COMIDAS_COLUMN_HAMBRE + " TEXT,"
                + COMIDAS_COLUMN_COMIDA_FECHA_LLENADO + " TEXT"
                + ")")

        db?.execSQL(CREATE_TABLA_COMIDAS);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        if(oldVersion != newVersion) {

            db?.execSQL("DROP TABLE "+ TABLA_USUARIOS )
            db?.execSQL("DROP TABLE "+ TABLA_COMIDAS )

            onCreate(db)
        }
    }

    fun guardarComida(c: Comida) {
        val db = this.writableDatabase

        val values = ContentValues()

        values.put(COMIDAS_COLUMN_TIPOCOMIDA, c.tipoComida)
        values.put(COMIDAS_COLUMN_COMIDA_PRINCIPAL, c.comidaPrincipal)
        values.put(COMIDAS_COLUMN_COMIDA_SECUNDARIA, c.comidaSecundaria)
        values.put(COMIDAS_COLUMN_BEBIDA, c.bebida)
        values.put(COMIDAS_COLUMN_POSTRE, c.postre)
        values.put(COMIDAS_COLUMN_COMIDA_TENTACION, c.comidaTentacion)
        values.put(COMIDAS_COLUMN_HAMBRE, c.hambre)
        values.put(COMIDAS_COLUMN_COMIDA_FECHA_LLENADO, c.fechaLlenado)

        db.insert(TABLA_COMIDAS,null,values)
    }

    fun guardarUsuario(p: Usuario) {
        val db = this.writableDatabase

        val values = ContentValues()

        values.put(COLUMN_NOMBRE, p.nombre)
        values.put(COLUMN_APELLIDO, p.apellido)
        values.put(COLUMN_DNI, p.dni)
        values.put(COLUMN_GENERO, p.genero)
        values.put(COLUMN_LOCALIDAD, p.localidad)
        values.put(COLUMN_TRATAMIENTO, p.tipoTratamiento)
        values.put(COLUMN_USUARIO, p.usuario)
        values.put(COLUMN_CONTRASEÑA, p.contraseña)

        db.insert(TABLA_USUARIOS,null,values)
    }

    fun usuarioExiste(usuario:String, contraseña:String):Usuario {

        val query:String = "SELECT * FROM " + TABLA_USUARIOS + " where usuario = " + "'" + usuario + "'" + " AND " + "contraseña = " + "'" + contraseña + "'"
        val usuario: Usuario = Usuario("","",0,"","","","","")
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(query,null)

        if(cursor != null && cursor.moveToFirst()){

            do {
                usuario.nombre = cursor.getString(cursor.getColumnIndex("nombre"))
                usuario.apellido = cursor.getString(cursor.getColumnIndex("apellido"))
                usuario.dni = cursor.getString(cursor.getColumnIndex("dni")).toInt()
                usuario.genero = cursor.getString(cursor.getColumnIndex("genero"))
                usuario.localidad = cursor.getString(cursor.getColumnIndex("localidad"))
                usuario.tipoTratamiento = cursor.getString(cursor.getColumnIndex("tratamiento"))

            }while(cursor.moveToNext())
        }

        return usuario
    }

    fun traerTodasLasComidas(): ArrayList<Comida> {

        val query:String = "SELECT * FROM " + TABLA_COMIDAS
        val comidas: ArrayList<Comida> = ArrayList<Comida>()
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(query,null)

        if(cursor != null && cursor.moveToFirst()){

            do {
                var comida:Comida = Comida("","","","","","","","")
                comida.tipoComida = cursor.getString(cursor.getColumnIndex("tipoComida"))
                comida.comidaPrincipal = cursor.getString(cursor.getColumnIndex("comidaPrincipal"))
                comida.comidaSecundaria = cursor.getString(cursor.getColumnIndex("comidaSecundaria"))
                comida.bebida = cursor.getString(cursor.getColumnIndex("Bebida"))
                comida.postre = cursor.getString(cursor.getColumnIndex("Postre"))
                comida.comidaTentacion = cursor.getString(cursor.getColumnIndex("ComidaTentacion"))
                comida.hambre = cursor.getString(cursor.getColumnIndex("Hambre"))
                comida.fechaLlenado = cursor.getString(cursor.getColumnIndex("fechaLlenado"))

                comidas.add(comida)

            } while(cursor.moveToNext())
        }

        return comidas
    }
}
