package com.example.bigboyz_final_project

data class Account(
    var accountId: String,
    var username: String,
    var email: String,
    var password: String
)

data class Record(
    val createdBy: Int,
    val title: String,
    val description: String,
    val quizId: Int
)

data class Question(
    var questionId: Int,
    var quizId: Int,
    var questionText: String,
    var isCorrect: Boolean
) {
    fun getQuestion(): Any {
        return questionText
    }

    fun getQuestionID(): Int {
        return questionId
    }

    // setters

    fun setQuestion(newQuestion: String){
        questionText = newQuestion
    }

    fun setQuestionID(newQuestionID: Int){
        questionId = newQuestionID
    }

    fun setQuizID(newQuizID: Int){
        quizId = newQuizID
    }
}

data class Answer(
    var answerId: Int,
    var questionId: Int,
    var answerText: String,
    var wrongOption1: String,
    var wrongOption2: String,
    var wrongOption3: String,
    var isCorrect: Boolean = false) {

    fun getAnswer(): String {
        return answerText
    }

    fun getAnswerID(): Int {
        return answerId
    }

    fun getQuestionID(): Int {
        return questionId
    }




//    setters

        fun setAnswer(newAnswer: String){
            answerText = newAnswer
        }

        fun setAnswerID(newAnswerID: Int){
            answerId = newAnswerID
        }

        fun setQuestionID(newQuestionID: Int){
            questionId = newQuestionID
        }

}


//question card class
data class QuestionCard(
    var questionId: Int = 0,
    var quizId: Int = 0,
    var question: String = "",
    var wrongAnswer1: String = "",
    var wrongAnswer2: String = "",
    var wrongAnswer3: String = "",
    var correctAnswer: String = ""
) {
    fun getQuestion(): Any {
        return question
    }

    fun getQuestionID(): Int {
        return questionId
    }

    // setters

//    fun setQuestion(newQuestion: String){
//        question = newQuestion
//    }
//
//    fun setQuestionID(newQuestionID: Int){
//        questionId = newQuestionID
//    }
//
//    fun setQuizID(newQuizID: Int){
//        quizId = newQuizID
//    }
}

data class QuizResults(
    var quizId: Int,
    var accountId: Int,
    var score: Int
) {
    companion object {
        var score: Int = 0
    }
}