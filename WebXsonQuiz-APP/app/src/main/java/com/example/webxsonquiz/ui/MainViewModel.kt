package com.example.webxsonquiz.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webxsonquiz.data.ServertRepositoy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<MainUIState>(MainUIState.Loging)
    val uiState: StateFlow<MainUIState> = _uiState

    fun coneccion (login:String){
        val servertRepositoy = ServertRepositoy(login)
        viewModelScope.launch {
            servertRepositoy.counter
                //.map { it.toString() }//modificar
                //.onEach { usar(it) }// usar el dato
                .catch {
                    _uiState.value = MainUIState.Error(it.message.orEmpty())
                }
                //Dispatchers.Main hilo principal
                //Dispatchers.IO hilo secundario
                .flowOn(Dispatchers.IO)
                .collect{
                // esto lo esta imprimiendo en log
                _uiState.value = MainUIState.Success(it.toString())
            }
        }
    }

    private fun usar(it: String) {

    }

}