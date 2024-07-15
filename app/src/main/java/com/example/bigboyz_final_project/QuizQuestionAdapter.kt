package com.example.bigboyz_final_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuizQuestionAdapter(private var questions: List<QuestionCard>) : RecyclerView.Adapter<QuizQuestionAdapter.QuestionViewHolder>() {

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionText: TextView = itemView.findViewById(R.id.questionText)
        val answerGroup: RadioGroup = itemView.findViewById(R.id.answerGroup)
        val answer1: RadioButton = itemView.findViewById(R.id.answer1)
        val answer2: RadioButton = itemView.findViewById(R.id.answer2)
        val answer3: RadioButton = itemView.findViewById(R.id.answer3)
        val answer4: RadioButton = itemView.findViewById(R.id.answer4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.begin_quiz, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questions[position]
        holder.questionText.text = question.question
        holder.answer1.text = question.wrongAnswer1
        holder.answer2.text = question.wrongAnswer2
        holder.answer3.text = question.wrongAnswer3
        holder.answer4.text = question.correctAnswer

        holder.answerGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = group.findViewById<RadioButton>(checkedId)
            if (selectedRadioButton.text == question.correctAnswer) {
                // add points to the user's score in class QuizResults
                if (selectedRadioButton.text == question.correctAnswer) {
                    // add points to the user's score in class QuizResults
                    QuizResults.score += 1
                } else {
                    // The selected answer is incorrect. Perform the desired action here.
                    // For example, you might want to show a message to the user
                }
            } else {
                // The selected answer is incorrect. Perform the desired action here.
            }
        }
    }

    override fun getItemCount() = questions.size

    fun updateData(newQuestions: List<QuestionCard>) {
        questions = newQuestions
        notifyDataSetChanged()
    }
}