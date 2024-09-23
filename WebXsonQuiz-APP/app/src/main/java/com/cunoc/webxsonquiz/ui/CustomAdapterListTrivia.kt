package com.cunoc.webxsonquiz.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.cunoc.webxsonquiz.data.servert.Trivia
import com.example.webxsonquiz.R

class CustomAdapterListTrivia(
    val context: Context,
    val listTrivias: ArrayList<Trivia>
)  : BaseAdapter()   {
    override fun getCount(): Int {
        return listTrivias.size
    }

    override fun getItem(position: Int): Any {
        return listTrivias[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(index: Int, convertView: View?, p2: ViewGroup?): View {
        var view = convertView
        val trivia: Trivia = listTrivias[index]

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_trivia, p2, false)
        }

        // Inicialización de las vistas del layout
        val textViewTriviaName: TextView = view!!.findViewById(R.id.textViewTriviaName)
        val textViewTriviaTheme: TextView = view!!.findViewById(R.id.textViewTriviaTheme)
        val textViewTriviaTime: TextView = view!!.findViewById(R.id.textViewTriviaTime)

        // Asignación de los valores correspondientes de la trivia a las vistas
        textViewTriviaName.text = trivia.name
        textViewTriviaTheme.text = trivia.theme
        textViewTriviaTime.text = trivia.time.toString()

        return view
    }

}