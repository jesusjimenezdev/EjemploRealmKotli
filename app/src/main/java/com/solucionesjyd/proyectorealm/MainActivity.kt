package com.solucionesjyd.proyectorealm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("persona.realm").build()
        val realm = Realm.getInstance(config)

        guardar.setOnClickListener {
            val selectid = realm.where(Persona::class.java).findAllSorted("id", Sort.DESCENDING)
            val id = selectid.first().id + 1
            val nombre = editNombre.text.toString()
            val edad = editEdad.text.toString().toInt()

            realm.beginTransaction()
            val persona = realm.createObject(Persona::class.java, id)
            persona.nombre = nombre
            persona.edad = edad
            try {
                realm.commitTransaction()
                mensaje("Guardo")
            } catch (e: Exception) {
                mensaje("No guardo")
            }

        }

        val todo = realm.where(Persona::class.java).findAll()
        todo.forEach { persona ->
            println("Persona: ${persona.nombre} -- Edad: ${persona.edad} -- Id : ${persona.id}")
        }

        listaS.setOnClickListener {
            val intent = Intent(this, ListaSimpleActivity::class.java)
            startActivity(intent)
        }

        listaP.setOnClickListener {
            val intent = Intent(this, ListaPersonalizadaActivity::class.java)
            startActivity(intent)
        }


    }// fin onCreate

    fun mensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}
