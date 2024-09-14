package com.cunoc.webxsonquiz.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cunoc.webxsonquiz.data.ServertRepositoy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<MainUIState>(MainUIState.Loging)
    val uiState: StateFlow<MainUIState> = _uiState

    fun connectionToServer (ip:String,port:Int){
        val servertRepositoy = ServertRepositoy(ip,port)
        viewModelScope.launch {
            servertRepositoy.flowSocket
                //.map { it.toString() }//modificar
                //.onEach { usar(it) }// usar el dato
                .catch {
                    _uiState.value = MainUIState.Error(it.message.orEmpty())
                }
                //Dispatchers.Main hilo principal --->>>>> Siempre Activites de vista en el primer hilo
                //Dispatchers.IO hilo secundario
                //Dispatchers.Default n^x
                .flowOn(Dispatchers.IO)
                .collect{
                // esto lo esta imprimiendo en log
                _uiState.value = MainUIState.Success(it)
            }
        }
    }

}