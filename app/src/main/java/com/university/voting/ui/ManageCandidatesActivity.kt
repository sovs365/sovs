package com.university.voting.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.university.voting.R
import com.university.voting.api.CandidateResponse
import com.university.voting.databinding.ActivityManageCandidatesBinding
import com.university.voting.repository.VotingRepository
import kotlinx.coroutines.launch

class ManageCandidatesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManageCandidatesBinding
    private lateinit var adapter: CandidatesAdapter
    private val repository = VotingRepository()
    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageCandidatesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        token = sharedPref.getString("token", "") ?: ""
        val role = sharedPref.getString("user_role", "") ?: ""
        if (token.isBlank() || role != "admin") {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        setupRecyclerView()
        loadCandidates()
    }

    private fun setupRecyclerView() {
        adapter = CandidatesAdapter(
            onApprove = { item -> updateVerification(item, true) },
            onDisapprove = { item -> updateVerification(item, false) },
            onDelete = { item -> confirmDelete(item) },
            onDetails = { item -> showDetails(item) }
        )
        binding.rvCandidates.layoutManager = LinearLayoutManager(this)
        binding.rvCandidates.adapter = adapter
    }

    private fun loadCandidates() {
        binding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            repository.getAllCandidates().onSuccess { candidates ->
                adapter.setCandidates(candidates)
            }.onFailure { error ->
                Toast.makeText(this@ManageCandidatesActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun updateVerification(item: CandidateResponse, verified: Boolean) {
        lifecycleScope.launch {
            binding.progressBar.visibility = View.VISIBLE
            repository.verifyCandidate(token, item.candidateId, verified).onSuccess {
                loadCandidates()
            }.onFailure { error ->
                Toast.makeText(this@ManageCandidatesActivity, "Failed: ${error.message}", Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun confirmDelete(item: CandidateResponse) {
        AlertDialog.Builder(this)
            .setTitle("Remove Candidate")
            .setMessage("Remove ${item.fullName} from candidacy?")
            .setPositiveButton("Remove") { _, _ ->
                lifecycleScope.launch {
                    binding.progressBar.visibility = View.VISIBLE
                    repository.deleteCandidate(token, item.candidateId).onSuccess {
                        loadCandidates()
                    }.onFailure { error ->
                        Toast.makeText(this@ManageCandidatesActivity, "Failed: ${error.message}", Toast.LENGTH_SHORT).show()
                    }
                    binding.progressBar.visibility = View.GONE
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showDetails(item: CandidateResponse) {
        val details = """
            Full Name: ${item.fullName}
            Registration No: ${item.regNo ?: "N/A"}
            Faculty: ${item.faculty ?: "N/A"}
            Course: ${item.course ?: "N/A"}
            Position: ${item.positionName ?: "Unknown"}
            Manifesto: ${item.manifesto}
            Verified: ${if (item.isVerified) "Yes" else "No"}
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Candidate Details")
            .setMessage(details)
            .setPositiveButton("Close", null)
            .show()
    }

    inner class CandidatesAdapter(
        private val onApprove: (CandidateResponse) -> Unit,
        private val onDisapprove: (CandidateResponse) -> Unit,
        private val onDelete: (CandidateResponse) -> Unit,
        private val onDetails: (CandidateResponse) -> Unit
    ) : RecyclerView.Adapter<CandidatesAdapter.CandidateViewHolder>() {

        private var candidates: List<CandidateResponse> = emptyList()

        fun setCandidates(newCandidates: List<CandidateResponse>) {
            candidates = newCandidates
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_candidate_admin, parent, false)
            return CandidateViewHolder(view)
        }

        override fun onBindViewHolder(holder: CandidateViewHolder, position: Int) {
            holder.bind(candidates[position])
        }

        override fun getItemCount(): Int = candidates.size

        inner class CandidateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val tvName = view.findViewById<TextView>(R.id.tvCandidateName)
            private val tvPosition = view.findViewById<TextView>(R.id.tvPosition)
            private val tvStatus = view.findViewById<TextView>(R.id.tvStatus)
            private val btnVerify = view.findViewById<Button>(R.id.btnVerify)
            private val btnDisapprove = view.findViewById<Button>(R.id.btnDisapprove)
            private val btnDelete = view.findViewById<Button>(R.id.btnDelete)
            private val btnDetails = view.findViewById<Button>(R.id.btnDetails)

            fun bind(item: CandidateResponse) {
                tvName.text = item.fullName
                tvPosition.text = item.positionName ?: "Unknown Position"

                if (item.isVerified) {
                    tvStatus.text = "Status: Verified"
                    tvStatus.setTextColor(android.graphics.Color.GREEN)
                } else {
                    tvStatus.text = "Status: Pending"
                    tvStatus.setTextColor(android.graphics.Color.YELLOW)
                }

                btnVerify.setOnClickListener { onApprove(item) }
                btnDisapprove.setOnClickListener { onDisapprove(item) }
                btnDelete.setOnClickListener { onDelete(item) }
                btnDetails.setOnClickListener { onDetails(item) }
            }
        }
    }
}
