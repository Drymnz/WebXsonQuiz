package com.cunoc.webxsonquiz.data


import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class ServertRepositoy(ip: String, port: Int) {

    val flowSocket: Flow<ConectionServert> = flow {
        val conectionServert = ConectionServert(ip, port)
        //val socket = Socket(ip, port)
        var booleanReturn:Boolean =  true
        while (conectionServert.getSocket()!!.isConnected) {
            if (booleanReturn && conectionServert.getSocket()!!.isConnected){
                emit(conectionServert)
                booleanReturn = !booleanReturn
            }
            delay(100) // Add a small delay to reduce CPU usage
        }
        conectionServert.getSocket()!!.close()
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