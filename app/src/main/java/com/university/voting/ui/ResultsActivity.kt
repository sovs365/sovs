package com.university.voting.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.university.voting.api.ElectionResponse
import com.university.voting.databinding.ActivityResultsBinding
import com.university.voting.repository.VotingRepository
import kotlinx.coroutines.launch
import java.util.Locale

class ResultsActivity : BaseActivity() {
    private lateinit var binding: ActivityResultsBinding
    private val repository = VotingRepository()
    private var elections: List<ElectionResponse> = emptyList()
    private var token: String = ""
    private var userRole: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        token = sharedPref.getString("token", "") ?: ""
        userRole = (sharedPref.getString("user_role", "") ?: "").lowercase(Locale.getDefault())

        if (token.isBlank()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding.spinnerElections.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position >= 0 && position < elections.size) {
                    loadElectionResults(elections[position].electionId)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        lifecycleScope.launch {
            val canAccess = canAccessLiveResultsForCurrentUser()
            if (!canAccess) {
                Toast.makeText(
                    this@ResultsActivity,
                    "Vote in all open elections before viewing live analysis.",
                    Toast.LENGTH_LONG
                ).show()
                finish()
                return@launch
            }
            loadElections()
        }
    }

    private suspend fun canAccessLiveResultsForCurrentUser(): Boolean {
        if (userRole == "admin") {
            return true
        }

        val openElections = repository.getOpenElections().getOrDefault(emptyList())
        if (openElections.isEmpty()) {
            return false
        }

        var votedCount = 0
        for (election in openElections) {
            repository.checkVoted(token, election.electionId).onSuccess { hasVoted ->
                if (hasVoted) votedCount += 1
            }
        }

        return votedCount == openElections.size
    }

    private fun loadElections() {
        lifecycleScope.launch {
            binding.progressBar.visibility = View.VISIBLE
            repository.getAllElections().onSuccess { loaded ->
                elections = loaded
                val adapter = ArrayAdapter(
                    this@ResultsActivity,
                    android.R.layout.simple_spinner_item,
                    elections.map { it.title }
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerElections.adapter = adapter
            }.onFailure { error ->
                Toast.makeText(this@ResultsActivity, "Failed to load elections: ${error.message}", Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun loadElectionResults(electionId: String) {
        lifecycleScope.launch {
            binding.progressBar.visibility = View.VISIBLE
            try {
                repository.getElectionResults(electionId).onSuccess { response ->
                    val resultsText = mutableListOf<String>()
                    val pieEntries = mutableListOf<PieEntry>()

                    response.positions.forEach { position ->
                        resultsText.add("--- ${position.positionName} ---")
                        position.candidates.forEach { candidate ->
                            val percentStr = String.format(Locale.getDefault(), "%.1f", candidate.percentage)
                            resultsText.add("${candidate.candidateName}: ${candidate.votes} votes ($percentStr%)")
                            pieEntries.add(PieEntry(candidate.votes.toFloat(), "${candidate.candidateName} (${position.positionName})"))
                        }
                        resultsText.add("Total for ${position.positionName}: ${position.totalVotes}")
                        resultsText.add("")
                    }

                    if (resultsText.isEmpty()) {
                        resultsText.add("No results available for this election yet.")
                    }

                    binding.lvResults.adapter = ArrayAdapter(
                        this@ResultsActivity,
                        android.R.layout.simple_list_item_1,
                        resultsText
                    )

                    if (pieEntries.isNotEmpty()) {
                        val dataSet = PieDataSet(pieEntries, "Vote Distribution")
                        dataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()
                        binding.pieChart.data = PieData(dataSet)
                        binding.pieChart.invalidate()
                        binding.pieChart.visibility = View.VISIBLE
                    } else {
                        binding.pieChart.visibility = View.GONE
                    }
                }.onFailure { error ->
                    Toast.makeText(this@ResultsActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            } finally {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}
