package com.cunoc.webxsonquiz.data

import android.os.Parcel
import android.os.Parcelable
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

class ConectionServert(private val ip: String, private val port: Int) : Parcelable {

    private var socket: Socket? = null
    private var outputStream: ObjectOutputStream? = null
    private var inputStream: ObjectInputStream? = null

    init {
        initializeConnection()
    }

    private fun initializeConnection() {
        try {
            socket = Socket(ip, port)
            outputStream = ObjectOutputStream(socket?.getOutputStream())
            inputStream = ObjectInputStream(socket?.getInputStream())
        } catch (e: ClassNotFoundException) {
            println("Error: La clase del objeto no pudo ser encontrada. Detalles: ${e.message}")
        } catch (e: IOException) {
            println("Error: Fallo en la lectura del objeto. Detalles: ${e.message}")
        } catch (e: Exception) {
            println("Error inesperado: ${e.message}")
        } finally {
            println("Finalizando la operación de lectura del objeto.")
        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt()
    )

    fun sendMessage(message: Any?): Any? {
        outputStream?.writeObject(message)
        try {
            val dataRequest = inputStream?.readObject()
            return dataRequest
        } catch (e: ClassNotFoundException) {
            println("Error: La clase del objeto no pudo ser encontrada. Detalles: ${e.message}")
        } catch (e: IOException) {
            println("Error: Fallo en la lectura del objeto. Detalles: ${e.message}")
        } catch (e: Exception) {
            println("Error inesperado: ${e.message}")
        } finally {
            println("Finalizando la operación de lectura del objeto.")
        }
        return null
    }

    fun getSocket(): Socket? = socket

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ip)
        parcel.writeInt(port)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ConectionServert> {
        override fun createFromParcel(parcel: Parcel): ConectionServert {
            return ConectionServert(parcel)
        }

        override fun newArray(size: Int): Array<ConectionServert?> {
            return arrayOfNulls(size)
        }
    }

    // Método para cerrar la conexión
    fun closeConnection() {
        try {
            inputStream?.close()
            outputStream?.close()
            socket?.close()
        } catch (e: IOException) {
            println("Error al cerrar la conexión: ${e.message}")
        }
    }
}