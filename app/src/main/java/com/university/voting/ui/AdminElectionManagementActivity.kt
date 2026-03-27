package com.university.voting.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.university.voting.R
import com.university.voting.api.CreateElectionRequest
import com.university.voting.api.CreatePositionRequest
import com.university.voting.api.CourseResponse
import com.university.voting.api.ElectionResponse
import com.university.voting.api.FacultyResponse
import com.university.voting.api.PositionResponse
import com.university.voting.api.SubjectCombinationResponse
import com.university.voting.repository.VotingRepository
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AdminElectionManagementActivity : BaseActivity() {
    private val repository = VotingRepository()
    private var token: String = ""

    private lateinit var spinnerPositionsForElection: Spinner
    private lateinit var etPositionName: EditText
    private lateinit var etElectionTitle: EditText
    private lateinit var etElectionDesc: EditText
    private lateinit var tvStart: TextView
    private lateinit var tvEnd: TextView
    private lateinit var listPositions: ListView
    private lateinit var listActiveElections: ListView

    private lateinit var rgPositionType: RadioGroup
    private lateinit var facultyCourseSelection: LinearLayout
    private lateinit var spinnerFaculty: Spinner
    private lateinit var spinnerCourse: Spinner
    private lateinit var spinnerSubject: Spinner
    private lateinit var spinnerYear: Spinner
    private lateinit var courseContainer: LinearLayout
    private lateinit var subjectContainer: LinearLayout

    private var startDateMillis: Long = System.currentTimeMillis()
    private var endDateMillis: Long = System.currentTimeMillis() + 24 * 60 * 60 * 1000

    private var faculties: List<FacultyResponse> = emptyList()
    private var courses: List<CourseResponse> = emptyList()
    private var subjects: List<SubjectCombinationResponse> = emptyList()
    private var allPositions: List<PositionResponse> = emptyList()
    private var allElections: List<ElectionResponse> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_election_management)

        val sharedPref = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        token = sharedPref.getString("token", "") ?: ""
        val role = sharedPref.getString("user_role", "") ?: ""
        if (token.isBlank() || role != "admin") {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        etElectionTitle = findViewById(R.id.etElectionTitle)
        etElectionDesc = findViewById(R.id.etElectionDesc)
        val btnCreateElection = findViewById<View>(R.id.btnCreateElection)

        spinnerPositionsForElection = findViewById(R.id.spinnerPositionsForElection)
        etPositionName = findViewById(R.id.etPositionName)
        val btnCreatePosition = findViewById<View>(R.id.btnCreatePosition)

        val btnPickStart = findViewById<View>(R.id.btnPickStart)
        val btnPickEnd = findViewById<View>(R.id.btnPickEnd)
        tvStart = findViewById(R.id.tvStart)
        tvEnd = findViewById(R.id.tvEnd)

        listPositions = findViewById(R.id.listPositions)
        listActiveElections = findViewById(R.id.listActiveElections)

        rgPositionType = findViewById(R.id.rgPositionType)
        facultyCourseSelection = findViewById(R.id.facultyCourseSelection)
        spinnerFaculty = findViewById(R.id.spinnerFaculty)
        spinnerCourse = findViewById(R.id.spinnerCourse)
        spinnerSubject = findViewById(R.id.spinnerSubject)
        spinnerYear = findViewById(R.id.spinnerYear)
        courseContainer = findViewById(R.id.courseContainer)
        subjectContainer = findViewById(R.id.subjectContainer)

        setupPositionTypeSelection()
        setupSpinners()
        updateStartEndLabels()

        btnPickStart.setOnClickListener {
            pickDateTime(startDateMillis) { ms ->
                startDateMillis = ms
                updateStartEndLabels()
            }
        }

        btnPickEnd.setOnClickListener {
            pickDateTime(endDateMillis) { ms ->
                endDateMillis = ms
                updateStartEndLabels()
            }
        }

        btnCreateElection.setOnClickListener {
            createElection()
        }

        btnCreatePosition.setOnClickListener {
            savePosition()
        }

        loadAllPositions()
        loadActiveElections()
    }

    private fun setupPositionTypeSelection() {
        rgPositionType.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbGeneral -> {
                    facultyCourseSelection.visibility = View.GONE
                }
                R.id.rbFaculty -> {
                    facultyCourseSelection.visibility = View.VISIBLE
                    courseContainer.visibility = View.GONE
                    subjectContainer.visibility = View.GONE
                }
                R.id.rbCourse -> {
                    facultyCourseSelection.visibility = View.VISIBLE
                    courseContainer.visibility = View.VISIBLE
                    subjectContainer.visibility = View.GONE
                }
                R.id.rbSubject -> {
                    facultyCourseSelection.visibility = View.VISIBLE
                    courseContainer.visibility = View.VISIBLE
                    subjectContainer.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupSpinners() {
        val years = arrayOf("First Year", "Second Year", "Third Year", "Fourth Year")
        spinnerYear.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)

        spinnerFaculty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedFaculty = faculties.getOrNull(position) ?: return
                loadCoursesForFaculty(selectedFaculty.facultyId)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        spinnerCourse.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCourse = courses.getOrNull(position) ?: return
                loadSubjectsForCourse(selectedCourse.courseId)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        loadFaculties()
    }

    private fun loadFaculties() {
        lifecycleScope.launch {
            repository.getFaculties().onSuccess { list ->
                faculties = list
                spinnerFaculty.adapter = ArrayAdapter(
                    this@AdminElectionManagementActivity,
                    android.R.layout.simple_spinner_item,
                    faculties.map { it.name }
                )
            }.onFailure { error ->
                Toast.makeText(this@AdminElectionManagementActivity, "Failed to load faculties: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadCoursesForFaculty(facultyId: String) {
        lifecycleScope.launch {
            repository.getCoursesByFaculty(facultyId).onSuccess { list ->
                courses = list
                spinnerCourse.adapter = ArrayAdapter(
                    this@AdminElectionManagementActivity,
                    android.R.layout.simple_spinner_item,
                    courses.map { it.name }
                )
            }.onFailure { error ->
                Toast.makeText(this@AdminElectionManagementActivity, "Failed to load courses: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadSubjectsForCourse(courseId: String) {
        lifecycleScope.launch {
            repository.getSubjectsByCourse(courseId).onSuccess { list ->
                subjects = list
                spinnerSubject.adapter = ArrayAdapter(
                    this@AdminElectionManagementActivity,
                    android.R.layout.simple_spinner_item,
                    subjects.map { it.name }
                )
            }.onFailure { error ->
                Toast.makeText(this@AdminElectionManagementActivity, "Failed to load subjects: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateStartEndLabels() {
        val sdf = java.text.SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())
        tvStart.text = "Starts: ${sdf.format(Date(startDateMillis))}"
        tvEnd.text = "Ends: ${sdf.format(Date(endDateMillis))}"
    }

    private fun pickDateTime(initial: Long, onPicked: (Long) -> Unit) {
        val cal = Calendar.getInstance()
        cal.timeInMillis = initial
        DatePickerDialog(this, { _, y, m, d ->
            cal.set(Calendar.YEAR, y)
            cal.set(Calendar.MONTH, m)
            cal.set(Calendar.DAY_OF_MONTH, d)
            TimePickerDialog(this, { _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                onPicked(cal.timeInMillis)
            }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun loadAllPositions() {
        lifecycleScope.launch {
            repository.getAllPositions().onSuccess { positions ->
                allPositions = positions
                val labels = allPositions.map { pos ->
                    if (pos.yearOfStudy.isNullOrBlank()) pos.name else "${pos.name} (${pos.yearOfStudy})"
                }
                listPositions.adapter = ArrayAdapter(
                    this@AdminElectionManagementActivity,
                    android.R.layout.simple_list_item_1,
                    labels
                )

                val spinnerAdapter = ArrayAdapter(
                    this@AdminElectionManagementActivity,
                    android.R.layout.simple_spinner_item,
                    labels
                )
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerPositionsForElection.adapter = spinnerAdapter
            }.onFailure { error ->
                Toast.makeText(this@AdminElectionManagementActivity, "Failed to load positions: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun savePosition() {
        val name = etPositionName.text.toString().trim()
        if (name.isEmpty()) {
            Toast.makeText(this, "Enter position name", Toast.LENGTH_SHORT).show()
            return
        }

        val type = when (rgPositionType.checkedRadioButtonId) {
            R.id.rbFaculty -> "faculty"
            R.id.rbCourse -> "course"
            R.id.rbSubject -> "subject_combination"
            else -> "general"
        }

        val selectedFacultyId = faculties.getOrNull(spinnerFaculty.selectedItemPosition)?.facultyId
        val selectedCourseId = courses.getOrNull(spinnerCourse.selectedItemPosition)?.courseId
        val selectedSubjectId = subjects.getOrNull(spinnerSubject.selectedItemPosition)?.id
        val year = if (type == "general") null else spinnerYear.selectedItem?.toString()

        val request = CreatePositionRequest(
            name = name,
            type = type,
            facultyId = if (type == "general") null else selectedFacultyId,
            courseId = if (type == "course" || type == "subject_combination") selectedCourseId else null,
            subjectCombinationId = if (type == "subject_combination") selectedSubjectId else null,
            yearOfStudy = year
        )

        lifecycleScope.launch {
            repository.createPosition(token, request).onSuccess {
                Toast.makeText(this@AdminElectionManagementActivity, "Position registered", Toast.LENGTH_SHORT).show()
                etPositionName.text.clear()
                loadAllPositions()
            }.onFailure { error ->
                Toast.makeText(this@AdminElectionManagementActivity, "Failed: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createElection() {
        val title = etElectionTitle.text.toString().trim()
        val desc = etElectionDesc.text.toString().trim()
        val selectedPos = allPositions.getOrNull(spinnerPositionsForElection.selectedItemPosition)

        if (title.isEmpty() || selectedPos == null) {
            Toast.makeText(this, "Fill all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        val request = CreateElectionRequest(
            title = title,
            description = desc,
            positionId = selectedPos.positionId,
            startDate = startDateMillis,
            endDate = endDateMillis
        )

        lifecycleScope.launch {
            repository.createElection(token, request).onSuccess {
                Toast.makeText(this@AdminElectionManagementActivity, "Election launched", Toast.LENGTH_SHORT).show()
                etElectionTitle.text.clear()
                etElectionDesc.text.clear()
                loadActiveElections()
            }.onFailure { error ->
                Toast.makeText(this@AdminElectionManagementActivity, "Failed: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadActiveElections() {
        lifecycleScope.launch {
            repository.getAllElections().onSuccess { elections ->
                allElections = elections
                val labels = elections.map { "${it.title} (${it.status})" }
                listActiveElections.adapter = ArrayAdapter(
                    this@AdminElectionManagementActivity,
                    android.R.layout.simple_list_item_1,
                    labels
                )
                listActiveElections.setOnItemClickListener { _, _, index, _ ->
                    showElectionOptions(allElections[index])
                }
            }.onFailure { error ->
                Toast.makeText(this@AdminElectionManagementActivity, "Failed to load elections: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showElectionOptions(election: ElectionResponse) {
        val options = arrayOf("Close Election", "Set to Draft", "Open Election", "Cancel")
        android.app.AlertDialog.Builder(this)
            .setTitle(election.title)
            .setItems(options) { _, which ->
                val newStatus = when (which) {
                    0 -> "closed"
                    1 -> "draft"
                    2 -> "open"
                    else -> null
                }
                if (newStatus != null) {
                    lifecycleScope.launch {
                        repository.updateElectionStatus(token, election.electionId, newStatus).onSuccess {
                            loadActiveElections()
                        }.onFailure { error ->
                            Toast.makeText(this@AdminElectionManagementActivity, "Failed: ${error.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            .show()
    }
}
