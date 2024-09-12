package com.example.webxsonquiz.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

class ServertRepositoy (login:String) {

    val counter: Flow<Int> = flow {
        var bombitas = 1
        while (true){
            bombitas += 1
            emit(bombitas)
            delay(1000)
        }
    }

    /* val direccion:String = "192.168.1.47"
    val portDi:Int = 7090

    val counter : Flow<String> = flow{
        val socket = Socket(direccion, portDi)
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