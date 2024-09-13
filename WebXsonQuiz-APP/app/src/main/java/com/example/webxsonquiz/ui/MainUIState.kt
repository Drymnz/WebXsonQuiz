package com.example.webxsonquiz.ui

import java.net.Socket

sealed class MainUIState {
    object Loging:MainUIState()// se necesita para como que inicializar
    data class Success(val socket: Socket):MainUIState ()
    data class Error(val sendMensssa:String):MainUIState ()
}