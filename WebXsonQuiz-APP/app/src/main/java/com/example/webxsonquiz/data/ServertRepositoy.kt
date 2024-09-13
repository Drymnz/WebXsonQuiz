package com.example.webxsonquiz.data

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.Socket

class ServertRepositoy(ip: String, port: Int) {

    val flowSocket: Flow<Socket> = flow {
        val socket = Socket(ip, port)
        var booleanReturn:Boolean =  true
        while (socket.isConnected) {
            if (booleanReturn && socket.isConnected){
                emit(socket)
                booleanReturn = !booleanReturn
            }
            delay(100) // Add a small delay to reduce CPU usage
        }
        socket.close()
    }

    /*
            val outputStream = ObjectOutputStream(socket.getOutputStream())
            val inputStream = ObjectInputStream(socket.getInputStream())
        // sending message
        outputStream.writeObject(login)
        var bombitas:String  = inputStream.readObject() as String
        var login:Boolean = bombitas as Boolean
        emit(bombitas)
        while (login)
        {
            emit(bombitas)
            outputStream.writeObject(bombitas)
            // receiving message
            val report = inputStream.readObject() as String
            bombitas = report as String
            emit(bombitas)
            login  = !(bombitas.equals("false"))
        }

    } */
}