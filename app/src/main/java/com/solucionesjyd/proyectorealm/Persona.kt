package com.solucionesjyd.proyectorealm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Persona() : RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var nombre: String? = null
    var edad: Int? = null
}
