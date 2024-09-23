package com.cunoc.webxsonquiz.data.servert

import java.io.Serializable

data class ComponentTrivia(
    var idComponent: String,
    var type: ClassComponent,
    var index: Int,
    var text: String,
    var options: String,
    var row: Int,
    var column: Int,
    var result: String,
    var idTrivia: String
) : Serializable {

    companion object {
        private const val serialVersionUID: Long = 3L
    }
}