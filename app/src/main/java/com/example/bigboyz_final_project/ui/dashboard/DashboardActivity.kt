package com.example.bigboyz_final_project.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.bigboyz_final_project.ProfileActivity
import com.example.bigboyz_final_project.R
import com.example.bigboyz_final_project.WebviewActivity
import com.example.bigboyz_final_project.database_stuff.DatabaseHelper
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private var accountId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        accountId = intent.getStringExtra("ACCOUNT_ID")
        dbHelper = DatabaseHelper(this)

        if (accountId == null) {
            finish()
        } else {
            val username = dbHelper.getUsernameByAccountId(accountId!!)
            if (username != null) {
                val usernameTextView = findViewById<TextView>(R.id.dashboard_username)
                usernameTextView.text = "\uD83D\uDC4B Hi $username,"
            }
        }

        val btn_to_webview = findViewById<CardView>(R.id.btn_to_webview)
        btn_to_webview.setOnClickListener {
            val intent = Intent(this, WebviewActivity::class.java)
            startActivity(intent)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> true
                R.id.nav_quizzes -> {
                    // Navigate to QuizzesActivity or handle as needed
                    true
                }
                R.id.nav_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    intent.putExtra("ACCOUNT_ID", accountId)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }
        bottomNavigationView.selectedItemId = R.id.nav_dashboard
    }
}
