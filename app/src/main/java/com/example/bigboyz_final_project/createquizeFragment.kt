package com.example.bigboyz_final_project

import QuestionCardAdapter
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bigboyz_final_project.database_stuff.DatabaseHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CreateQuizFragment(private val param1: String = "", private val param2: String = "") : Fragment() {

    private lateinit var questionCards: MutableList<QuestionCard>
    private lateinit var adapter: QuestionCardAdapter
    private lateinit var db: SQLiteDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_createquize, container, false)
        questionCards = mutableListOf(QuestionCard())

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        adapter = QuestionCardAdapter(questionCards, recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        val addButton: FloatingActionButton = view.findViewById(R.id.add_button)
        addButton.setOnClickListener {
            adapter.addQuestionCard(QuestionCard())
        }

        val saveButton: Button = view.findViewById(R.id.save_button)

        // Initialize the database here
        val dbHelper = DatabaseHelper(requireContext())
        db = dbHelper.writableDatabase

        saveButton.setOnClickListener {
            // Save the quiz
            val quizTitle = view.findViewById<EditText>(R.id.quizTitle).text.toString()
            val quizDescription = view.findViewById<EditText>(R.id.quizDescription).text.toString()

            adapter.updateQuestionCards()

            val questions = adapter.getQuestionCards().map {
                QuestionCard(
                    questionId = it.questionId,
                    quizId = it.quizId,
                    question = it.question,
                    wrongAnswer1 = it.wrongAnswer1,
                    wrongAnswer2 = it.wrongAnswer2,
                    wrongAnswer3 = it.wrongAnswer3,
                    correctAnswer = it.correctAnswer
                )
            }

            val quizValues = ContentValues().apply {
                put("title", quizTitle)
                put("description", quizDescription)
            }

            db.insert("quizzes", null, quizValues)

            val quizId = db.rawQuery("SELECT last_insert_rowid()", null).use {
                it.moveToFirst()
                it.getInt(0)
            }

            questions.forEach { questionCard ->
                val questionValues = ContentValues().apply {
                    put("quizId", quizId)
                    put("question", questionCard.question)
                    put("wrongAnswer1", questionCard.wrongAnswer1)
                    put("wrongAnswer2", questionCard.wrongAnswer2)
                    put("wrongAnswer3", questionCard.wrongAnswer3)
                    put("correctAnswer", questionCard.correctAnswer)
                }

                db.insert("Question", null, questionValues)
            }

            // Clear the questionCards list and notify the adapter
            questionCards.clear()
            adapter.notifyDataSetChanged()

            // Display a Toast message
            Toast.makeText(context, "Data successfully saved", Toast.LENGTH_SHORT).show()

            // Navigate back to the previous fragment
            parentFragmentManager.popBackStack()
        }

        return view
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        // Close the database here
//        db.close()
//    }
}