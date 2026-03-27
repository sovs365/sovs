package com.university.voting.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.university.voting.databinding.ActivityRegistrationSuccessBinding

class RegistrationSuccessActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username") ?: ""
        val fullName = intent.getStringExtra("fullName") ?: ""
        val role = intent.getStringExtra("role") ?: ""
        val phone = intent.getStringExtra("phone") ?: ""

        binding.tvUsername.text = username
        binding.tvFullName.text = fullName
        binding.tvRole.text = role
        binding.tvPhone.text = phone

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
