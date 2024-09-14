package com.example.webxsonquiz.data.servert

import java.io.Serializable

data class User(
    var id: String,
    var password: String,
    var name: String,
    var institution: String,
    var date: String
) : Serializable {
    override fun toString(): String {
        return "ID: $id, password: $password, name: $name, institution: $institution, date: $date"
    }
}
