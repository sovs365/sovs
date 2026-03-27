package com.university.voting.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.university.voting.databinding.ActivityLoginVerifyCodeBinding
import com.university.voting.viewmodel.AuthViewModel
import java.util.Locale

class LoginVerifyCodeActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginVerifyCodeBinding
    private lateinit var authViewModel: AuthViewModel
    private var userEmail = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginVerifyCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        userEmail = intent.getStringExtra("userEmail")?.trim().orEmpty()

        if (userEmail.isBlank()) {
            Toast.makeText(this, "Missing verification email. Please login again.", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        observeViewModel()

        binding.btnVerify.setOnClickListener {
            verifyLoginCode()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun observeViewModel() {
        authViewModel.loginResult.observe(this) { result ->
            binding.progressBar.visibility = View.GONE
            binding.btnVerify.isEnabled = true

            result.onSuccess { authResponse ->
                Toast.makeText(this@LoginVerifyCodeActivity, "Login successful", Toast.LENGTH_SHORT).show()
                val role = authResponse.user.role.lowercase(Locale.getDefault())
                navigateToDashboard(role)
            }

            result.onFailure { error ->
                Toast.makeText(
                    this@LoginVerifyCodeActivity,
                    "Verification failed: ${extractErrorMessage(error)}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun verifyLoginCode() {
        val enteredCode = binding.etVerifyCode.text.toString().trim()

        if (enteredCode.isEmpty()) {
            Toast.makeText(this, "Please enter the verification code", Toast.LENGTH_SHORT).show()
            return
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.btnVerify.isEnabled = false
        authViewModel.verifyLoginCode(userEmail, enteredCode)
    }

    private fun navigateToDashboard(role: String) {
        val intent = when (role) {
            "admin" -> Intent(this, AdminDashboardActivity::class.java)
            "candidate" -> Intent(this, CandidateDashboardActivity::class.java)
            else -> Intent(this, VoterDashboardActivity::class.java)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun extractErrorMessage(error: Throwable): String {
        val raw = error.message?.trim().orEmpty()
        if (raw.isBlank()) return "Invalid or expired verification code"
        return "\"error\"\\s*:\\s*\"([^\"]+)\"".toRegex().find(raw)?.groupValues?.getOrNull(1) ?: raw
    }
}
