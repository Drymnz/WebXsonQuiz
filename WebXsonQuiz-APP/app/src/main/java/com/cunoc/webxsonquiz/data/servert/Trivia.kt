package com.cunoc.webxsonquiz.data.servert

import java.io.Serializable

data class Trivia(
    val id: String,
    val name: String,
    val time: Double,
    val theme: String,
    val idUser: String
) : Serializable {
    companion object {
        private const val serialVersionUID = 3L
    }
}
