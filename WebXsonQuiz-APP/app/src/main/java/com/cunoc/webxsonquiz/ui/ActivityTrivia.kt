package com.cunoc.webxsonquiz.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cunoc.webxsonquiz.data.servert.Trivia
import com.cunoc.webxsonquiz.data.servert.User
import com.example.webxsonquiz.R

class ActivityTrivia : AppCompatActivity() {
    private var trivia: Trivia? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trivia)
        val useTrivia = getIntent().getSerializableExtra("trivia")
        if (useTrivia != null){
            val userTrivia:Trivia = useTrivia as Trivia
            this.loadTrivia(userTrivia)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadTrivia(userTrivia: Trivia) {
        this.trivia = userTrivia
        val layout: LinearLayout = findViewById(R.id.LayoutActivityTrivia)
        // Crea un nuevo TextView
        val textView = TextView(this@ActivityTrivia)

        textView.text = userTrivia.name
        textView.textSize = 16f
        textView.gravity = Gravity.CENTER

        // Configurar parámetros de diseño
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, 0)
        textView.layoutParams = params

        // Agregar TextView al LinearLayout
        layout.addView(textView)

        // Logs para depuración
        Log.d("LoadTrivia", "TextView agregado con texto: ${userTrivia.name}")
        Toast.makeText(this, "Agregado: ${userTrivia.name}", Toast.LENGTH_SHORT).show()
    }


}