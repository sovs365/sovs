package com.university.voting.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.university.voting.databinding.ActivityForgotPasswordBinding
import com.university.voting.repository.VotingRepository
import kotlinx.coroutines.launch

class ForgotPasswordActivity : BaseActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private val repository = VotingRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSendCode.setOnClickListener {
            val emailInput = binding.etEmail.text.toString().trim()
            if (emailInput.isEmpty()) {
                Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            sendResetCode(emailInput)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun sendResetCode(email: String) {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnSendCode.isEnabled = false

        lifecycleScope.launch {
            val result = repository.sendPasswordResetCode(email)
            binding.progressBar.visibility = View.GONE
            binding.btnSendCode.isEnabled = true

            result.onSuccess {
                Toast.makeText(this@ForgotPasswordActivity, "Verification code sent to $email", Toast.LENGTH_LONG).show()
                val intent = Intent(this@ForgotPasswordActivity, VerifyCodeActivity::class.java)
                intent.putExtra("email", email)
                intent.putExtra("mode", "forgot_password")
                startActivity(intent)
            }

            result.onFailure { error ->
                Toast.makeText(
                    this@ForgotPasswordActivity,
                    "Failed to send code: ${extractErrorMessage(error)}",
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
