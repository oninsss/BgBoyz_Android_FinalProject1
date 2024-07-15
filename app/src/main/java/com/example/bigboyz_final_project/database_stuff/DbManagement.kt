package com.example.bigboyz_final_project.database_stuff

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.bigboyz_final_project.Account
import com.example.bigboyz_final_project.Answer
import com.example.bigboyz_final_project.Question
import com.example.bigboyz_final_project.Record

class DbManagement {
    companion object {
        fun insertAccount(db: SQLiteDatabase, account: Account): Boolean {
            val values = ContentValues().apply {
                put(DatabaseHelper.COLUMN_USERNAME, account.username)
                put(DatabaseHelper.COLUMN_EMAIL, account.email)
                put(DatabaseHelper.COLUMN_PASSWORD, account.password)
            }
            val result = db.insert(DatabaseHelper.TABLE_NAME_ACCOUNTS, null, values)
            return result != -1L
        }

        fun insertQuiz(db: SQLiteDatabase, quiz: Record, accountId: String) {
            val values = ContentValues().apply {
                put(DatabaseHelper.COLUMN_ACCOUNT_REFERENCE, accountId)
                put(DatabaseHelper.COLUMN_ITEMS, quiz.questions.toString())
            }
            db.insert(DatabaseHelper.TABLE_NAME_QUIZZES, null, values)
        }

        fun insertQuestion(db: SQLiteDatabase, question: Question) {
            val values = ContentValues().apply {
                put(DatabaseHelper.COLUMN_QUIZ_ID_REFERENCE, question.quizId)
                put(DatabaseHelper.COLUMN_QUESTION_TEXT, question.questionText)
            }
            db.insert(DatabaseHelper.TABLE_NAME_QUESTIONS, null, values)
        }

        fun insertAnswer(db: SQLiteDatabase, answer: Answer) {
            val values = ContentValues().apply {
                put(DatabaseHelper.COLUMN_QUESTION_ID_REFERENCE, answer.questionId)
                put(DatabaseHelper.COLUMN_ANSWER_TEXT, answer.answerText)
                put(DatabaseHelper.COLUMN_IS_CORRECT, answer.isCorrect)
            }
            db.insert(DatabaseHelper.TABLE_NAME_ANSWERS, null, values)
        }
    }
}
