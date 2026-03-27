package com.university.voting.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.university.voting.api.CourseResponse
import com.university.voting.api.FacultyResponse
import com.university.voting.api.PositionResponse
import com.university.voting.databinding.ActivityRegistrationBinding
import com.university.voting.repository.VotingRepository
import com.university.voting.viewmodel.AdminViewModel
import kotlinx.coroutines.launch
import java.util.Locale

class RegistrationActivity : BaseActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var viewModel: AdminViewModel
    private var availablePositions: List<PositionResponse> = emptyList()
    private var faculties: List<FacultyResponse> = emptyList()
    private var courses: List<CourseResponse> = emptyList()
    private var isUpdatingRoleSelection = false

    private val REQ_CV = 101
    private val REQ_DECLARATION = 102
    private val REQ_SCHOOL_ID = 103
    private val REQ_ACADEMIC_CLEARANCE = 104
    private val REQ_CODE_OF_CONDUCT = 105
    private val REQ_PASSPORT = 106

    private var cvUri: Uri? = null
    private var declarationUri: Uri? = null
    private var schoolIdUri: Uri? = null
    private var academicClearanceUri: Uri? = null
    private var codeOfConductUri: Uri? = null
    private var passportUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AdminViewModel::class.java)

        setupSpinners()

        binding.cbDisability.setOnCheckedChangeListener { _, isChecked ->
            binding.spinnerDisability.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        setupRoleSelection()

        binding.btnRegister.setOnClickListener { register() }
        binding.tvLoginLink.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }
        binding.btnBack.setOnClickListener { finish() }
        
        binding.tvTerms.setOnClickListener {
            startActivity(Intent(this, TermsConditionsActivity::class.java))
        }

        binding.btnUploadCv.setOnClickListener { pickFile(REQ_CV) }
        binding.btnUploadDeclaration.setOnClickListener { pickFile(REQ_DECLARATION) }
        binding.btnUploadSchoolId.setOnClickListener { pickFile(REQ_SCHOOL_ID) }
        binding.btnUploadAcademicClearance.setOnClickListener { pickFile(REQ_ACADEMIC_CLEARANCE) }
        binding.btnUploadCodeOfConduct.setOnClickListener { pickFile(REQ_CODE_OF_CONDUCT) }
        binding.btnUploadPassport.setOnClickListener { pickFile(REQ_PASSPORT) }

        observeViewModel()
        loadDataFromBackend()
        selectRole(if (binding.rbCandidate.isChecked) "candidate" else "voter")
    }

    private fun observeViewModel() {
        viewModel.faculties.observe(this) { facultyList ->
            faculties = facultyList
            updateFacultySpinner()
        }

        viewModel.courses.observe(this) { courseList ->
            courses = courseList
            updateCourseSpinner()
        }

        viewModel.positions.observe(this) { positionList ->
            availablePositions = positionList
            updatePositionSpinner()
        }
    }

    private fun updateFacultySpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, faculties.map { it.name })
        binding.spinnerFaculty.adapter = adapter

        binding.spinnerFaculty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedFaculty = faculties.getOrNull(position) ?: return
                viewModel.loadCoursesByFaculty(selectedFaculty.facultyId)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun updateCourseSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, courses.map { it.name })
        binding.spinnerCourse.adapter = adapter
    }

    private fun updatePositionSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, availablePositions.map { it.name })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPosition.adapter = adapter
    }

    private fun loadDataFromBackend() {
        viewModel.loadFaculties()
        viewModel.loadAllPositions()
    }

    private fun setupRoleSelection() {
        binding.roleVoterOption.setOnClickListener { selectRole("voter") }
        binding.roleCandidateOption.setOnClickListener { selectRole("candidate") }
        binding.rbVoter.setOnClickListener { selectRole("voter") }
        binding.rbCandidate.setOnClickListener { selectRole("candidate") }
    }

    private fun selectRole(role: String) {
        if (isUpdatingRoleSelection) return

        isUpdatingRoleSelection = true
        val isCandidate = role == "candidate"
        binding.rbVoter.isChecked = !isCandidate
        binding.rbCandidate.isChecked = isCandidate
        updateUiForRole(isCandidate)
        isUpdatingRoleSelection = false
    }

    private fun updateUiForRole(isCandidate: Boolean) {
        binding.voterFieldsContainer.visibility = View.VISIBLE
        binding.candidateContainer.visibility = if (isCandidate) View.VISIBLE else View.GONE
    }

    private fun setupSpinners() {
        // Faculties will be loaded from backend via viewModel
        // Courses will be loaded when faculty is selected

        val levels = arrayOf("Certificate", "Diploma", "Undergraduate", "Postgraduate")
        binding.spinnerLevel.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, levels)

        val years = arrayOf("First Year", "Second Year", "Third Year", "Fourth Year")
        binding.spinnerYear.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)

        val disabilities = arrayOf("Visual", "Hearing", "Walking", "Speaking")
        binding.spinnerDisability.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, disabilities)
    }

    private fun pickFile(requestCode: Int) {
        val fileType = if (requestCode == REQ_PASSPORT) "image/*" else "application/pdf"
        val pickerTitle = if (requestCode == REQ_PASSPORT) "Select passport image" else "Select PDF document"

        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = fileType
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        startActivityForResult(Intent.createChooser(intent, pickerTitle), requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val uri = data.data ?: return
            
            // Validate file type for documents
            val mimeType = contentResolver.getType(uri)
            val fileName = getFileName(uri).lowercase()

            // Check if user is registering as candidate
            val isCandidate = binding.rbCandidate.isChecked

            when (requestCode) {
                REQ_PASSPORT -> {
                    if (!isCandidate) {
                        Toast.makeText(this, "Passport upload is only required for candidates", Toast.LENGTH_SHORT).show()
                        return
                    }

                    // Passport must be an image
                    if (isImageDocument(fileName, mimeType)) {
                        passportUri = uri
                        binding.tvPassportName.text = getFileName(uri)
                    } else {
                        Toast.makeText(this, "Passport must be an image file (JPG, PNG, etc.)", Toast.LENGTH_SHORT).show()
                    }
                }
                REQ_CV, REQ_DECLARATION, REQ_SCHOOL_ID, REQ_ACADEMIC_CLEARANCE, REQ_CODE_OF_CONDUCT -> {
                    if (!isCandidate) {
                        Toast.makeText(this, "Switch to Candidate to upload candidate documents", Toast.LENGTH_SHORT).show()
                        return
                    }

                    // All other candidate documents must be PDF
                    if (isPdfDocument(fileName, mimeType)) {
                        when (requestCode) {
                            REQ_CV -> { cvUri = uri; binding.tvCvName.text = getFileName(uri) }
                            REQ_DECLARATION -> { declarationUri = uri; binding.tvDeclarationName.text = getFileName(uri) }
                            REQ_SCHOOL_ID -> { schoolIdUri = uri; binding.tvSchoolIdName.text = getFileName(uri) }
                            REQ_ACADEMIC_CLEARANCE -> { academicClearanceUri = uri; binding.tvAcademicClearanceName.text = getFileName(uri) }
                            REQ_CODE_OF_CONDUCT -> { codeOfConductUri = uri; binding.tvCodeOfConductName.text = getFileName(uri) }
                        }
                    } else {
                        Toast.makeText(this, "This document must be a PDF file", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun isPdfDocument(fileName: String, mimeType: String?): Boolean {
        val normalizedMimeType = mimeType?.lowercase(Locale.getDefault()).orEmpty()
        return normalizedMimeType == "application/pdf" || fileName.endsWith(".pdf")
    }

    private fun isImageDocument(fileName: String, mimeType: String?): Boolean {
        val normalizedMimeType = mimeType?.lowercase(Locale.getDefault()).orEmpty()
        return normalizedMimeType.startsWith("image/") ||
            fileName.endsWith(".jpg") ||
            fileName.endsWith(".jpeg") ||
            fileName.endsWith(".png") ||
            fileName.endsWith(".webp")
    }

    private fun getFileName(uri: Uri): String {
        var name = ""
        contentResolver.query(uri, null, null, null, null)?.use {
            if (it.moveToFirst()) {
                val index = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (index >= 0) name = it.getString(index)
            }
        }
        return if (name.isEmpty()) uri.lastPathSegment ?: "file" else name
    }

    private fun register() {
        val fullName = binding.etFullName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val regNo = binding.etRegNo.text.toString().trim()

        if (fullName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || phone.isEmpty() || regNo.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (password.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (!binding.cbTerms.isChecked) {
            Toast.makeText(this, "You must accept the terms and conditions", Toast.LENGTH_SHORT).show()
            return
        }

        val facultyName = binding.spinnerFaculty.selectedItem?.toString()
        val selectedFaculty = faculties.find { it.name == facultyName }
        val courseName = binding.spinnerCourse.selectedItem?.toString()
        val selectedCourse = courses.find { it.name == courseName }
        
        val subjectCombination = if (binding.spinnerSubject.visibility == View.VISIBLE) {
            binding.spinnerSubject.selectedItem?.toString()
        } else {
            null
        }
        val levelOfStudy = binding.spinnerLevel.selectedItem?.toString()
        val yearOfStudy = binding.spinnerYear.selectedItem?.toString()
        val gender = if (binding.rbFemale.isChecked) "Female" else "Male"
        val disability = if (binding.cbDisability.isChecked) {
            binding.spinnerDisability.selectedItem?.toString()
        } else {
            null
        }

        val role = when {
            binding.rbCandidate.isChecked -> "candidate"
            else -> "voter"
        }

        var positionId: String? = null
        var manifesto: String? = null

        if (role == "candidate") {
            manifesto = binding.etManifesto.text.toString().trim()
            val selectedPosition = availablePositions.getOrNull(binding.spinnerPosition.selectedItemPosition)

            if (selectedPosition == null) {
                Toast.makeText(this, "Please select the position you are contesting", Toast.LENGTH_SHORT).show()
                return
            }

            if (manifesto.isEmpty() || cvUri == null || declarationUri == null || schoolIdUri == null || academicClearanceUri == null || codeOfConductUri == null || passportUri == null) {
                Toast.makeText(this, "Candidates must upload all required documents and manifesto", Toast.LENGTH_SHORT).show()
                return
            }

            positionId = selectedPosition.positionId
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.btnRegister.isEnabled = false

        // Step 1: Send verification code to email
        lifecycleScope.launch {
            try {
                val repository = VotingRepository()
                val codeResponse = repository.sendRegistrationCode(email)
                
                codeResponse.onSuccess {
                    // Step 2: Navigate to verification screen
                    val intent = Intent(this@RegistrationActivity, RegisterVerifyCodeActivity::class.java)
                    intent.putExtra("fullName", fullName)
                    intent.putExtra("email", email)
                    intent.putExtra("username", username)
                    intent.putExtra("password", password)
                    intent.putExtra("phone", phone)
                    intent.putExtra("regNo", regNo)
                    intent.putExtra("role", role)
                    intent.putExtra("faculty", selectedFaculty?.name)
                    intent.putExtra("course", selectedCourse?.name)
                    intent.putExtra("subjectCombination", subjectCombination)
                    intent.putExtra("levelOfStudy", levelOfStudy)
                    intent.putExtra("yearOfStudy", yearOfStudy)
                    intent.putExtra("gender", gender)
                    intent.putExtra("disability", disability)

                    if (role == "candidate") {
                        intent.putExtra("positionId", positionId)
                        intent.putExtra("manifesto", manifesto)
                    }

                    binding.progressBar.visibility = View.GONE
                    binding.btnRegister.isEnabled = true
                    startActivity(intent)
                    finish()
                }
                
                codeResponse.onFailure { error ->
                    binding.progressBar.visibility = View.GONE
                    binding.btnRegister.isEnabled = true
                    Toast.makeText(this@RegistrationActivity, "Failed to send verification code: ${error.message}", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                binding.progressBar.visibility = View.GONE
                binding.btnRegister.isEnabled = true
                Toast.makeText(this@RegistrationActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
