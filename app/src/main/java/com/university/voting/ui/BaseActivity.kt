package com.university.voting.ui

import android.content.Context
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

    protected fun setupPasswordVisibilityToggle(vararg passwordFields: EditText) {
        passwordFields.forEach { field ->
            configurePasswordField(field)
        }
    }

    private fun configurePasswordField(field: EditText) {
        var passwordVisible = false
        applyPasswordDrawable(field, passwordVisible)

        field.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP && event.x >= (field.width - field.compoundPaddingEnd)) {
                passwordVisible = !passwordVisible
                val cursorPosition = field.selectionEnd.coerceAtLeast(0)

                field.transformationMethod = if (passwordVisible) {
                    HideReturnsTransformationMethod.getInstance()
                } else {
                    PasswordTransformationMethod.getInstance()
                }

                applyPasswordDrawable(field, passwordVisible)
                field.setSelection(cursorPosition)
                true
            } else {
                false
            }
        }
    }

    private fun applyPasswordDrawable(field: EditText, passwordVisible: Boolean) {
        val drawables = field.compoundDrawablesRelative
        val icon = if (passwordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility
        val endDrawable = ContextCompat.getDrawable(this, icon)

        field.setCompoundDrawablesRelativeWithIntrinsicBounds(
            drawables[0],
            drawables[1],
            endDrawable,
            drawables[3]
        )
    }
}
