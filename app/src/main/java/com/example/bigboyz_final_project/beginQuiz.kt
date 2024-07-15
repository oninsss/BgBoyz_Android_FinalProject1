package com.example.bigboyz_final_project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bigboyz_final_project.database_stuff.DatabaseHelper

class beginQuiz : Fragment() {
    private var quizId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            quizId = it.getInt("quizId")
        }
        Log.d("beginQuiz", "onCreate called with quizId: $quizId")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("beginQuiz", "onCreateView called")
        val view = inflater.inflate(R.layout.fragment_begin_quiz, container, false)
        Log.d("beginQuiz", "Inflated view: $view")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("beginQuiz", "onViewCreated called")

        // Initialize RecyclerView and Adapter
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val adapter = QuizQuestionAdapter(mutableListOf())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Load questions from database
        quizId?.let {
            val questions = getQuestionsFromDatabase(it)
            adapter.updateData(questions)
        }

        // Initialize Submit Button and handle click
        val submitButton: Button = view.findViewById(R.id.submit_button)
        submitButton.setOnClickListener {
            // Calculate the score
            val score = QuizResults.score

            // Display the score to the user using an AlertDialog
            AlertDialog.Builder(requireContext()).apply {
                setTitle("Quiz Results")
                setMessage("Your score is $score")
                setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                    parentFragmentManager.popBackStack()
                }
                show()
            }

            // Reset the score for the next quiz
            QuizResults.score = 0
        }
    }

    private fun getQuestionsFromDatabase(quizId: Int): List<QuestionCard> {
        val questions = mutableListOf<QuestionCard>()
        val dbHelper = DatabaseHelper(requireContext())
        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM Question WHERE quizId = ?", arrayOf(quizId.toString()))
        while (cursor.moveToNext()) {
            val questionId = cursor.getInt(cursor.getColumnIndexOrThrow("questionId"))
            val question = cursor.getString(cursor.getColumnIndexOrThrow("question"))
            val wrongAnswer1 = cursor.getString(cursor.getColumnIndexOrThrow("wrongAnswer1"))
            val wrongAnswer2 = cursor.getString(cursor.getColumnIndexOrThrow("wrongAnswer2"))
            val wrongAnswer3 = cursor.getString(cursor.getColumnIndexOrThrow("wrongAnswer3"))
            val correctAnswer = cursor.getString(cursor.getColumnIndexOrThrow("correctAnswer"))

            val questionCard = QuestionCard(
                questionId = questionId,
                quizId = quizId,
                question = question,
                wrongAnswer1 = wrongAnswer1,
                wrongAnswer2 = wrongAnswer2,
                wrongAnswer3 = wrongAnswer3,
                correctAnswer = correctAnswer
            )

            questions.add(questionCard)
        }

        cursor.close()
        db.close()

        return questions
    }

    companion object {
        @JvmStatic
        fun newInstance(quizId: Int) =
            beginQuiz().apply {
                arguments = Bundle().apply {
                    putInt("quizId", quizId)
                }
            }
    }
}
