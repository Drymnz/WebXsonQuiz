package com.cunoc.webxsonquiz.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cunoc.webxsonquiz.data.ResultComponentTrivia
import com.cunoc.webxsonquiz.data.servert.ClassComponent
import com.cunoc.webxsonquiz.data.servert.ComponentTrivia
import com.cunoc.webxsonquiz.data.servert.Trivia
import com.example.webxsonquiz.R

class ActivityTrivia : AppCompatActivity() {

    private var textViewTimeTrivia: TextView? = null;
    private val list: ArrayList<ResultComponentTrivia> = ArrayList<ResultComponentTrivia>();
    private val CREADOR:String = "Creador : "
    private val TIME_TRIVIA:String = "Tiempo de la trivia : "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trivia)
        val useTrivia = getIntent().getSerializableExtra("trivia")
        if (useTrivia != null) {
            val userTrivia: Trivia = useTrivia as Trivia
            this.loadTrivia(userTrivia)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadTrivia(userTrivia: Trivia) {
        this.loadInfomTrivia(userTrivia)
        for (element in userTrivia.getListComponent()) {
            this.loadComponentTrivia(element)
        }
        this.loadButtonSend()
    }

    private fun loadComponentTrivia(component: ComponentTrivia) {
        this.loadQuestion(component.text)
        when (component.type) {//null
            ClassComponent.CAMPO_TEXT -> {
                //
                this.loadTextField(component)
            }

            ClassComponent.AREA_TEXT -> {
                //
                this.loadAreaText(component)
            }

            ClassComponent.CHECKBOX -> {
                //
                this.loadCheckbox(component)
            }

            ClassComponent.RADIO -> {
                this.loadRadio(component)
            }

            ClassComponent.FICHERO -> {
                this.loadFile(component)
            }

            ClassComponent.COMBO -> {
                this.loadCombo(component)
            }

            ClassComponent.NULL_EMPTY -> {
                this.handleNullOrEmpty(component)
            }

            else -> { // Note the block
                print("x no es 1 o 2")
            }
        }
    }

    private fun loadTextField(component: ComponentTrivia) {
        // Crear un EditText
        val editText = EditText(this).apply {
            hint = "Ingresa tu texto aquí"
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        list.add(ResultComponentTrivia(component,editText ))
        val layout: LinearLayout = findViewById(R.id.LayoutActivityTrivia)
        layout!!.addView(editText)
    }

    private fun loadAreaText(component: ComponentTrivia) {
        // Crear un EditText multilinea
        val limitLine = component.row
        val limitColumn = component.column
        val maxCharacters = limitColumn * limitLine
        val editTextMultiLine = EditText(this).apply {
            hint = "Ingresa tu texto aquí"
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            // Configurar para permitir múltiples líneas
            isSingleLine = false
            maxLines = limitLine // Límite de líneas
            inputType =
                android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE

            // Establecer un límite de caracteres (por ejemplo, 100 caracteres)
            filters = arrayOf(InputFilter.LengthFilter(maxCharacters))
        }

        editTextMultiLine.setVerticalScrollBarEnabled(true)
        editTextMultiLine.setHorizontalScrollBarEnabled(true)
        val layout: LinearLayout = findViewById(R.id.LayoutActivityTrivia)
        layout!!.addView(editTextMultiLine)
        list.add(ResultComponentTrivia(component,editTextMultiLine ))
    }

    private fun loadCheckbox(component: ComponentTrivia) {
        val layout: LinearLayout = findViewById(R.id.LayoutActivityTrivia)
        val arrayOfStrings = component.options.split("|").toTypedArray()
        // Crear un grupo de CheckBox en base al array de strings
        val checkBoxes = arrayOfStrings.map { option ->
            CheckBox(this).apply {
                text = option
            }
        }

        // Agregar los CheckBox al layout
        checkBoxes.forEach { checkBox ->
            layout.addView(checkBox)
        }

       // list.add(ResultComponentTrivia(component, ))
    }

    private fun loadFile(component: ComponentTrivia) {

    }

    private fun loadCombo(component: ComponentTrivia) {
        val layout: LinearLayout = findViewById(R.id.LayoutActivityTrivia)
        val arrayOfStrings = component.options.split("|").toTypedArray()
        // Crear un Spinner
        val spinner = Spinner(this).apply {
            // Crear un adaptador para el Spinner
            val adapter = ArrayAdapter(this@ActivityTrivia, android.R.layout.simple_spinner_item, arrayOfStrings).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Establecer el diseño del dropdown
            }
            this.adapter = adapter // Establecer el adaptador en el Spinner
        }

        // Agregar el Spinner al layout
        layout.addView(spinner)
        list.add(ResultComponentTrivia(component,spinner ))
    }

    private fun loadRadio(component: ComponentTrivia) {
        val layout: LinearLayout = findViewById(R.id.LayoutActivityTrivia)
        val arrayOfStrings = component.options.split("|").toTypedArray()
        // Crear un RadioGroup
        val radioGroup = RadioGroup(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        // Crear RadioButton en base al array de strings
        arrayOfStrings.forEach { option ->
            val radioButton = RadioButton(this).apply {
                text = option
                id = View.generateViewId() // Generar un ID único para cada RadioButton
            }
            radioGroup.addView(radioButton) // Agregar el RadioButton al RadioGroup
        }

        // Agregar el RadioGroup al layout
        layout.addView(radioGroup)
        list.add(ResultComponentTrivia(component,radioGroup ))
    }

    private fun handleNullOrEmpty(component: ComponentTrivia) {}

    private fun loadQuestion(text: String) {
        val inflater = LayoutInflater.from(this)
        val resultView = inflater.inflate(R.layout.layout_trivia_component_question, null)

        val textViewIdUser: TextView = resultView.findViewById(R.id.textViewQuestionTrivia)

        textViewIdUser.text = text

        val layout: LinearLayout = findViewById(R.id.LayoutActivityTrivia)
        layout!!.addView(resultView)
    }

    @SuppressLint("MissingInflatedId")
    private fun loadInfomTrivia(trivia: Trivia) {
        val inflater = LayoutInflater.from(this)
        val resultView = inflater.inflate(R.layout.layout_info_trivia, null)

        val textViewNameTrivia: TextView = resultView.findViewById(R.id.textViewNameTrivia)
        val textViewThemeTrivia: TextView = resultView.findViewById(R.id.textViewThemeTrivia)
        val textViewIdUserTrivia: TextView = resultView.findViewById(R.id.textViewIdUserTrivia)
        this.textViewTimeTrivia = resultView.findViewById(R.id.textViewTimeTrivia)
        textViewNameTrivia.text = trivia.name
        textViewThemeTrivia.text = trivia.theme
        textViewIdUserTrivia.text = this.CREADOR + trivia.idUser
        this.timeTrivia(trivia.time)

        val layout: LinearLayout = findViewById(R.id.LayoutActivityTrivia)
        layout!!.addView(resultView)
    }

    private fun timeTrivia(time: Double){
        this.textViewTimeTrivia!!.text =  this.TIME_TRIVIA + time.toString()
    }

    private fun loadButtonSend() {
        // Crear un botón programáticamente
        val button = Button(this).apply {
            text = "Enviar resultados"
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setBackgroundColor(Color.parseColor("#02BFF5")) // Fondo azul personalizado
            setTextColor(Color.WHITE) // Texto blanco para el botón
            textSize = 18f // Tamaño del texto
        }

        // Definir la funcionalidad al hacer clic
        button.setOnClickListener {
            Toast.makeText(this, "Hello, World!", Toast.LENGTH_SHORT).show()
        }
        val layout: LinearLayout = findViewById<LinearLayout>(R.id.LayoutActivityTrivia)
        layout.addView(button)
    }


}