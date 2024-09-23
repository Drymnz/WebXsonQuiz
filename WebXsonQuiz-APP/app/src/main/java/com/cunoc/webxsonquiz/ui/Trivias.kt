package com.cunoc.webxsonquiz.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
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
import kotlinx.coroutines.withContext

class Trivias : AppCompatActivity() {
    private var user: User? = null
    private var conectionServer: ConectionServert? = null
    private val INICIO: String = "INICIO"
    private val UPDATA: String = "INICIO"

    private val stateFlow = MutableStateFlow(INICIO)
    private var layout: LinearLayout? = null
    private var listTrivias:ArrayList<Trivia> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trivias)
        val user: User = getIntent().getSerializableExtra("user") as User
        lifecycleScope.launch(Dispatchers.Default) {
            this@Trivias.conectionServer = getIntent().getParcelableExtra("ConectionServert")
            // sending message
            val job = launch {
                stateFlow.collect { newValue ->
                    if (newValue.equals(this@Trivias.UPDATA)|| newValue.equals(this@Trivias.INICIO)){
                        var newTrivia = this@Trivias.conectionServer?.sendMessage(this@Trivias.listTrivias)
                        if ( newTrivia!=null){
                            if (newTrivia is Trivia){
                                this@Trivias.listTrivias.add(newTrivia)
                                var outBoolean:Boolean = true
                                var intentoss:Int = 0
                                do {
                                    val newTriviaTwo = this@Trivias.conectionServer?.sendMessage(newTrivia)
                                    if (newTriviaTwo is Trivia){
                                        this@Trivias.listTrivias.add(newTriviaTwo)
                                    }
                                    if (newTriviaTwo is Boolean){
                                        outBoolean = newTriviaTwo
                                    }
                                    if (newTriviaTwo==null && intentoss > 10){
                                        outBoolean = false
                                    }
                                    if (newTriviaTwo==null) {
                                        intentoss++
                                    }
                                }while (outBoolean)
                                this@Trivias.rederListTrivia(this@Trivias.listTrivias)
                            }
                        }
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

        // Inflar el layout
        val inflater = LayoutInflater.from(this)
        val resultView = inflater.inflate(R.layout.layout_user, null)

        val textViewIdUser: TextView = resultView.findViewById(R.id.textViewIdUser)
        val textViewUserInstition: TextView = resultView.findViewById(R.id.textViewUserInstitution)

        textViewIdUser.text = user.id.toString()
        textViewUserInstition.text = user.institution.toString()

        this.layout!!.addView(resultView)
    }
    private suspend fun rederListTrivia(list: ArrayList<Trivia>) {
        withContext(Dispatchers.Main) {
            // Inflar el layout
            val listViewTrivia: ListView = findViewById(R.id.listViewTrivia)
            val customAdapterListTrivia = CustomAdapterListTrivia(this@Trivias, list)
            listViewTrivia.adapter = customAdapterListTrivia
        }
    }

    fun clickUpDataTrivia(view: View){
        stateFlow.value = if (stateFlow.value == this.INICIO) this.UPDATA else this.INICIO
    }

}