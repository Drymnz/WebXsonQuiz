package com.example.webxsonquiz.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.ObjectOutputStream
import java.net.Socket
import java.io.ObjectInputStream as ObjectInputStream1


class ConectionServert(ip: String, port: Int) {

    private val socket: Socket
    private val outputStream: ObjectOutputStream
    private val inputStream: ObjectInputStream1

    init {

        this.socket = Socket(ip, port)
        this.outputStream = ObjectOutputStream(this.socket?.getOutputStream())
        this.inputStream = ObjectInputStream1(this.socket?.getInputStream())

    }

    public fun sendMessage(messagen: Any?): Any? {
        outputStream.writeObject(messagen)
        return inputStream.readObject()
    }

    public fun getSocket():Socket{
        return  this.socket
    }
}