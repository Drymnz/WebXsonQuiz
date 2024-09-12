package com.example.webxsonquiz.ui

sealed class MainUIState {
    object Loging:MainUIState()
    data class Success(val numSubscribers:String):MainUIState ()
    data class Error(val sendMensssa:String):MainUIState ()
}