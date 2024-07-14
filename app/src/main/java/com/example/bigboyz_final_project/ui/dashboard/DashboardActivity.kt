package com.example.bigboyz_final_project.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bigboyz_final_project.R

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        fun isCurrentActivity(activityClass: Class<out AppCompatActivity>): Boolean {
            return this::class.java == activityClass
        }
    }
}