package com.cunoc.webxsonquiz.data

import android.os.Parcel
import android.os.Parcelable
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

class ConectionServert(private val ip: String, private val port: Int) : Parcelable {

    private val socket: Socket = Socket(ip, port)
    private val outputStream: ObjectOutputStream = ObjectOutputStream(this.socket.getOutputStream())
    private val inputStream: ObjectInputStream = ObjectInputStream(this.socket.getInputStream())

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ip)
        parcel.writeInt(port)
    }

    override fun describeContents(): Int {
        return 0
    }

    public fun sendMessage(messagen: Any?): Any? {
        outputStream.writeObject(messagen)
        var dataRequest: Any? = null
        try {
            val user = inputStream.readObject()
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

    public fun getSocket(): Socket {
        return this.socket
    }

    companion object CREATOR : Parcelable.Creator<ConectionServert> {
        override fun createFromParcel(parcel: Parcel): ConectionServert {
            return ConectionServert(parcel)
        }

        override fun newArray(size: Int): Array<ConectionServert?> {
            return arrayOfNulls(size)
        }
    }
}