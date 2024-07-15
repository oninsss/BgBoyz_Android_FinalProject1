package com.example.bigboyz_final_project

data class Account(
    var username: String,
    var email: String,
    var password: String
)

data class Record(
    val quizId: Int,
    val title: String,
    val createdBy: String,
    val questions: List<Question> = emptyList()
)

data class Question(
    val questionId: Int,
    val quizId: Int,
    val questionText: String,
    val isCorrect: Boolean
)

data class Answer(
    val answerId: Int,
    val questionId: Int,
    val answerText: String,
    val isCorrect: Boolean
)