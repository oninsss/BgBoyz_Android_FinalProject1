package com.example.bigboyz_final_project.database_stuff

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.bigboyz_final_project.Account
import com.example.bigboyz_final_project.Answer
import com.example.bigboyz_final_project.Question
import com.example.bigboyz_final_project.Record

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        private const val DATABASE_NAME = "BigBrainz.db"
        private const val DATABASE_VERSION = 2

        // Account Table
        const val TABLE_NAME_ACCOUNTS = "accounts"
        const val COLUMN_ACCOUNT_ID = "account_Id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"

        // Quiz Table
        const val TABLE_NAME_QUIZZES = "quizzes"
        const val COLUMN_QUIZ_ID = "quiz_Id"
        const val COLUMN_ACCOUNT_REFERENCE = "account_Id"
        const val COLUMN_ITEMS = "items"

        // Question Table
        const val TABLE_NAME_QUESTIONS = "questions"
        const val COLUMN_QUESTION_ID = "question_Id"
        const val COLUMN_QUIZ_ID_REFERENCE = "quiz_id"
        const val COLUMN_QUESTION_TEXT = "question_text"

        // Answer Table
        const val TABLE_NAME_ANSWERS = "answers"
        const val COLUMN_ANSWER_ID = "answer_Id"
        const val COLUMN_QUESTION_ID_REFERENCE = "question_id"
        const val COLUMN_ANSWER_TEXT = "answer_text"
        const val COLUMN_IS_CORRECT = "is_correct"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQueryAccounts = "CREATE TABLE $TABLE_NAME_ACCOUNTS (" +
                "$COLUMN_ACCOUNT_ID TEXT PRIMARY KEY, " +
                "$COLUMN_USERNAME TEXT, " +
                "$COLUMN_EMAIL TEXT, " +
                "$COLUMN_PASSWORD TEXT)"
        val createTableQueryQuizzes = "CREATE TABLE $TABLE_NAME_QUIZZES (" +
                "$COLUMN_QUIZ_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_ACCOUNT_REFERENCE TEXT, " +
                "$COLUMN_ITEMS TEXT)"
        val createTableQueryQuestions = "CREATE TABLE $TABLE_NAME_QUESTIONS (" +
                "$COLUMN_QUESTION_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_QUIZ_ID_REFERENCE INTEGER, " +
                "$COLUMN_QUESTION_TEXT TEXT)"
        val createTableQueryAnswers = "CREATE TABLE $TABLE_NAME_ANSWERS (" +
                "$COLUMN_ANSWER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_QUESTION_ID_REFERENCE INTEGER, " +
                "$COLUMN_ANSWER_TEXT TEXT, " +
                "$COLUMN_IS_CORRECT INTEGER)"
        db?.execSQL(createTableQueryAccounts)
        db?.execSQL(createTableQueryQuizzes)
        db?.execSQL(createTableQueryQuestions)
        db?.execSQL(createTableQueryAnswers)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQueryAccounts = "DROP TABLE IF EXISTS $TABLE_NAME_ACCOUNTS"
        val dropTableQueryQuizzes = "DROP TABLE IF EXISTS $TABLE_NAME_QUIZZES"
        val dropTableQueryQuestions = "DROP TABLE IF EXISTS $TABLE_NAME_QUESTIONS"
        val dropTableQueryAnswers = "DROP TABLE IF EXISTS $TABLE_NAME_ANSWERS"
        db?.execSQL(dropTableQueryAccounts)
        db?.execSQL(dropTableQueryQuizzes)
        db?.execSQL(dropTableQueryQuestions)
        db?.execSQL(dropTableQueryAnswers)
        onCreate(db)
    }

    // Insert functions
    fun insertAccount(account: Account) {
        val db = writableDatabase
        DbManagement.insertAccount(db, account)
        db.close()
    }

    fun insertQuiz(quiz: Record) {
        val db = writableDatabase
        DbManagement.insertQuiz(db, quiz)
        db.close()
    }

    fun insertQuestion(question: Question) {
        val db = writableDatabase
        DbManagement.insertQuestion(db, question)
        db.close()
    }

    fun insertAnswer(answer: Answer) {
        val db = writableDatabase
        DbManagement.insertAnswer(db, answer)
        db.close()
    }

}