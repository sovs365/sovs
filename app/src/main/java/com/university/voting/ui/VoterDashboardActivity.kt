package com.university.voting.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.university.voting.R
import com.university.voting.api.CourseResponse
import com.university.voting.api.ElectionResponse
import com.university.voting.api.FacultyResponse
import com.university.voting.api.PositionResponse
import com.university.voting.api.SubjectCombinationResponse
import com.university.voting.api.UserResponse
import com.university.voting.databinding.ActivityVoterDashboardBinding
import com.university.voting.repository.VotingRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class VoterDashboardActivity : BaseActivity() {
    private lateinit var binding: ActivityVoterDashboardBinding
    private val repository = VotingRepository()
    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoterDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        token = sharedPref.getString("token", "") ?: ""
        val userId = sharedPref.getString("user_id", "") ?: ""

        if (token.isBlank() || userId.isBlank()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding.rvElections.layoutManager = LinearLayoutManager(this)

        binding.btnLogout.setOnClickListener {
            sharedPref.edit().clear().apply()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        binding.btnViewLiveAnalysis.setOnClickListener {
            startActivity(Intent(this, ResultsActivity::class.java))
        }

        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        binding.btnHome.setOnClickListener {
            loadElections()
        }

        loadElections()
    }

    override fun onResume() {
        super.onResume()
        loadElections()
    }

    private fun loadElections() {
        lifecycleScope.launch {
            binding.progressBar.visibility = View.VISIBLE
            try {
                val userDeferred = async { repository.getCurrentUser(token) }
                val positionsDeferred = async { repository.getAllPositions() }
                val facultiesDeferred = async { repository.getFaculties() }
                val coursesDeferred = async { repository.getCourses() }
                val subjectsDeferred = async { repository.getSubjectCombinations() }
                val electionsDeferred = async { repository.getOpenElections() }

                val user = userDeferred.await().getOrElse {
                    throw IllegalStateException("Failed to load current user")
                }
                val positions = positionsDeferred.await().getOrDefault(emptyList())
                val faculties = facultiesDeferred.await().getOrDefault(emptyList())
                val courses = coursesDeferred.await().getOrDefault(emptyList())
                val subjects = subjectsDeferred.await().getOrDefault(emptyList())
                val openElections = electionsDeferred.await().getOrDefault(emptyList())

                binding.tvWelcome.text = "Welcome back, ${user.fullName}"
                binding.ivProfileHeader.setImageResource(R.drawable.ic_sov_logo)

                val positionMap = positions.associateBy { it.positionId }
                val facultyMap = faculties.associateBy { it.facultyId }
                val courseMap = courses.associateBy { it.courseId }
                val subjectMap = subjects.associateBy { it.id }

                val filteredElections = openElections.filter { election ->
                    isElectionEligible(
                        user = user,
                        position = positionMap[election.positionId],
                        faculties = facultyMap,
                        courses = courseMap,
                        subjects = subjectMap
                    )
                }

                val votedElectionIds = mutableSetOf<String>()
                for (election in filteredElections) {
                    repository.checkVoted(token, election.electionId).onSuccess { hasVoted ->
                        if (hasVoted) votedElectionIds.add(election.electionId)
                    }
                }

                binding.noticeCard.visibility = View.VISIBLE
                binding.tvElectionNotice.text = if (filteredElections.isNotEmpty()) {
                    "Notice: ${filteredElections.size} election(s) are open for you!"
                } else {
                    "No open elections at this time."
                }

                binding.btnViewLiveAnalysis.visibility =
                    if (votedElectionIds.isNotEmpty()) View.VISIBLE else View.GONE

                val adapter = ElectionAdapter(filteredElections, votedElectionIds) { election ->
                    val intent = Intent(this@VoterDashboardActivity, VotingActivity::class.java)
                    intent.putExtra("election_id", election.electionId)
                    startActivity(intent)
                }
                binding.rvElections.adapter = adapter
            } catch (e: Exception) {
                Toast.makeText(this@VoterDashboardActivity, "Error loading elections: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun isElectionEligible(
        user: UserResponse,
        position: PositionResponse?,
        faculties: Map<String, FacultyResponse>,
        courses: Map<String, CourseResponse>,
        subjects: Map<String, SubjectCombinationResponse>
    ): Boolean {
        if (position == null) return false

        val userYear = user.yearOfStudy
        val positionYear = yearLabelToInt(position.yearOfStudy)
        val yearMatches = positionYear == null || userYear == null || positionYear == userYear
        if (!yearMatches) return false

        return when (position.type.lowercase(Locale.getDefault())) {
            "general" -> true
            "faculty" -> {
                val facultyName = position.facultyId?.let { faculties[it]?.name }
                facultyName.isNullOrBlank() || facultyName.equals(user.faculty, ignoreCase = true)
            }
            "course" -> {
                val courseName = position.courseId?.let { courses[it]?.name }
                courseName.isNullOrBlank() || courseName.equals(user.course, ignoreCase = true)
            }
            "subject_combination" -> {
                val subjectName = position.subjectCombinationId?.let { subjects[it]?.name }
                subjectName.isNullOrBlank() || subjectName.equals(user.subjectCombination, ignoreCase = true)
            }
            else -> true
        }
    }

    private fun yearLabelToInt(label: String?): Int? {
        val value = label?.trim()?.lowercase(Locale.getDefault()) ?: return null
        return when {
            value.contains("first") -> 1
            value.contains("second") -> 2
            value.contains("third") -> 3
            value.contains("fourth") -> 4
            else -> value.filter { it.isDigit() }.toIntOrNull()
        }
    }

    inner class ElectionAdapter(
        private val elections: List<ElectionResponse>,
        private val votedIds: Set<String>,
        private val onVote: (ElectionResponse) -> Unit
    ) : RecyclerView.Adapter<ElectionAdapter.ViewHolder>() {

        private val dateFormatter = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvTitle: TextView = view.findViewById(R.id.tvElectionTitle)
            val tvDate: TextView = view.findViewById(R.id.tvElectionDate)
            val tvStatus: TextView = view.findViewById(R.id.tvVoteStatus)
            val btnVote: Button = view.findViewById(R.id.btnVote)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_election_voter, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val election = elections[position]
            holder.tvTitle.text = election.title
            holder.tvDate.text = "${dateFormatter.format(Date(election.startDate))} - ${dateFormatter.format(Date(election.endDate))}"

            val hasVoted = votedIds.contains(election.electionId)
            if (hasVoted) {
                holder.tvStatus.text = "You have voted"
                holder.tvStatus.setTextColor(resources.getColor(android.R.color.holo_green_dark, null))
                holder.btnVote.isEnabled = false
                holder.btnVote.text = "Voted"
                holder.btnVote.setOnClickListener(null)
            } else {
                holder.tvStatus.text = "Not yet voted"
                holder.tvStatus.setTextColor(resources.getColor(android.R.color.holo_orange_dark, null))
                holder.btnVote.isEnabled = true
                holder.btnVote.text = "Vote Now"
                holder.btnVote.setOnClickListener { onVote(election) }
            }
        }

        override fun getItemCount() = elections.size
    }
}
