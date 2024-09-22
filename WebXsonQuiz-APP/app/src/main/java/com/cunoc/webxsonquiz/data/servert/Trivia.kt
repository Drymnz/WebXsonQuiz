package com.cunoc.webxsonquiz.data.servert

import java.io.Serializable

class Trivia(
    var id: String,
    var name: String,
    var time: Double,
    var theme: String,
    var idUser: String,
    var date: String
) : Serializable {

    companion object {
        private const val serialVersionUID = 3L
    }

    var structure: ArrayList<ComponentTrivia> = ArrayList()

    fun addComponent(component: ComponentTrivia) {
        structure.add(component)
    }

    fun getListComponent(): ArrayList<ComponentTrivia> {
        return structure
    }
}
