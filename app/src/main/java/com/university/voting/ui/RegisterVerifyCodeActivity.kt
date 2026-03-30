package com.university.voting.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.university.voting.api.RegisterRequest
import com.university.voting.databinding.ActivityRegisterVerifyCodeBinding
import com.university.voting.repository.VotingRepository
import com.university.voting.viewmodel.AuthViewModel
import kotlinx.coroutines.launch
import java.util.Locale

class RegisterVerifyCodeActivity : BaseActivity() {
    private lateinit var binding: ActivityRegisterVerifyCodeBinding
    private lateinit var authViewModel: AuthViewModel
    private val repository = VotingRepository()
    private var userEmail = ""
    private var isCodeVerified = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterVerifyCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        userEmail = intent.getStringExtra("email")?.trim().orEmpty()

        if (userEmail.isBlank()) {
            Toast.makeText(this, "Missing registration email. Please register again.", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        observeViewModel()

        binding.btnVerify.setOnClickListener {
            verifyCode()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun observeViewModel() {
        authViewModel.registerResult.observe(this) { result ->
            result.onSuccess { authResponse ->
                Toast.makeText(this@RegisterVerifyCodeActivity, "Registration successful", Toast.LENGTH_SHORT).show()
                val sharedPref = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                sharedPref.edit()
                    .putString("token", authResponse.token)
                    .putString("user_id", authResponse.user.userId)
                    .putString("user_role", authResponse.user.role)
                    .apply()

                binding.progressBar.visibility = View.GONE
                val role = authResponse.user.role.lowercase(Locale.getDefault())
                val intent = when (role) {
                    "candidate" -> Intent(this, CandidateDashboardActivity::class.java)
                    else -> Intent(this, VoterDashboardActivity::class.java)
                }
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            result.onFailure { error ->
                binding.progressBar.visibility = View.GONE
                binding.btnVerify.isEnabled = true
                Toast.makeText(
                    this@RegisterVerifyCodeActivity,
                    "Registration failed: ${extractErrorMessage(error)}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun verifyCode() {
        if (isCodeVerified) {
            completeRegistration()
            return
        }

        val enteredCode = binding.etVerifyCode.text.toString().trim()

        if (enteredCode.isEmpty()) {
            Toast.makeText(this, "Please enter the verification code", Toast.LENGTH_SHORT).show()
            return
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.btnVerify.isEnabled = false

        lifecycleScope.launch {
            val verifyResult = repository.verifyRegistrationCode(userEmail, enteredCode)
            verifyResult.onSuccess {
                isCodeVerified = true
                completeRegistration()
            }
            verifyResult.onFailure { error ->
                binding.progressBar.visibility = View.GONE
                binding.btnVerify.isEnabled = true
                Toast.makeText(
                    this@RegisterVerifyCodeActivity,
                    "Verification failed: ${extractErrorMessage(error)}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun completeRegistration() {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnVerify.isEnabled = false

        val fullName = intent.getStringExtra("fullName")?.trim()
        val email = intent.getStringExtra("email")?.trim()
        val username = intent.getStringExtra("username")?.trim()
        val password = intent.getStringExtra("password")
        val phone = intent.getStringExtra("phone")?.trim()
        val regNo = intent.getStringExtra("regNo")?.trim()
        val role = intent.getStringExtra("role")?.lowercase(Locale.getDefault()) ?: "voter"
        val faculty = intent.getStringExtra("faculty")?.trim()
        val course = intent.getStringExtra("course")?.trim()
        val subjectCombination = intent.getStringExtra("subjectCombination")?.trim()
        val levelOfStudy = intent.getStringExtra("levelOfStudy")?.trim()
        val yearOfStudy = intent.getStringExtra("yearOfStudy")?.trim()
        val gender = intent.getStringExtra("gender")?.trim()
        val disability = intent.getStringExtra("disability")?.trim()
        val positionId = intent.getStringExtra("positionId")?.trim()
        val manifesto = intent.getStringExtra("manifesto")?.trim()
        val profilePhotoPath = intent.getStringExtra("profilePhotoPath")?.trim()

        if (fullName.isNullOrEmpty() || email.isNullOrEmpty() || username.isNullOrEmpty() || password.isNullOrEmpty() || phone.isNullOrEmpty() || regNo.isNullOrEmpty()) {
            binding.progressBar.visibility = View.GONE
            binding.btnVerify.isEnabled = true
            Toast.makeText(this, "Registration details are incomplete. Please register again.", Toast.LENGTH_LONG).show()
            return
        }

        if (role == "candidate" && (positionId.isNullOrEmpty() || manifesto.isNullOrEmpty())) {
            binding.progressBar.visibility = View.GONE
            binding.btnVerify.isEnabled = true
            Toast.makeText(this, "Candidate details are incomplete. Please register again.", Toast.LENGTH_LONG).show()
            return
        }

        lifecycleScope.launch {
            try {
                val registerRequest = RegisterRequest(
                    username = username,
                    fullName = fullName,
                    email = email,
                    password = password,
                    role = role,
                    phoneNumber = phone,
                    regNo = regNo,
                    faculty = faculty,
                    course = course,
                    subjectCombination = subjectCombination,
                    levelOfStudy = levelOfStudy,
                    yearOfStudy = yearOfStudy?.substringBefore(" ")?.toIntOrNull() ?: 1,
                    gender = gender,
                    disability = disability,
                    manifesto = if (role == "candidate") manifesto else null,
                    positionId = if (role == "candidate") positionId else null,
                    profilePhotoPath = if (role == "candidate") profilePhotoPath else null
                )

                authViewModel.register(registerRequest)
            } catch (e: Exception) {
                binding.progressBar.visibility = View.GONE
                binding.btnVerify.isEnabled = true
                Toast.makeText(
                    this@RegisterVerifyCodeActivity,
                    "Registration failed: ${extractErrorMessage(e)}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun extractErrorMessage(error: Throwable): String {
        val raw = error.message?.trim().orEmpty()
        if (raw.isBlank()) return "Request failed"
        return "\"error\"\\s*:\\s*\"([^\"]+)\"".toRegex().find(raw)?.groupValues?.getOrNull(1) ?: raw
    }
}
