package com.university.voting.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.university.voting.R
import com.university.voting.api.UpdateUserRequest
import com.university.voting.api.UserResponse
import com.university.voting.repository.VotingRepository
import kotlinx.coroutines.launch

class ManageUsersActivity : BaseActivity() {
    private lateinit var listView: ListView
    private lateinit var progressBar: ProgressBar
    private lateinit var cbSelectAllUnverifiedUsers: CheckBox
    private lateinit var btnVerifySelectedUsers: Button
    private val repository = VotingRepository()
    private var users: List<UserResponse> = emptyList()
    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_users)

        val sharedPref = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        token = sharedPref.getString("token", "") ?: ""
        val role = sharedPref.getString("user_role", "") ?: ""
        if (token.isBlank() || role != "admin") {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        listView = findViewById(R.id.listUsers)
        progressBar = findViewById(R.id.progressBar)
        cbSelectAllUnverifiedUsers = findViewById(R.id.cbSelectAllUnverifiedUsers)
        btnVerifySelectedUsers = findViewById(R.id.btnVerifySelectedUsers)
        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        loadUsers()

        listView.setOnItemLongClickListener { _, _, position, _ ->
            if (position in users.indices) {
                showUserActions(users[position])
            }
            true
        }

        cbSelectAllUnverifiedUsers.setOnCheckedChangeListener { _, isChecked ->
            applySelectAllUnverified(isChecked)
        }

        btnVerifySelectedUsers.setOnClickListener {
            verifySelectedUnverifiedUsers()
        }
    }

    private fun loadUsers() {
        progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            try {
                repository.getAllUsers(token).onSuccess { allUsers ->
                    users = allUsers.filter { it.role == "voter" || it.role == "candidate" }
                    val labels = users.map { user ->
                        val verification = if (user.isVerified) "[Verified]" else "[Unverified]"
                        "${user.fullName} (${user.role}) $verification"
                    }
                    listView.adapter = ArrayAdapter(
                        this@ManageUsersActivity,
                        android.R.layout.simple_list_item_multiple_choice,
                        labels
                    )
                    clearSelections()
                }.onFailure { error ->
                    Toast.makeText(this@ManageUsersActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@ManageUsersActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun clearSelections() {
        for (i in users.indices) {
            listView.setItemChecked(i, false)
        }
        if (cbSelectAllUnverifiedUsers.isChecked) {
            cbSelectAllUnverifiedUsers.setOnCheckedChangeListener(null)
            cbSelectAllUnverifiedUsers.isChecked = false
            cbSelectAllUnverifiedUsers.setOnCheckedChangeListener { _, isChecked ->
                applySelectAllUnverified(isChecked)
            }
        }
    }

    private fun applySelectAllUnverified(select: Boolean) {
        for (i in users.indices) {
            val isUnverified = !users[i].isVerified
            listView.setItemChecked(i, select && isUnverified)
        }
    }

    private fun verifySelectedUnverifiedUsers() {
        val checkedPositions: SparseBooleanArray = listView.checkedItemPositions
        val selectedUnverifiedUsers = mutableListOf<UserResponse>()
        for (i in users.indices) {
            if (checkedPositions.get(i) && !users[i].isVerified) {
                selectedUnverifiedUsers.add(users[i])
            }
        }

        if (selectedUnverifiedUsers.isEmpty()) {
            Toast.makeText(this, "No unverified users selected", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            progressBar.visibility = View.VISIBLE
            var successCount = 0
            var failureCount = 0

            for (user in selectedUnverifiedUsers) {
                repository.verifyUser(token, user.userId, true)
                    .onSuccess { successCount += 1 }
                    .onFailure { failureCount += 1 }
            }

            val message = if (failureCount == 0) {
                "Verified $successCount user(s)"
            } else {
                "Verified $successCount user(s), failed $failureCount"
            }
            Toast.makeText(this@ManageUsersActivity, message, Toast.LENGTH_SHORT).show()
            loadUsers()
            progressBar.visibility = View.GONE
        }
    }

    private fun showUserActions(user: UserResponse) {
        val options = mutableListOf("View Details", "Edit Details")
        options.add(if (user.isVerified) "Revoke Verification" else "Approve User")
        if (user.isLocked) options.add("Unlock Account")
        options.add("Delete User")
        options.add("Close")

        AlertDialog.Builder(this)
            .setTitle(user.fullName)
            .setItems(options.toTypedArray()) { _, which ->
                when (options[which]) {
                    "View Details" -> showUserDetails(user)
                    "Edit Details" -> showEditUserDialog(user)
                    "Revoke Verification", "Approve User" -> toggleVerification(user)
                    "Unlock Account" -> unlockUser(user)
                    "Delete User" -> confirmDelete(user)
                }
            }
            .show()
    }

    private fun showUserDetails(user: UserResponse) {
        val details = """
            Full name: ${user.fullName}
            Username: ${user.username}
            RegNo: ${user.regNo ?: "N/A"}
            Role: ${user.role}
            Faculty: ${user.faculty ?: "N/A"}
            Course: ${user.course ?: "N/A"}
            Phone: ${user.phoneNumber}
            Verified: ${if (user.isVerified) "Yes" else "No"}
            Locked: ${if (user.isLocked) "Yes" else "No"}
        """.trimIndent()
        AlertDialog.Builder(this).setTitle("User Details").setMessage(details).setPositiveButton("OK", null).show()
    }

    private fun showEditUserDialog(user: UserResponse) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_user, null)
        val etName = dialogView.findViewById<EditText>(R.id.etFullName)
        val etPhone = dialogView.findViewById<EditText>(R.id.etPhone)
        val etRegNo = dialogView.findViewById<EditText>(R.id.etRegNo)

        etName.setText(user.fullName)
        etPhone.setText(user.phoneNumber)
        etRegNo.setText(user.regNo)

        AlertDialog.Builder(this)
            .setTitle("Edit User")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                lifecycleScope.launch {
                    progressBar.visibility = View.VISIBLE
                    val request = UpdateUserRequest(
                        fullName = etName.text.toString().trim().ifBlank { null },
                        phoneNumber = etPhone.text.toString().trim().ifBlank { null },
                        regNo = etRegNo.text.toString().trim().ifBlank { null }
                    )
                    repository.updateUser(token, user.userId, request).onSuccess {
                        Toast.makeText(this@ManageUsersActivity, "User updated", Toast.LENGTH_SHORT).show()
                        loadUsers()
                    }.onFailure { error ->
                        Toast.makeText(this@ManageUsersActivity, "Failed: ${error.message}", Toast.LENGTH_SHORT).show()
                    }
                    progressBar.visibility = View.GONE
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun toggleVerification(user: UserResponse) {
        lifecycleScope.launch {
            progressBar.visibility = View.VISIBLE
            repository.verifyUser(token, user.userId, !user.isVerified).onSuccess {
                loadUsers()
            }.onFailure { error ->
                Toast.makeText(this@ManageUsersActivity, "Failed: ${error.message}", Toast.LENGTH_SHORT).show()
            }
            progressBar.visibility = View.GONE
        }
    }

    private fun unlockUser(user: UserResponse) {
        lifecycleScope.launch {
            progressBar.visibility = View.VISIBLE
            repository.unlockUser(token, user.userId).onSuccess {
                loadUsers()
            }.onFailure { error ->
                Toast.makeText(this@ManageUsersActivity, "Failed: ${error.message}", Toast.LENGTH_SHORT).show()
            }
            progressBar.visibility = View.GONE
        }
    }

    private fun confirmDelete(user: UserResponse) {
        AlertDialog.Builder(this)
            .setTitle("Confirm Delete")
            .setMessage("Are you sure you want to permanently delete this user?")
            .setPositiveButton("Delete") { _, _ ->
                lifecycleScope.launch {
                    progressBar.visibility = View.VISIBLE
                    repository.deleteUser(token, user.userId).onSuccess {
                        loadUsers()
                    }.onFailure { error ->
                        Toast.makeText(this@ManageUsersActivity, "Failed: ${error.message}", Toast.LENGTH_SHORT).show()
                    }
                    progressBar.visibility = View.GONE
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
