package com.example.bigboyz_final_project.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bigboyz_final_project.CreateQuizFragment
import com.example.bigboyz_final_project.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val createbutton = findViewById<FloatingActionButton>(R.id.createQuiz)
        createbutton?.setOnClickListener {
            createQuizFragment()
        }
    }

    fun createQuizFragment() {
        val fragment = CreateQuizFragment() // Assuming QuizFragment is a class that extends Fragment

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment_activity_main, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}