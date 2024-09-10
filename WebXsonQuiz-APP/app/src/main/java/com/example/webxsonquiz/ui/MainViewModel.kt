package com.example.webxsonquiz.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webxsonquiz.data.ServertRepositoy
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {



    val servertRepositoy = ServertRepositoy()
    fun example (){
        viewModelScope.launch {
            servertRepositoy.counter.collect{
                // esto lo esta imprimiendo en log
                Log.i("contador",it.toString())
            }
        }
    }

}