package com.example.bigboyz_final_project.database_stuff

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.bigboyz_final_project.Account
import com.example.bigboyz_final_project.Answer
import com.example.bigboyz_final_project.Question
import com.example.bigboyz_final_project.QuestionCard
import com.example.bigboyz_final_project.Record

class DbManagement {
    companion object {
        fun insertAccount(db: SQLiteDatabase, account: Account) {
            val values = ContentValues().apply {
                put(DatabaseHelper.COLUMN_ACCOUNT_ID, account.accountId)
                put(DatabaseHelper.COLUMN_USERNAME, account.username)
                put(DatabaseHelper.COLUMN_EMAIL, account.email)
                put(DatabaseHelper.COLUMN_PASSWORD, account.password)
            }
            db.insert(DatabaseHelper.TABLE_NAME_ACCOUNTS, null, values)
        }

        fun insertQuiz(db: SQLiteDatabase, quiz: Record) {
            val values = ContentValues().apply {
                put(DatabaseHelper.COLUMN_ACCOUNT_REFERENCE, quiz.createdBy)
                put(DatabaseHelper.COLUMN_TITLE, quiz.title)
                put(DatabaseHelper.COLUMN_DESCRIPTION, quiz.description)
            }
            val quizId = db.insert(DatabaseHelper.TABLE_NAME_QUIZZES, null, values)
            val title = db.insert(DatabaseHelper.TABLE_NAME_QUIZZES, null, values)
            val description = db.insert(DatabaseHelper.TABLE_NAME_QUIZZES, null, values)

        }

        fun insertQuestion(db: SQLiteDatabase, question: QuestionCard, quizId: Long) {
            val values = ContentValues().apply {
                put(DatabaseHelper.COLUMN_QUIZ_ID_REFERENCE, quizId)
                put(DatabaseHelper.COLUMN_NAME_QUESTION, question.question)
                put(DatabaseHelper.COLUMN_NAME_WRONGANSWER1, question.wrongAnswer1)
                put(DatabaseHelper.COLUMN_NAME_WRONGANSWER2, question.wrongAnswer2)
                put(DatabaseHelper.COLUMN_NAME_WRONGANSWER3, question.wrongAnswer3)
                put(DatabaseHelper.COLUMN_NAME_CORRECTANSWER, question.correctAnswer)
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
