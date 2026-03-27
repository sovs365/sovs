package com.university.voting.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.university.voting.api.LoginRequest
import com.university.voting.api.RetrofitClient
import com.university.voting.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        binding.tvRegisterLink.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun login() {
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.btnLogin.isEnabled = false

        lifecycleScope.launch {
            try {
                val request = LoginRequest(username, password)
                val response = RetrofitClient.apiService.login(request)

                binding.progressBar.visibility = View.GONE
                binding.btnLogin.isEnabled = true

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        // Credentials authenticated successfully
                        // Transition to email verification
                        val intent = Intent(this@LoginActivity, LoginVerifyCodeActivity::class.java)
                        intent.putExtra("userEmail", body.email)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Login failed: No response from server", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val errorMsg = extractErrorMessage(response.errorBody()?.string())
                    Toast.makeText(this@LoginActivity, "Login failed: $errorMsg", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                binding.progressBar.visibility = View.GONE
                binding.btnLogin.isEnabled = true
                Toast.makeText(this@LoginActivity, "Login error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun extractErrorMessage(raw: String?): String {
        val payload = raw?.trim().orEmpty()
        if (payload.isBlank()) return "Invalid credentials"
        return "\"error\"\\s*:\\s*\"([^\"]+)\"".toRegex().find(payload)?.groupValues?.getOrNull(1) ?: payload
    }
}
