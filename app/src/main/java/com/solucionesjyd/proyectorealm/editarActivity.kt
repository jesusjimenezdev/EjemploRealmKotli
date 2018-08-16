package com.solucionesjyd.proyectorealm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_editar.*

class editarActivity : AppCompatActivity() {

    var nombre: String = ""
    var edad: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .name("persona.realm").build()
        val realm = Realm.getInstance(config)

        val b: Bundle = intent.extras
        val id = b.getLong("id")
        val select = realm.where(Persona::class.java).equalTo("id", id).findAll()
        select.forEach { persona ->
            nombre = persona.nombre.toString()
            edad = persona.edad.toString()
        }

        edNombre.setText(nombre)
        edEdad.setText(edad)

        editar.setOnClickListener {
            val select = realm.where(Persona::class.java).equalTo("id", id).findAll()
            select.forEach { persona ->
                realm.beginTransaction()
                persona.nombre = edNombre.text.toString()
                persona.edad = edEdad.text.toString().toInt()
                try {
                    realm.commitTransaction()
                    val intent = Intent(this, ListaPersonalizadaActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(this, "no actualizo", Toast.LENGTH_SHORT).show()
                }
            }
        }

        eliminar.setOnClickListener {
            val alertaDialog = AlertDialog.Builder(this).create()
            alertaDialog.setTitle("Alerta")
            alertaDialog.setMessage("Deseas eliminar el registro")
            alertaDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar", { dialogInterface, i ->
                val select = realm.where(Persona::class.java).equalTo("id", id).findAll()
                select.forEach { persona ->
                    realm.beginTransaction()
                    persona.deleteFromRealm()
                    realm.commitTransaction()
                    val intent = Intent(this, ListaPersonalizadaActivity::class.java)
                    startActivity(intent)
                }
            })
            alertaDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar", { dialogInterface, i ->
                Toast.makeText(this, "Accion cancelada", Toast.LENGTH_SHORT).show()
            })
            alertaDialog.show()
        }


    }
}
