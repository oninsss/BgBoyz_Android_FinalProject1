package com.example.bigboyz_final_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bigboyz_final_project.R
import com.google.android.material.bottomnavigation.BottomNavigationView
class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

    }

    private fun isCurrentActivity(activityClass: Class<out AppCompatActivity>): Boolean {
        return this::class.java == activityClass
    }
}
