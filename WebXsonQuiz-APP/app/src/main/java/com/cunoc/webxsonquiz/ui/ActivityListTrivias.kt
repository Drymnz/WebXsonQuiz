package com.cunoc.webxsonquiz.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
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
import com.cunoc.webxsonquiz.data.servert.QuizAttempt
import com.cunoc.webxsonquiz.data.servert.Trivia
import com.cunoc.webxsonquiz.data.servert.User
import com.example.webxsonquiz.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityListTrivias : AppCompatActivity() {
    private var user: User? = null
    private var conectionServer: ConectionServert? = null
    private val INICIO: String = "INICIO"
    private val UPDATA: String = "UPDATA"
    private val PUSH_RESULT: String = "PUSH_RESULT"

    private val stateFlow = MutableStateFlow(INICIO)
    private var layout: LinearLayout? = null
    private var listTrivias:ArrayList<Trivia> = ArrayList()
    private var quizAttempt:QuizAttempt? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trivias)
        val user: User = getIntent()!!.getSerializableExtra("user") as User
        lifecycleScope.launch(Dispatchers.IO) {
            this@ActivityListTrivias.conectionServer = getIntent().getParcelableExtra("ConectionServert")
            // sending message
            val job = launch {
                stateFlow.collect { newValue ->
                    if (newValue.equals(this@ActivityListTrivias.UPDATA)|| newValue.equals(this@ActivityListTrivias.INICIO)){
                        var newTrivia = this@ActivityListTrivias.conectionServer?.sendMessage(this@ActivityListTrivias.listTrivias)
                        if ( newTrivia!=null){
                            if (newTrivia is Trivia){
                                this@ActivityListTrivias.listTrivias.add(newTrivia)
                                var outBoolean:Boolean = true
                                var intentoss:Int = 0
                                do {
                                    val newTriviaTwo = this@ActivityListTrivias.conectionServer?.sendMessage(newTrivia)
                                    if (newTriviaTwo is Trivia){
                                        this@ActivityListTrivias.listTrivias.add(newTriviaTwo)
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
                                this@ActivityListTrivias.rederListTrivia(this@ActivityListTrivias.listTrivias)
                            }
                        }
                    }
                    else if (newValue.equals(this@ActivityListTrivias.PUSH_RESULT)){
                        val newTriviaTwo = this@ActivityListTrivias.conectionServer?.sendMessage(this@ActivityListTrivias.quizAttempt)
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
            val listViewTrivia: ListView = findViewById(R.id.listViewTrivia)
            val customAdapterListTrivia = CustomAdapterListTrivia(this@ActivityListTrivias, list)
            listViewTrivia.adapter = customAdapterListTrivia
            listViewTrivia.setOnItemClickListener { parent, view, position, id ->
                val selectedItem: Trivia = list[position]
                Toast.makeText(this@ActivityListTrivias, "Clicked: ${selectedItem.name}", Toast.LENGTH_SHORT).show()
                this@ActivityListTrivias.goTrivia(selectedItem)
            }
        }
    }

    fun clickUpDataTrivia(view: View){
        Toast.makeText(this@ActivityListTrivias, "Clicked: ${stateFlow.value}", Toast.LENGTH_SHORT).show()
        stateFlow.value = if (stateFlow.value == this.INICIO) this.UPDATA else this.INICIO
    }

    private fun goTrivia(trivia:Trivia){
        val intent = Intent(this, ActivityTrivia::class.java)
        intent.putExtra("trivia", trivia)
        //startActivity(intent)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            // Obtener el resultado de la segunda actividad
            val resultData = data?.getSerializableExtra("result_key")
            val verificar:QuizAttempt = resultData as QuizAttempt
            verificar.user = this.user!!.id
            this.quizAttempt = verificar

            stateFlow.value = this.PUSH_RESULT
            Toast.makeText(this, "Resultado: ${verificar.toString()}", Toast.LENGTH_SHORT).show()
            Log.i("Resultado de la trivia",verificar.toString())
        }
    }

}