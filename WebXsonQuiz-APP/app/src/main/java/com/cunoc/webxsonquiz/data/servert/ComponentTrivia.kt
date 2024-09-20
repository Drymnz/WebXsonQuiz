package com.cunoc.webxsonquiz.data.servert

import java.io.Serializable

data class ComponentTrivia(
    val idComponent: String,
    val type: ClassComponent,
    val index: Int,
    val text: String,
    val options: String,
    val row: Int,
    val column: Int,
    val result: String
) : Serializable {
    companion object {
        private const val serialVersionUID = 2L
    }
}
