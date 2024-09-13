package com.example.webxsonquiz.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

class ServertRepositoy(ip: String, port: Int) {

    val counter: Flow<Socket> = flow {
        var socket = Socket(ip, port)
        while (socket.isConnected) {
            emit(socket)
            delay(1000)
        }
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