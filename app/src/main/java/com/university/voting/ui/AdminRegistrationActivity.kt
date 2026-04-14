package com.university.voting.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.university.voting.api.RegisterRequest
import com.university.voting.api.RetrofitClient
import com.university.voting.databinding.ActivityAdminRegistrationBinding
import kotlinx.coroutines.launch

class AdminRegistrationActivity : BaseActivity() {
    private lateinit var binding: ActivityAdminRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupPasswordVisibilityToggle(binding.etPassword)

        binding.btnRegister.setOnClickListener { registerAdmin() }
        binding.btnBack.setOnClickListener { finish() }
    }

    private fun registerAdmin() {
        val fullName = binding.etFullName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            return
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.btnRegister.isEnabled = false

        val request = RegisterRequest(
            username = username,
            fullName = fullName,
            email = email,
            password = password,
            role = "admin",
            phoneNumber = phone
        )

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.apiService.register(request)
                binding.progressBar.visibility = View.GONE
                binding.btnRegister.isEnabled = true

                if (response.isSuccessful) {
                    Toast.makeText(this@AdminRegistrationActivity, "Admin Registered Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@AdminRegistrationActivity, LoginActivity::class.java))
                    finish()
                } else {
                    val errorMsg = response.errorBody()?.string() ?: "Registration failed"
                    Toast.makeText(this@AdminRegistrationActivity, errorMsg, Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                binding.progressBar.visibility = View.GONE
                binding.btnRegister.isEnabled = true
                Toast.makeText(this@AdminRegistrationActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
