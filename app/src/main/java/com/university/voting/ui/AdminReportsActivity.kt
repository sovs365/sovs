package com.university.voting.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.university.voting.databinding.ActivityAdminReportsBinding
import com.university.voting.repository.VotingRepository
import kotlinx.coroutines.launch
import java.util.Locale

class AdminReportsActivity : BaseActivity() {
    private lateinit var binding: ActivityAdminReportsBinding
    private val repository = VotingRepository()
    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminReportsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        token = sharedPref.getString("token", "") ?: ""
        val role = sharedPref.getString("user_role", "") ?: ""
        if (token.isBlank() || role != "admin") {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding.btnBack.setOnClickListener { finish() }
        loadReports()
    }

    private fun loadReports() {
        lifecycleScope.launch {
            binding.progressBar.visibility = android.view.View.VISIBLE
            repository.getReports(token).onSuccess { reports ->
                binding.tvTotalVoters.text = "Total Voters: ${reports.totalVoters}"
                binding.tvTotalCandidates.text = "Total Candidates: ${reports.totalCandidates}"
                binding.tvMaleVoters.text = "Male Users: ${reports.genderBreakdown.male}"
                binding.tvFemaleVoters.text = "Female Users: ${reports.genderBreakdown.female}"
                binding.tvDisabilityCount.text =
                    "Users with Disability: ${reports.disabilityCount} | Active Elections: ${reports.activeElections} | Total Votes: ${reports.totalVotesCast}"

                val turnoutItems = mutableListOf<String>()
                reports.voterTurnoutPerElection.forEach { turnout ->
                    val percent = String.format(Locale.getDefault(), "%.2f", turnout.turnoutPercentage)
                    turnoutItems.add("${turnout.electionTitle}: ${turnout.totalVoted}/${turnout.totalEligible} voted ($percent%)")
                }
                if (turnoutItems.isEmpty()) {
                    turnoutItems.add("No turnout data available")
                }

                binding.lvTurnout.adapter = ArrayAdapter(
                    this@AdminReportsActivity,
                    android.R.layout.simple_list_item_1,
                    turnoutItems
                )
            }.onFailure { error ->
                Toast.makeText(this@AdminReportsActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.visibility = android.view.View.GONE
        }
    }
}
