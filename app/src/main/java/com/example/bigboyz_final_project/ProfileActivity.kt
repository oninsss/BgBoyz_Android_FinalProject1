package com.example.bigboyz_final_project

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.bigboyz_final_project.R
import com.example.bigboyz_final_project.database_stuff.DatabaseHelper
import com.example.bigboyz_final_project.ui.dashboard.DashboardActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ProfileActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private var accountId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        accountId = intent.getStringExtra("ACCOUNT_ID")
        dbHelper = DatabaseHelper(this)

        if (accountId == null) {
            finish()
        } else {
            val username = dbHelper.getUsernameByAccountId(accountId!!)
            val email = dbHelper.getEmailByAccountId(accountId!!)
            if (username != null) {
                val usernameTextView = findViewById<TextView>(R.id.profile_username)
                usernameTextView.text = username
                val emailTextView = findViewById<TextView>(R.id.profile_email)
                emailTextView.text = email
            }
        }

        val editProfileButton = findViewById<Button>(R.id.btn_edit_profile)
        editProfileButton.setOnClickListener {
            showUpdateProfileDialog()
        }

        val logoutButton = findViewById<Button>(R.id.btn_logout)
        logoutButton.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> {
                    val intent = Intent(this, DashboardActivity::class.java)
                    intent.putExtra("ACCOUNT_ID", accountId)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_quizzes -> {
                    // Navigate to QuizzesActivity
                    true
                }
                R.id.nav_profile -> true

                else -> false
            }
        }
        bottomNavigationView.selectedItemId = R.id.nav_profile
    }
    private fun showUpdateProfileDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_update_profile, null)

        val usernameEditText: TextInputEditText = dialogView.findViewById(R.id.new_username)
        val emailEditText: TextInputEditText = dialogView.findViewById(R.id.new_email)
        val passwordEditText: TextInputEditText = dialogView.findViewById(R.id.new_password)
        val confirmPasswordEditText: TextInputEditText = dialogView.findViewById(R.id.new_confirm_password)
        val updateProfileBtn: Button = dialogView.findViewById(R.id.btn_update_profile)
        val cancelUpdateBtn: Button = dialogView.findViewById(R.id.btn_cancel_update)

        // Populate the fields with existing data
        val username = dbHelper.getUsernameByAccountId(accountId!!)
        val email = dbHelper.getEmailByAccountId(accountId!!)
        val password = dbHelper.getPasswordByAccountId(accountId!!)  // Assuming you have a method to get the password

        usernameEditText.setText(username)
        emailEditText.setText(email)
        passwordEditText.setText(password)
        confirmPasswordEditText.setText(password)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        updateProfileBtn.setOnClickListener {
            val inputUsername = usernameEditText.text.toString()
            val inputEmail = emailEditText.text.toString()
            val inputPassword = passwordEditText.text.toString()
            val inputConfirmPassword = confirmPasswordEditText.text.toString()

            if (inputPassword != inputConfirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                dbHelper.updateUserProfile(accountId!!, inputUsername, inputEmail, inputPassword)
                findViewById<TextView>(R.id.profile_username).text = inputUsername
                findViewById<TextView>(R.id.profile_email).text = inputEmail

                Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }

        cancelUpdateBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }




    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to log out?")
            .setPositiveButton("Yes") { dialog, id ->
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
}
