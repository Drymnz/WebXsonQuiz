package com.cunoc.webxsonquiz.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.webxsonquiz.R
import com.cunoc.webxsonquiz.data.ConectionServert
import com.cunoc.webxsonquiz.data.servert.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private var firtsSocket: Boolean = false
    private var conectionServert: ConectionServert? = null
    private var INICIO: String = "INICIO"
    private val stateFlow = MutableStateFlow(INICIO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()//pnatalla completa
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun connectedToServer(ip: String, port: Int) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is MainUIState.Error -> {
                            this@MainActivity.disebleCompi(false)
                        }

                        MainUIState.Loging -> {}
                        is MainUIState.Success -> {
                            //&& !this@MainActivity.firtsSocket
                            if (uiState.socket.getSocket()!!.isConnected && !this@MainActivity.firtsSocket) {
                                this@MainActivity.disebleCompi(true)
                                this@MainActivity.setSocket(uiState.socket)
                            }
                        }
                    }
                }
            }
        }
        viewModel.connectionToServer(ip, port)
    }

    fun disebleCompi(enable: Boolean) {
        this.firtsSocket = enable
        findViewById<TextView>(R.id.buttonCompi).setEnabled(enable)
        findViewById<TextView>(R.id.textArea).setEnabled(enable)
        findViewById<TextView>(R.id.textArea).setEnabled(enable)
        findViewById<TextView>(R.id.textIPServert).setEnabled(!enable)
        findViewById<TextView>(R.id.portServert).setEnabled(!enable)
        findViewById<TextView>(R.id.bConection).setEnabled(!enable)
        val messText: String =
            if (enable) getString(R.string.connected_true) else getString(R.string.connected_false)
        Toast.makeText(
            this,
            messText,
            Toast.LENGTH_LONG
        ).show()
        if (messText.equals(getString(R.string.connected_true))) {
            findViewById<TextView>(R.id.textArea).setHint(getString(R.string.insert_text))
        } else {
            findViewById<TextView>(R.id.textArea).setHint(getString(R.string.first_ip))
        }

    }

    fun clickConnectIP(view: View) {
        val getIp = findViewById<TextView>(R.id.textIPServert)
        val getPort = findViewById<TextView>(R.id.portServert)
        val ipv4Regex =
            """^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$""".toRegex()
        val ip = getIp.text.toString()
        val port = getPort.text.toString().toIntOrNull() // Convierte el puerto de forma segura
        if (port != null && ipv4Regex.matches(ip)) {
            this.connectedToServer(ip, port)
        }
    }

    fun clickCompi(view: View) {
        Log.i("clickCompi,MainActivity",findViewById<EditText>(R.id.textArea).getText().toString())
        if (stateFlow.value.equals(this.INICIO)) {
            stateFlow.value = findViewById<EditText>(R.id.textArea).getText().toString()
            lifecycleScope.launch(Dispatchers.IO) {
                // sending message
                val job = launch {
                    stateFlow.collect { newValue ->
                        // receiving message`
                        val report = this@MainActivity.conectionServert?.sendMessage(newValue)
                        if (report is Boolean) {
                            this@MainActivity.irTrivias(report,this@MainActivity ,null)
                        }
                        println(report.toString())
                        if (report is User) {
                            this@MainActivity.irTrivias(true,this@MainActivity ,report)
                        }
                    }
                }
            }
        } else {
            stateFlow.value = findViewById<EditText>(R.id.textArea).getText().toString()
        }
    }

    private fun setSocket(conectionServert: ConectionServert) {
        this.conectionServert = conectionServert
    }

    private fun getConectionServer(): ConectionServert? {
        return this.conectionServert
    }

    override fun onDestroy() {
        super.onDestroy()
        if (conectionServert != null && conectionServert!!.getSocket()!!.isConnected) {
            conectionServert!!.sendMessage("false")
            conectionServert!!.getSocket()!!.close()
        }
    }

    suspend fun irTrivias(requestServert: Boolean,context: Context,user: User?) {
        val messText: String =
            if (requestServert) getString(R.string.login_true) else getString(R.string.connected_false)
        this@MainActivity.messeg(context, messText)

        if (requestServert && user != null) {
            withContext(Dispatchers.Main) {
                val intent = Intent(context, Trivias::class.java)
                intent.putExtra("user", user) // Enviar el usuario
                intent.putExtra("ConectionServert", this@MainActivity.getConectionServer()) // Enviar la conexión
                startActivity(intent)
            }
        }
    }

    suspend fun messeg(context: Context,messText:String){
        withContext(Dispatchers.Main) {//Siempre Activites de vista en el primer hilo
            Toast.makeText(context, messText, Toast.LENGTH_SHORT).show()
        }
    }

}