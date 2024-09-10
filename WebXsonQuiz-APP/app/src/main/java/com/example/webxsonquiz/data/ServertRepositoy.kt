package com.example.webxsonquiz.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ServertRepositoy {
    val counter : Flow<Int> = flow{
        var bombitas = 1
        while (true)
        {
            bombitas++
            //emitir el valor
            emit(bombitas)
            delay(1000)
        }
    }
}