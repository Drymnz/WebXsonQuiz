package com.example.webxsonquiz.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket


class ConectionServert(ip: String, port: Int) {
    private val socket:Socket
    private val outputStream:ObjectOutputStream
    private val inputStream:ObjectInputStream

    init {
        this.socket = Socket(ip, port)
        this.outputStream = ObjectOutputStream(this.socket?.getOutputStream())
        this.inputStream = ObjectInputStream(this.socket?.getInputStream())

    }



}