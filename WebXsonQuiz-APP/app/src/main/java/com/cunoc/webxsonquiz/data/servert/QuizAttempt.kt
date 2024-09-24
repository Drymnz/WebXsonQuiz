package com.cunoc.webxsonquiz.data.servert

import java.io.Serializable

data class QuizAttempt(
    var user: String,
    var quizId: String,
    var responseTime: Int,
    var score: Int
) : Serializable {
    companion object {
        private const val serialVersionUID = 4L
    }

    override fun toString(): String {
        return "QuizAttempt(user='$user', quizId='$quizId', responseTime=$responseTime, score=$score)"
    }
}