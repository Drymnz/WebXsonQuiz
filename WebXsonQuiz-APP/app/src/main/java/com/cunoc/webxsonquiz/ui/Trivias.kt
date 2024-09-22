package com.cunoc.webxsonquiz.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.cunoc.webxsonquiz.data.ConectionServert
import com.cunoc.webxsonquiz.data.servert.Trivia
import com.cunoc.webxsonquiz.data.servert.User
import com.example.webxsonquiz.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class Trivias : AppCompatActivity() {
    private var user: User? = null
    private var conectionServer: ConectionServert? = null
    private var INICIO: String = "INICIO"
    private val stateFlow = MutableStateFlow(INICIO)
    private var layout: LinearLayout? = null
    private var listTrivias:ArrayList<Trivia> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trivias)
        val user: User = getIntent().getSerializableExtra("user") as User
        lifecycleScope.launch(Dispatchers.IO) {
            this@Trivias.conectionServer = getIntent().getParcelableExtra("ConectionServert")
            // sending message
            val job = launch {
                stateFlow.collect { newValue ->
                    if (!newValue.equals(this@Trivias.INICIO)){
                        val report = this@Trivias.conectionServer?.sendMessage(newValue)
                    }
                }
            }
        }
        if (  user != null) {
            this.conectionServer = conectionServer // Asigna la conexión
            this.info(user)
        }else{
            finish() // Cerrar la actividad actual y regresar
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun info(user:User){
        this.layout = findViewById(R.id.layout)
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
        this.layout!!.addView(textView)
    }
}