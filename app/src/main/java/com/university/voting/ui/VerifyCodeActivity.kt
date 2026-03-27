package com.university.voting.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.university.voting.databinding.ActivityVerifyCodeBinding
import com.university.voting.repository.VotingRepository
import kotlinx.coroutines.launch

class VerifyCodeActivity : BaseActivity() {
    private lateinit var binding: ActivityVerifyCodeBinding
    private val repository = VotingRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("email")?.trim().orEmpty()
        if (email.isBlank()) {
            Toast.makeText(this, "Missing email. Please start again.", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        binding.btnVerify.setOnClickListener {
            val enteredCode = binding.etVerifyCode.text.toString().trim()
            if (enteredCode.isBlank()) {
                Toast.makeText(this, "Please enter the verification code", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            verifyResetCode(email, enteredCode)
        }
    }

    private fun verifyResetCode(email: String, code: String) {
        binding.btnVerify.isEnabled = false

        lifecycleScope.launch {
            val result = repository.verifyPasswordResetCode(email, code)
            binding.btnVerify.isEnabled = true

            result.onSuccess {
                val intent = Intent(this@VerifyCodeActivity, ResetPasswordActivity::class.java)
                intent.putExtra("email", email)
                intent.putExtra("code", code)
                startActivity(intent)
                finish()
            }

            result.onFailure { error ->
                Toast.makeText(
                    this@VerifyCodeActivity,
                    "Invalid code: ${extractErrorMessage(error)}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun extractErrorMessage(error: Throwable): String {
        val raw = error.message?.trim().orEmpty()
        if (raw.isBlank()) return "Verification failed"
        return "\"error\"\\s*:\\s*\"([^\"]+)\"".toRegex().find(raw)?.groupValues?.getOrNull(1) ?: raw
    }
}
