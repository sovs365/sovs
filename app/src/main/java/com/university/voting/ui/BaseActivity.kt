package com.university.voting.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.university.voting.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        applyAppSettings()
        super.onCreate(savedInstanceState)
    }

    private fun applyAppSettings() {
        val sharedPref = getSharedPreferences("app_settings", Context.MODE_PRIVATE)
        val fontSizeIdx = sharedPref.getInt("font_size_idx", 1) // 0:Small, 1:Medium, 2:Large
        val fontTypeIdx = sharedPref.getInt("font_type_idx", 0) // 0:Sans, 1:Serif, 2:Mono

        // We combine the font size and font type into one theme or apply them sequentially
        // For simplicity, we'll use a mapping to themes that define these
        
        val themeRes = when {
            fontSizeIdx == 0 -> R.style.Theme_UniversityVotingApp_Small
            fontSizeIdx == 2 -> R.style.Theme_UniversityVotingApp_Large
            else -> R.style.Theme_UniversityVotingApp_Medium
        }
        setTheme(themeRes)

        // Set typeface globally or apply another theme layer if needed.
        // For this demo, the primary theme handles the font size.
    }
}
