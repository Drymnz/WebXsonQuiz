package com.example.webxsonquiz.ui

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
import androidx.lifecycle.viewModelScope
import com.example.webxsonquiz.R
import com.example.webxsonquiz.servert.AsyncResponse
import com.example.webxsonquiz.servert.MyTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

class MainActivity : AppCompatActivity(), AsyncResponse {

    private val viewModel by viewModels<MainViewModel>()
    private var firtsSocket: Boolean = false
    private var socket: Socket? = null

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
                            if (uiState.socket.isConnected && !this@MainActivity.firtsSocket) {
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
        lifecycleScope.launch (Dispatchers.IO){
            if (this@MainActivity.socket != null) {
                val textsend: String = findViewById<EditText>(R.id.textArea).getText().toString()
                sendMessage(textsend)
            }
        }
    }

    suspend fun sendMessage(message: String) {
        try {
            val outputStream = ObjectOutputStream(this.socket?.getOutputStream())
            val inputStream = ObjectInputStream(this.socket?.getInputStream())
            // sending message
            outputStream.writeObject(message)
            // receiving message
            val report = inputStream.readObject()
            if (report is Boolean) {
                val reportBlo: Boolean = report as Boolean
                val stringValue =
                if (reportBlo) "Entraste a las trivias" else "Lo siento tu usario esta incoreccto"
                withContext(Dispatchers.Main){//Siempre Activites de vista en el primer hilo
                    val setText = findViewById<TextView>(R.id.textView)
                    setText.setText(stringValue)
                }
            }
        } catch (e: ArithmeticException) {
            Toast.makeText(
                this,
                getString(R.string.connected_false),
                Toast.LENGTH_LONG
            ).show()
        }
    }


    override fun processResponse(output: String?) {
        val setText = findViewById<TextView>(R.id.textView)
        setText.setText(output)
    }

    private fun setSocket(socket: Socket) {
        this.socket = socket
    }
}