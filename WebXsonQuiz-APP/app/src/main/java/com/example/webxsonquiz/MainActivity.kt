package com.example.webxsonquiz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.webxsonquiz.servert.AsyncResponse
import com.example.webxsonquiz.servert.MyTask

class MainActivity : AppCompatActivity(), AsyncResponse {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun clickCompi(view: View){
        val textsend = findViewById<EditText>(R.id.textArea)

        val textSend = textsend.getText()
        val  portN:Int = 8956
        println("Envie "+ textSend)
        val task = MyTask("192.168.1.47", portN, textSend.toString())//create the object
        task.delegate = this
        task.execute()
    }

    /**
     * return ip entered
     * */
    private fun getIpServert():String {
        val text:String = findViewById<EditText>(R.id.textView).text.toString().replace("\\s".toRegex(), "")
        Log.d("IP SERVER",text)
        if (!text.isEmpty()){
            return text
        }
        return ""
    }

    override fun processResponse(output: String?) {
        val setText = findViewById<TextView>(R.id.textView)
        setText.setText(output)
    }
}