package com.cunoc.webxsonquiz.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cunoc.webxsonquiz.data.ConectionServert
import com.cunoc.webxsonquiz.data.servert.User
import com.example.webxsonquiz.R

class Trivias : AppCompatActivity() {
    private var user: User? = null
    private var conectionServer: ConectionServert? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trivias)
        val user: User = getIntent().getSerializableExtra("user") as User
        //val conectionServer = getIntent().getSerializableExtra("conectionServert") as ConectionServert
        if (  user != null) {
            this.info(user)
        }else{
            // Si las condiciones no se cumplen, enviar un resultado y regresar
            finish() // Cerrar la actividad actual y regresar
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun info(user:User){
        val layout: LinearLayout = findViewById(R.id.layout)
        this.user = user
        val textView = TextView(this).apply {
            text = user.toString()
            setTextColor(Color.RED) // Cambiar el color del texto
            textSize = 20f // Establecer el tamaño del texto
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        layout.addView(textView)
    }
}