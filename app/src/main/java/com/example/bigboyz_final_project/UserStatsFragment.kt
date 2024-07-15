package com.example.bigboyz_final_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bigboyz_final_project.database_stuff.DatabaseHelper
import com.example.bigboyz_final_project.utils.NotificationUtils

class UserStatsFragment : Fragment() {
    private lateinit var dbHelper: DatabaseHelper
    private var accountId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_stats, container, false)
        accountId = activity?.intent?.getStringExtra("ACCOUNT_ID")
        dbHelper = DatabaseHelper(requireContext())

        NotificationUtils.createNotificationChannel(requireContext())
        NotificationUtils.showNotification(requireContext(), "Quiz Reminder", "Don't forget to complete your daily quiz!")

        return view
    }
}
