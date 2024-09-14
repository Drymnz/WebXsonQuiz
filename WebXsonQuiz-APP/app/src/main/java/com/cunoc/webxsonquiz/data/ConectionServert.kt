package com.cunoc.webxsonquiz.data

import com.cunoc.webxsonquiz.data.servert.User
import java.io.IOException
import java.io.ObjectOutputStream
import java.net.Socket
import java.io.ObjectInputStream


class ConectionServert(ip: String, port: Int) {

    private val socket: Socket
    private val outputStream: ObjectOutputStream
    private val inputStream: ObjectInputStream

    init {

        this.socket = Socket(ip, port)
        this.outputStream = ObjectOutputStream(this.socket?.getOutputStream())
        this.inputStream = ObjectInputStream(this.socket?.getInputStream())

    }

    public fun sendMessage(messagen: Any?): Any? {
        outputStream.writeObject(messagen)
        var dataRequest: Any? = null
        try {
            val user = inputStream.readObject()
            if (user is User){
                println(user.toString())
            }
            dataRequest = user
        } catch (e: ClassNotFoundException) {
            println("Error: La clase del objeto no pudo ser encontrada. Detalles: ${e.message}")
        } catch (e: IOException) {
            println("Error: Fallo en la lectura del objeto. Detalles: ${e.message}")
        } catch (e: Exception) {
            println("Error inesperado: ${e.message}")
        } finally {
            println("Finalizando la operación de lectura del objeto.")
        }
        return dataRequest
    }

    public fun getSocket():Socket{
        return  this.socket
    }
}