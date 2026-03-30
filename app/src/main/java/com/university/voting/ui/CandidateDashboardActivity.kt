package com.university.voting.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.university.voting.R
import com.university.voting.api.ElectionResponse
import com.university.voting.repository.VotingRepository
import com.university.voting.util.ProfileImageLoader
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CandidateDashboardActivity : BaseActivity() {
    private val repository = VotingRepository()
    private var token: String = ""
    private var availableElections: List<ElectionResponse> = emptyList()

    private lateinit var tvWelcome: TextView
    private lateinit var tvVerificationStatus: TextView
    private lateinit var tvPositionRunning: TextView
    private lateinit var tvManifesto: TextView
    private lateinit var lvElections: ListView
    private lateinit var progressBar: ProgressBar
    private lateinit var ivProfileHeader: ImageView
    private lateinit var ivProfileImage: ImageView
    private lateinit var ivHeaderPhoto: ImageView
    private lateinit var btnViewResults: Button
    private lateinit var btnVote: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate_dashboard)

        val sharedPref = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        token = sharedPref.getString("token", "") ?: ""
        val userId = sharedPref.getString("user_id", "") ?: ""

        if (token.isBlank() || userId.isBlank()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        tvWelcome = findViewById(R.id.tvWelcome)
        tvVerificationStatus = findViewById(R.id.tvVerificationStatus)
        tvPositionRunning = findViewById(R.id.tvPositionRunning)
        tvManifesto = findViewById(R.id.tvManifesto)
        lvElections = findViewById(R.id.lvElections)
        progressBar = findViewById(R.id.progressBar)
        ivProfileHeader = findViewById(R.id.ivProfileHeader)
        ivProfileImage = findViewById(R.id.ivProfileImage)
        ivHeaderPhoto = findViewById(R.id.ivHeaderPhoto)

        btnViewResults = findViewById(R.id.btnViewResults)
        btnVote = findViewById(R.id.btnVote)
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val btnHome = findViewById<ImageView>(R.id.btnHome)
        val btnProfile = findViewById<ImageView>(R.id.btnProfile)
        val btnSettings = findViewById<ImageView>(R.id.btnSettings)

        btnVote.setOnClickListener {
            val firstElection = availableElections.firstOrNull()
            if (firstElection == null) {
                Toast.makeText(this, "No elections available", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, VotingActivity::class.java)
                intent.putExtra("election_id", firstElection.electionId)
                startActivity(intent)
            }
        }

        btnViewResults.setOnClickListener {
            startActivity(Intent(this, ResultsActivity::class.java))
        }

        btnProfile.setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
        }

        btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        btnHome.setOnClickListener {
            loadDashboardData()
        }

        btnLogout.setOnClickListener {
            sharedPref.edit().clear().apply()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        loadDashboardData()
    }

    override fun onResume() {
        super.onResume()
        loadDashboardData()
    }

    private fun loadDashboardData() {
        lifecycleScope.launch {
            progressBar.visibility = View.VISIBLE
            try {
                val userDeferred = async { repository.getCurrentUser(token) }
                val positionsDeferred = async { repository.getAllPositions() }
                val electionsDeferred = async { repository.getOpenElections() }
                val candidatesDeferred = async { repository.getAllCandidates() }

                val user = userDeferred.await().getOrElse {
                    throw IllegalStateException("Failed to load current user")
                }

                val positions = positionsDeferred.await().getOrDefault(emptyList())
                val openElections = electionsDeferred.await().getOrDefault(emptyList())
                val candidates = candidatesDeferred.await().getOrDefault(emptyList())

                tvWelcome.text = "Welcome back, ${user.fullName}"
                tvVerificationStatus.text = "Verification: ${if (user.isVerified) "Verified" else "Pending"}"
                tvManifesto.text = user.manifesto ?: "No manifesto provided"
                ProfileImageLoader.loadInto(ivProfileHeader, user.profilePhotoPath, R.drawable.ic_sov_logo)
                ProfileImageLoader.loadInto(ivProfileImage, user.profilePhotoPath, R.drawable.ic_person)
                ProfileImageLoader.loadInto(ivHeaderPhoto, user.profilePhotoPath, R.drawable.ic_sov_logo)

                val myCandidate = candidates.firstOrNull { it.userId == user.userId }
                val myPositionName = myCandidate?.positionName
                    ?: myCandidate?.positionId?.let { id -> positions.firstOrNull { it.positionId == id }?.name }
                    ?: "Unknown"
                tvPositionRunning.text = "Running for: $myPositionName"

                availableElections = openElections

                val votedElectionIds = mutableSetOf<String>()
                for (election in availableElections) {
                    repository.checkVoted(token, election.electionId).onSuccess { hasVoted ->
                        if (hasVoted) votedElectionIds.add(election.electionId)
                    }
                }

                val canViewLiveResults = availableElections.isNotEmpty() &&
                    votedElectionIds.size == availableElections.size
                btnViewResults.isEnabled = canViewLiveResults
                btnViewResults.alpha = if (canViewLiveResults) 1f else 0.55f
                btnViewResults.text = if (canViewLiveResults) {
                    "📊  View Live Results"
                } else {
                    "📊  Vote In All Elections To View Results"
                }

                btnVote.isEnabled = availableElections.isNotEmpty()
                btnVote.alpha = if (availableElections.isNotEmpty()) 1f else 0.55f

                val items = availableElections.map { "${it.title} (${it.status})" }
                lvElections.adapter = ArrayAdapter(
                    this@CandidateDashboardActivity,
                    android.R.layout.simple_list_item_1,
                    items
                )

                lvElections.setOnItemClickListener { _, _, position, _ ->
                    val election = availableElections.getOrNull(position) ?: return@setOnItemClickListener
                    val intent = Intent(this@CandidateDashboardActivity, VotingActivity::class.java)
                    intent.putExtra("election_id", election.electionId)
                    startActivity(intent)
                }
            } catch (e: Exception) {
                Toast.makeText(this@CandidateDashboardActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                progressBar.visibility = View.GONE
            }
        }
    }
}
