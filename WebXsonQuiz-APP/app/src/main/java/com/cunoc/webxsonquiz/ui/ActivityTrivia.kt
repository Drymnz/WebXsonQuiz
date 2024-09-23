package com.cunoc.webxsonquiz.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
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
        val textViewName:TextView = findViewById(R.id.textViewNameTrivia)
        val textViewThemeTrivia:TextView = findViewById(R.id.textViewThemeTrivia)
        val textViewIdUserTrivia:TextView = findViewById(R.id.textViewIdUserTrivia)
        val textViewTimeTrivia:TextView = findViewById(R.id.textViewTimeTrivia)

        textViewName.text = userTrivia.name
        textViewThemeTrivia.text = userTrivia.theme
        textViewIdUserTrivia.text = userTrivia.idUser
        textViewTimeTrivia.text = userTrivia.time.toString()

        // Crea los
        for (element in userTrivia.getListComponent()) {
            this.textView(layout,element.text)
        }
        // Crear un Button dinámico
        val button = Button(this)
        button.text = "Hacer algo"

        val buttonParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        buttonParams.setMargins(16, 16, 16, 16)
        button.layoutParams = buttonParams

        button.setOnClickListener {
            Toast.makeText(this, "¡Botón presionado!", Toast.LENGTH_SHORT).show()
        }
        layout.addView(button)
    }

    private fun textView(layout: LinearLayout,text:String){
        val textView = TextView(this@ActivityTrivia)

        textView.text = text
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
    }

}