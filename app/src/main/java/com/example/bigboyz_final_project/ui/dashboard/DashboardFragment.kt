package com.example.bigboyz_final_project.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bigboyz_final_project.QuizAdapter
import com.example.bigboyz_final_project.R
import com.example.bigboyz_final_project.databinding.FragmentDashboardBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.bigboyz_final_project.Record
import com.example.bigboyz_final_project.database_stuff.DatabaseHelper

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: QuizAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val fab: FloatingActionButton = root.findViewById(R.id.createQuiz)
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_createQuizFragment)
        }

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        val quizzes = getQuizzesFromDatabase()
        adapter = QuizAdapter(quizzes, requireActivity())
        recyclerView.adapter = adapter

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val quizzes = getQuizzesFromDatabase()
        adapter.updateData(quizzes)
    }

    private fun getQuizzesFromDatabase(): List<Record> {
        val quizzes = mutableListOf<Record>()

        val dbHelper = DatabaseHelper(requireContext())
        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery("SELECT quizId, title, description FROM Quizzes", null)
        while (cursor.moveToNext()) {
            val quizId = cursor.getInt(cursor.getColumnIndexOrThrow("quizId"))
            val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
            val description = cursor.getString(cursor.getColumnIndexOrThrow("description"))
            quizzes.add(Record(quizId, title, description, quizId))
        }

        cursor.close()
        db.close()

        return quizzes
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
