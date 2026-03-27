package com.university.voting.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.gridlayout.widget.GridLayout
import androidx.lifecycle.lifecycleScope
import com.google.android.material.imageview.ShapeableImageView
import com.university.voting.R
import com.university.voting.api.CastVoteRequest
import com.university.voting.api.PositionResponse
import com.university.voting.api.PositionVote
import com.university.voting.databinding.ActivityVotingBinding
import com.university.voting.repository.VotingRepository
import kotlinx.coroutines.launch

class VotingActivity : BaseActivity() {
    private lateinit var binding: ActivityVotingBinding
    private val repository = VotingRepository()
    private var electionId: String = ""
    private var token: String = ""

    private val selectedCandidates = mutableMapOf<String, String>() // positionId -> candidateId
    private var positionsInElection: List<PositionResponse> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVotingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        electionId = intent.getStringExtra("election_id") ?: ""
        val sharedPref = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        token = sharedPref.getString("token", "") ?: ""
        val userId = sharedPref.getString("user_id", "") ?: ""

        if (electionId.isBlank() || token.isBlank() || userId.isBlank()) {
            Toast.makeText(this, "Error loading election", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        loadElectionData()

        binding.btnSubmitVote.setOnClickListener {
            submitVotes()
        }
    }

    private fun loadElectionData() {
        lifecycleScope.launch {
            binding.progressBar.visibility = View.VISIBLE
            try {
                repository.getElection(electionId).onSuccess { election ->
                    binding.tvElectionTitle.text = election.title
                }

                positionsInElection = repository.getPositionsForElection(electionId).getOrDefault(emptyList())
                binding.containerPositions.removeAllViews()

                for (position in positionsInElection) {
                    val posView = layoutInflater.inflate(R.layout.item_position_vote, binding.containerPositions, false)
                    val tvPosName = posView.findViewById<TextView>(R.id.tvPositionName)
                    val tvSelectionStatus = posView.findViewById<TextView>(R.id.tvSelectionStatus)
                    val gridCandidates = posView.findViewById<GridLayout>(R.id.gridCandidates)

                    tvPosName.text = position.name
                    tvSelectionStatus.text = "Not Selected"

                    val candidates = repository.getCandidatesForPosition(position.positionId).getOrDefault(emptyList())
                    for (candidate in candidates) {
                        if (!candidate.isVerified) continue

                        val candView = layoutInflater.inflate(R.layout.item_candidate_vote, gridCandidates, false)
                        val ivPhoto = candView.findViewById<ShapeableImageView>(R.id.ivCandidatePhoto)
                        val tvName = candView.findViewById<TextView>(R.id.tvCandidateName)
                        val tvFaculty = candView.findViewById<TextView>(R.id.tvCandidateFaculty)
                        val tvCourse = candView.findViewById<TextView>(R.id.tvCandidateCourse)
                        val cbSelect = candView.findViewById<CheckBox>(R.id.cbSelectCandidate)

                        ivPhoto.setImageResource(R.drawable.ic_person)
                        tvName.text = candidate.fullName
                        tvFaculty.text = candidate.faculty ?: ""
                        tvCourse.text = candidate.course ?: ""

                        cbSelect.setOnCheckedChangeListener { _, isChecked ->
                            if (isChecked) {
                                for (i in 0 until gridCandidates.childCount) {
                                    val otherView = gridCandidates.getChildAt(i)
                                    val otherCb = otherView.findViewById<CheckBox>(R.id.cbSelectCandidate)
                                    if (otherCb != cbSelect) {
                                        otherCb.isChecked = false
                                    }
                                }

                                selectedCandidates[position.positionId] = candidate.candidateId
                                tvSelectionStatus.text = "Selected: ${candidate.fullName}"
                                tvSelectionStatus.setTextColor(resources.getColor(android.R.color.holo_green_dark, null))
                            } else {
                                val selectedCandidateForPos = selectedCandidates[position.positionId]
                                if (selectedCandidateForPos == candidate.candidateId) {
                                    selectedCandidates.remove(position.positionId)
                                    tvSelectionStatus.text = "Not Selected"
                                    tvSelectionStatus.setTextColor(resources.getColor(android.R.color.darker_gray, null))
                                }
                            }
                        }

                        gridCandidates.addView(candView)
                    }

                    binding.containerPositions.addView(posView)
                }
            } catch (e: Exception) {
                Toast.makeText(this@VotingActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun submitVotes() {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnSubmitVote.isEnabled = false

        lifecycleScope.launch {
            try {
                if (positionsInElection.isEmpty()) {
                    Toast.makeText(this@VotingActivity, "No positions found for this election", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                if (selectedCandidates.size < positionsInElection.size) {
                    Toast.makeText(this@VotingActivity, "Please select a candidate for all positions", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                val hasVoted = repository.checkVoted(token, electionId).getOrElse { false }
                if (hasVoted) {
                    Toast.makeText(this@VotingActivity, "You have already voted in this election.", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                val votesList = selectedCandidates.map { (positionId, candidateId) ->
                    PositionVote(positionId = positionId, candidateId = candidateId)
                }

                val voteRequest = CastVoteRequest(electionId = electionId, votes = votesList)
                val result = repository.castVote(token, voteRequest)
                result.onSuccess {
                    Toast.makeText(
                        this@VotingActivity,
                        "Votes cast successfully. You can now view live analysis.",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
                result.onFailure { error ->
                    Toast.makeText(this@VotingActivity, "Voting failed: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@VotingActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.progressBar.visibility = View.GONE
                binding.btnSubmitVote.isEnabled = true
            }
        }
    }
}
