package com.example.bigboyz_final_project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class QuizAdapter(private var quizzes: List<Record>, private val activity: FragmentActivity) : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quizTitle: TextView = itemView.findViewById(R.id.quiz_title)
        val quizDescription: TextView = itemView.findViewById(R.id.quiz_description)
        val startQuiz: Button = itemView.findViewById(R.id.startQuiz)
    }

    fun updateData(newData: List<Record>) {
        quizzes = newData.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quiz, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val quiz = quizzes[position]
        holder.quizTitle.text = quiz.title
        holder.quizDescription.text = quiz.description

        Log.d("QuizAdapter", "Binding quizId: ${quiz.quizId}")

        holder.startQuiz.setOnClickListener {
            Log.d("QuizAdapter", "Start quiz button clicked for quizId: ${quiz.quizId}")

            // Create a Bundle with the quizId to pass to the fragment
            val bundle = Bundle().apply {
                putInt("quizId", quiz.quizId)
            }

            // Navigate to beginQuiz fragment using Navigation Component
            val navController = holder.itemView.findNavController()
            navController.navigate(R.id.action_navigation_dashboard_to_beginQuiz, bundle)
        }
    }

    override fun getItemCount() = quizzes.size
}
