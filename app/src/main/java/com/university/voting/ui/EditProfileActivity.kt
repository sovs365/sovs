package com.university.voting.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.university.voting.R
import com.university.voting.api.UserResponse
import com.university.voting.databinding.ActivityEditProfileBinding
import com.university.voting.repository.VotingRepository
import kotlinx.coroutines.launch

class EditProfileActivity : BaseActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    private val repository = VotingRepository()
    private var token: String = ""
    private var currentUser: UserResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        token = sharedPref.getString("token", "") ?: ""
        val userId = sharedPref.getString("user_id", "") ?: ""

        if (token.isBlank() || userId.isBlank()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        loadUserData()

        binding.btnChangePhoto.setOnClickListener {
            Toast.makeText(
                this,
                "Profile photo upload is not configured on backend yet.",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.btnSave.setOnClickListener {
            saveProfile()
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun loadUserData() {
        lifecycleScope.launch {
            binding.progressBar.visibility = View.VISIBLE
            repository.getCurrentUser(token).onSuccess { user ->
                currentUser = user
                binding.etFullName.setText(user.fullName)
                binding.etEmail.setText(user.email ?: "")
                binding.etPhone.setText(user.phoneNumber)
                binding.ivProfilePhoto.setImageResource(R.drawable.ic_person)

                if (user.role == "candidate") {
                    binding.candidateFields.visibility = View.VISIBLE
                    binding.etManifesto.setText(user.manifesto ?: "")
                } else {
                    binding.candidateFields.visibility = View.GONE
                }
            }.onFailure { error ->
                Toast.makeText(this@EditProfileActivity, "Failed to load profile: ${error.message}", Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun saveProfile() {
        val fullName = binding.etFullName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val manifesto = binding.etManifesto.text.toString().trim()

        if (fullName.isEmpty()) {
            Toast.makeText(this, "Full name is required", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            binding.progressBar.visibility = View.VISIBLE
            val role = currentUser?.role ?: "voter"
            repository.updateCurrentUser(
                token = token,
                fullName = fullName,
                email = email.ifBlank { null },
                phoneNumber = phone.ifBlank { null },
                manifesto = if (role == "candidate") manifesto.ifBlank { null } else null
            ).onSuccess {
                Toast.makeText(this@EditProfileActivity, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                finish()
            }.onFailure { error ->
                Toast.makeText(this@EditProfileActivity, "Failed to update profile: ${error.message}", Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.visibility = View.GONE
        }
    }
}
