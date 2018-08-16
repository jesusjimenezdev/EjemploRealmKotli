package com.solucionesjyd.proyectorealm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_lista_simple.*

class ListaSimpleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_simple)

        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .name("persona.realm").build()
        val realm = Realm.getInstance(config)

        var list = ArrayList<String>()
        val adapter: ArrayAdapter<String>
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter

        val todo = realm.where(Persona::class.java).findAll()
        todo.forEach { persona ->
            val nombre = persona.nombre.toString()
            list.add(nombre)
        }

    }
}
