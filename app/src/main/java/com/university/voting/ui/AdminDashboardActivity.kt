package com.university.voting.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.university.voting.R
import com.university.voting.repository.VotingRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AdminDashboardActivity : BaseActivity() {
    private val repository = VotingRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        val sharedPref = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", "") ?: ""
        val userId = sharedPref.getString("user_id", "") ?: ""

        if (token.isBlank() || userId.isBlank()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        findViewById<View>(R.id.cardElections).setOnClickListener {
            startActivity(Intent(this, AdminElectionManagementActivity::class.java))
        }

        findViewById<View>(R.id.cardData).setOnClickListener {
            startActivity(Intent(this, AdminDataManagementActivity::class.java))
        }

        findViewById<View>(R.id.cardCandidates).setOnClickListener {
            startActivity(Intent(this, ManageCandidatesActivity::class.java))
        }

        findViewById<View>(R.id.cardUsers).setOnClickListener {
            startActivity(Intent(this, ManageUsersActivity::class.java))
        }

        findViewById<View>(R.id.cardLiveTracking).setOnClickListener {
            startActivity(Intent(this, ResultsActivity::class.java))
        }

        findViewById<View>(R.id.cardReports).setOnClickListener {
            startActivity(Intent(this, AdminReportsActivity::class.java))
        }

        findViewById<View>(R.id.btnSettings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        findViewById<View>(R.id.btnProfile).setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
        }

        val ivProfileHeader = findViewById<ImageView>(R.id.ivProfileHeader)
        val ivHeaderPhoto = findViewById<ImageView>(R.id.ivHeaderPhoto)

        findViewById<View>(R.id.btnHome).setOnClickListener {
            loadAdminData(token, ivProfileHeader, ivHeaderPhoto)
            Toast.makeText(this, "Dashboard refreshed", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btnLogout).setOnClickListener {
            sharedPref.edit().clear().apply()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        loadAdminData(token, ivProfileHeader, ivHeaderPhoto)
    }

    override fun onResume() {
        super.onResume()
        val sharedPref = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", "") ?: ""
        val ivProfileHeader = findViewById<ImageView>(R.id.ivProfileHeader)
        val ivHeaderPhoto = findViewById<ImageView>(R.id.ivHeaderPhoto)
        if (token.isNotBlank()) {
            loadAdminData(token, ivProfileHeader, ivHeaderPhoto)
        }
    }

    private fun loadAdminData(token: String, ivProfileHeader: ImageView, ivHeaderPhoto: ImageView) {
        val listActive = findViewById<ListView>(R.id.listActiveElections)
        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)

        lifecycleScope.launch {
            try {
                repository.getCurrentUser(token).onSuccess { user ->
                    tvWelcome.text = "Welcome back, ${user.fullName}"
                    ivProfileHeader.setImageResource(R.drawable.ic_sov_logo)
                    ivHeaderPhoto.setImageDrawable(null)
                }.onFailure {
                    tvWelcome.text = "Welcome back, Admin"
                    ivProfileHeader.setImageResource(R.drawable.ic_sov_logo)
                    ivHeaderPhoto.setImageDrawable(null)
                }

                repository.getOpenElections().onSuccess { elections ->
                    val formatter = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())
                    val items = elections.map { election ->
                        "${election.title} - Ends: ${formatter.format(Date(election.endDate))}"
                    }
                    listActive.adapter = ArrayAdapter(
                        this@AdminDashboardActivity,
                        android.R.layout.simple_list_item_1,
                        items
                    )
                }.onFailure {
                    listActive.adapter = ArrayAdapter(
                        this@AdminDashboardActivity,
                        android.R.layout.simple_list_item_1,
                        listOf("Unable to load active elections")
                    )
                }
            } catch (_: Exception) {
                Toast.makeText(this@AdminDashboardActivity, "Error loading dashboard data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
