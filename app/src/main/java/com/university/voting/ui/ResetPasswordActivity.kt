package com.university.voting.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.university.voting.databinding.ActivityResetPasswordBinding
import com.university.voting.repository.VotingRepository
import kotlinx.coroutines.launch

class ResetPasswordActivity : BaseActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private val repository = VotingRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("email")?.trim().orEmpty()
        val code = intent.getStringExtra("code")?.trim().orEmpty()

        if (email.isBlank() || code.isBlank()) {
            Toast.makeText(this, "Invalid reset session. Please start again.", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        binding.btnReset.setOnClickListener {
            val password = binding.etNewPassword.text.toString().trim()
            val confirm = binding.etConfirmPassword.text.toString().trim()

            if (password.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirm) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            updatePassword(email, code, password, confirm)
        }
    }

    private fun updatePassword(email: String, code: String, newPassword: String, confirmPassword: String) {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnReset.isEnabled = false

        lifecycleScope.launch {
            val result = repository.resetPasswordWithCode(email, code, newPassword, confirmPassword)

            binding.progressBar.visibility = View.GONE
            binding.btnReset.isEnabled = true

            result.onSuccess {
                Toast.makeText(this@ResetPasswordActivity, "Password updated successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ResetPasswordActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }

            result.onFailure { error ->
                Toast.makeText(
                    this@ResetPasswordActivity,
                    "Failed: ${extractErrorMessage(error)}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun extractErrorMessage(error: Throwable): String {
        val raw = error.message?.trim().orEmpty()
        if (raw.isBlank()) return "Password reset failed"
        return "\"error\"\\s*:\\s*\"([^\"]+)\"".toRegex().find(raw)?.groupValues?.getOrNull(1) ?: raw
    }
}
