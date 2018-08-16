package com.solucionesjyd.proyectorealm

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class userListAdapter(private var activity: Activity, private var items: ArrayList<userDatos>) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var txtNombre: TextView? = null
        var txtEdad: TextView? = null

        init {
            this.txtNombre = row?.findViewById<TextView>(R.id.txtNombre)
            this.txtEdad = row?.findViewById<TextView>(R.id.txtEdad)
        }
    }


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (p1 == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.datoslista, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = p1
            viewHolder = view.tag as ViewHolder
        }
        var datos = items[p0]
        viewHolder.txtNombre?.text = datos.nombre
        viewHolder.txtEdad?.text = datos.edad

        return view as View
    }

    override fun getItem(p0: Int): Any {
        return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

}