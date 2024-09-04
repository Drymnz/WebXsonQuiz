package com.example.webxsonquiz.servert

import android.os.AsyncTask
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

class MyTask(val ip: String, val port: Int, val message: String) : AsyncTask<Void, Void, String>() {

    var delegate: AsyncResponse? = null
    val direccion:String = "192.168.1.47"
            val portDi:Int = 7090


    override fun doInBackground(vararg p0: Void?): String {
        try {
            val socket = Socket(direccion, portDi)
            val outputStream = ObjectOutputStream(socket.getOutputStream())
            val inputStream = ObjectInputStream(socket.getInputStream())

            // sending message
            outputStream.writeObject(message)

            // receiving message
            val response: String = inputStream.readObject() as String;
            // showing message
            return response.toString()
        }catch (e: Exception) {
            System.out.println("Ip incorrecta->" + e.message)
            return ""
        }
    }

    override fun onPostExecute(result: String?) {
        delegate?.processResponse(result)
    }
}