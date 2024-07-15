package com.example.bigboyz_final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bigboyz_final_project.database_stuff.DatabaseHelper
import com.example.bigboyz_final_project.ui.dashboard.DashboardActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dbHelper = DatabaseHelper(this)

        val editTextEmail = findViewById<EditText>(R.id.input_email)
        val editTextPassword = findViewById<EditText>(R.id.input_password)
        val buttonLogin = findViewById<Button>(R.id.btn_login)
        val buttonToSignup = findViewById<Button>(R.id.btn_to_signup)

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val accountId = dbHelper.authenticateUser(email, password)

            if (accountId != null) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, DashboardActivity::class.java).apply {
                    putExtra("ACCOUNT_ID", accountId)
                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        buttonToSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close()
    }
}
