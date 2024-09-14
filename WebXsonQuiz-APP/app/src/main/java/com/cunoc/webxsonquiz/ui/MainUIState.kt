package com.cunoc.webxsonquiz.ui

import com.cunoc.webxsonquiz.data.ConectionServert

sealed class MainUIState {
    object Loging: MainUIState()// se necesita para como que inicializar
    data class Success(val socket: ConectionServert): MainUIState()
    data class Error(val sendMensssa:String): MainUIState()
}