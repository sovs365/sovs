package com.university.voting.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.materialswitch.MaterialSwitch
import com.university.voting.R

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val sharedPref = getSharedPreferences("app_settings", Context.MODE_PRIVATE)
        
        val spinnerFont = findViewById<Spinner>(R.id.spinnerFontSize)
        val fontOptions = arrayOf("Small", "Medium", "Large")
        spinnerFont.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, fontOptions)
        spinnerFont.setSelection(sharedPref.getInt("font_size_idx", 1))

        val spinnerFontType = findViewById<Spinner>(R.id.spinnerFontType)
        val fontTypeOptions = arrayOf("Sans Serif", "Serif", "Monospace","Times New Roman", "Arial", "SF Pro Text")
        spinnerFontType.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, fontTypeOptions)
        spinnerFontType.setSelection(sharedPref.getInt("font_type_idx", 0))

        val spinnerIcon = findViewById<Spinner>(R.id.spinnerIconSize)
        val iconOptions = arrayOf("Small", "Medium", "Large")
        spinnerIcon.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, iconOptions)
        spinnerIcon.setSelection(sharedPref.getInt("icon_size_idx", 1))

        val switchNotif = findViewById<MaterialSwitch>(R.id.switchNotifications)
        switchNotif.isChecked = sharedPref.getBoolean("notifications_enabled", true)

        findViewById<View>(R.id.btnBack).setOnClickListener { finish() }

        // Support Buttons with Hidden Contacts
        findViewById<Button>(R.id.btnEmailSupport).setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:helpdesk@sovs.ac.ke")
                putExtra(Intent.EXTRA_SUBJECT, "SOVS Support Request")
            }
            startActivity(Intent.createChooser(intent, "Send Email"))
        }

        findViewById<Button>(R.id.btnSmsSupport).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:0110238870"))
            intent.putExtra("sms_body", "Hello SOVS Support, ")
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnWhatsappSupport).setOnClickListener {
            try {
                // Using the universal whatsapp link format
                val uri = Uri.parse("https://wa.me/254110238870?text=Hello%20SOVS%20Support")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "WhatsApp not installed", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnAppInfo).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("SOVS Information")
                .setMessage("Secure Online Voting System (SOVS)\nVersion: 1.0.0\nDeveloped for University Voting.")
                .setPositiveButton("OK", null)
                .show()
        }

        findViewById<Button>(R.id.btnClearCache).setOnClickListener {
            try {
                cacheDir.deleteRecursively()
                Toast.makeText(this, "Cache cleared successfully", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Failed to clear cache", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnResetSettings).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Reset Settings")
                .setMessage("Are you sure you want to reset all app settings to default?")
                .setPositiveButton("Reset") { _, _ ->
                    sharedPref.edit().clear().apply()
                    recreate()
                    Toast.makeText(this, "Settings reset", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        spinnerFont.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                if (sharedPref.getInt("font_size_idx", 1) != pos) {
                    sharedPref.edit().putInt("font_size_idx", pos).apply()
                    recreate()
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        spinnerFontType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                if (sharedPref.getInt("font_type_idx", 0) != pos) {
                    sharedPref.edit().putInt("font_type_idx", pos).apply()
                    recreate()
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        spinnerIcon.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                sharedPref.edit().putInt("icon_size_idx", pos).apply()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        switchNotif.setOnCheckedChangeListener { _, isChecked ->
            sharedPref.edit().putBoolean("notifications_enabled", isChecked).apply()
        }
    }
}
