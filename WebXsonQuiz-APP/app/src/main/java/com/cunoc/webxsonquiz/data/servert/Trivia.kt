package com.cunoc.webxsonquiz.data.servert

import java.io.Serializable

data class Trivia(
    var id: String,
    var name: String,
    var time: Double,
    var theme: String,
    var idUser: String,
    var date: String
) : Serializable {

    private val structure: ArrayList<ComponentTrivia> = ArrayList()

    fun addComponent(component: ComponentTrivia) {
        structure.add(component)
    }

    fun getListComponent(): ArrayList<ComponentTrivia> = structure

    companion object {
        private const val serialVersionUID: Long = 2L
    }
}