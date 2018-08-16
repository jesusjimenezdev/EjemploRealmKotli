package com.solucionesjyd.proyectorealm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_lista_personalizada.*

class ListaPersonalizadaActivity : AppCompatActivity() {

    var list = ArrayList<userDatos>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_personalizada)
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .name("persona.realm").build()
        val realm = Realm.getInstance(config)
        val personas: List<Persona> = realm.where(Persona::class.java).findAll()
        val adapter = userListAdapter(this, list)
        listapersonalizada.adapter = adapter
        val todo = realm.where(Persona::class.java).findAll()
        todo.forEach { persona ->
            list.add(userDatos(persona.nombre.toString(), persona.edad.toString()))
        }

        listapersonalizada.setOnItemClickListener { adapterView, view, i, l ->
            val id = personas[i].id
            val intent = Intent(this, editarActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
            finish()
        }
    }
}
