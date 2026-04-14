package com.university.voting.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.university.voting.api.LoginRequest
import com.university.voting.api.RetrofitClient
import com.university.voting.api.UserResponse
import com.university.voting.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch
import java.util.Locale

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupPasswordVisibilityToggle(binding.etPassword)

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
                        // Check if admin user (has token directly)
                        if (!body.token.isNullOrEmpty()) {
                            // Admin login - token received directly, skip email verification
                            saveAuthToken(body.token, body.user)
                            val role = body.user.role.lowercase(Locale.getDefault())
                            navigateToDashboard(role)
                        } else {
                            // Regular user - proceed to email verification
                            val intent = Intent(this@LoginActivity, LoginVerifyCodeActivity::class.java)
                            intent.putExtra("userEmail", body.email)
                            startActivity(intent)
                      finish()
                        }
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

    private fun saveAuthToken(token: String, user: UserResponse) {
        val prefs = getSharedPreferences("app_prefs", android.content.Context.MODE_PRIVATE)
        prefs.edit()
            .putString("token", token)
            .putString("user_id", user.userId)
            .putString("user_role", user.role)
            .putString("user_name", user.fullName)
            .putString("username", user.username)
            .apply()
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

    private fun extractErrorMessage(raw: String?): String {
        val payload = raw?.trim().orEmpty()
        if (payload.isBlank()) return "Invalid credentials"
        return "\"error\"\\s*:\\s*\"([^\"]+)\"".toRegex().find(payload)?.groupValues?.getOrNull(1) ?: payload
    }
}
