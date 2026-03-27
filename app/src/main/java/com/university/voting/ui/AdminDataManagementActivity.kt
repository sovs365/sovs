package com.university.voting.ui

import android.app.AlertDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.university.voting.api.CourseResponse
import com.university.voting.api.FacultyResponse
import com.university.voting.api.SubjectCombinationResponse
import com.university.voting.databinding.ActivityAdminDataManagementBinding
import com.university.voting.viewmodel.AdminViewModel

class AdminDataManagementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminDataManagementBinding
    private lateinit var viewModel: AdminViewModel
    
    private var faculties: List<FacultyResponse> = emptyList()
    private var courses: List<CourseResponse> = emptyList()
    private var subjects: List<SubjectCombinationResponse> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDataManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AdminViewModel::class.java)

        setupFacultyManagement()
        setupCourseManagement()
        setupSubjectCombinationManagement()
        
        observeViewModel()
        loadAllData()
    }

    private fun loadAllData() {
        viewModel.loadFaculties()
        viewModel.loadCourses()
        viewModel.loadSubjectCombinations()
    }

    private fun observeViewModel() {
        viewModel.faculties.observe(this) { facultyList ->
            faculties = facultyList
            updateFacultyUI()
        }

        viewModel.courses.observe(this) { courseList ->
            courses = courseList
            updateCourseUI()
        }

        viewModel.subjectCombinations.observe(this) { subjectList ->
            subjects = subjectList
            updateSubjectUI()
        }

        viewModel.message.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar?.visibility = if (isLoading) android.view.View.VISIBLE else android.view.View.GONE
        }
    }

    private fun updateFacultyUI() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, faculties.map { it.name })
        binding.lvFaculties.adapter = adapter
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, faculties.map { it.name })
        binding.spinnerFacultiesForCourses.adapter = spinnerAdapter
    }

    private fun updateCourseUI() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, courses.map { it.name })
        binding.lvCourses.adapter = adapter
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, courses.map { it.name })
        binding.spinnerCoursesForSubjects.adapter = spinnerAdapter
    }

    private fun updateSubjectUI() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, subjects.map { it.name })
        binding.lvSubjectCombinations.adapter = adapter
    }

    // ==================== FACULTY ====================

    private fun setupFacultyManagement() {
        binding.btnAddFaculty.setOnClickListener {
            val name = binding.etFacultyName.text.toString().trim()
            if (name.isNotEmpty()) {
                viewModel.createFaculty(name)
                binding.etFacultyName.text.clear()
            } else {
                Toast.makeText(this, "Please enter faculty name", Toast.LENGTH_SHORT).show()
            }
        }
        
        binding.lvFaculties.setOnItemClickListener { _, _, position, _ ->
            val faculty = faculties[position]
            showActionDialog("Faculty", faculty.name) {
                when(it) {
                    "Edit" -> showEditFacultyDialog(faculty)
                    "Delete" -> viewModel.deleteFaculty(faculty.facultyId)
                }
            }
        }
    }

    private fun showEditFacultyDialog(faculty: FacultyResponse) {
        val et = EditText(this).apply { setText(faculty.name) }
        AlertDialog.Builder(this).setTitle("Edit Faculty").setView(et)
            .setPositiveButton("Update") { _, _ -> 
                val newName = et.text.toString().trim()
                if (newName.isNotEmpty()) {
                    viewModel.updateFaculty(faculty.facultyId, newName)
                } else {
                    Toast.makeText(this, "Faculty name cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null).show()
    }

    private fun loadFaculties() {
        viewModel.loadFaculties()
    }

    // ==================== COURSE ====================

    private fun setupCourseManagement() {
        binding.btnAddCourse.setOnClickListener {
            val name = binding.etCourseName.text.toString().trim()
            val facultyName = binding.spinnerFacultiesForCourses.selectedItem?.toString()
            val facultyId = faculties.find { it.name == facultyName }?.facultyId
            
            if (name.isNotEmpty() && facultyId != null) {
                viewModel.createCourse(facultyId, name)
                binding.etCourseName.text.clear()
            } else {
                Toast.makeText(this, "Please select faculty and enter course name", Toast.LENGTH_SHORT).show()
            }
        }
        
        binding.lvCourses.setOnItemClickListener { _, _, position, _ ->
            val course = courses[position]
            showActionDialog("Course", course.name) {
                when(it) {
                    "Edit" -> showEditCourseDialog(course)
                    "Delete" -> viewModel.deleteCourse(course.courseId)
                }
            }
        }
    }

    private fun showEditCourseDialog(course: CourseResponse) {
        val et = EditText(this).apply { setText(course.name) }
        AlertDialog.Builder(this).setTitle("Edit Course").setView(et)
            .setPositiveButton("Update") { _, _ -> 
                val newName = et.text.toString().trim()
                if (newName.isNotEmpty()) {
                    viewModel.updateCourse(course.courseId, course.facultyId, newName)
                } else {
                    Toast.makeText(this, "Course name cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null).show()
    }

    private fun loadCourses() {
        viewModel.loadCourses()
    }

    // ==================== SUBJECT ====================

    private fun setupSubjectCombinationManagement() {
        binding.btnAddSubjectCombination.setOnClickListener {
            val name = binding.etSubjectCombinationName.text.toString().trim()
            val courseName = binding.spinnerCoursesForSubjects.selectedItem?.toString()
            val courseId = courses.find { it.name == courseName }?.courseId
            
            if (name.isNotEmpty() && courseId != null) {
                viewModel.createSubjectCombination(courseId, name)
                binding.etSubjectCombinationName.text.clear()
            } else {
                Toast.makeText(this, "Please select course and enter subject name", Toast.LENGTH_SHORT).show()
            }
        }
        
        binding.lvSubjectCombinations.setOnItemClickListener { _, _, position, _ ->
            val subject = subjects[position]
            showActionDialog("Subject", subject.name) {
                when(it) {
                    "Edit" -> showEditSubjectDialog(subject)
                    "Delete" -> viewModel.deleteSubjectCombination(subject.id)
                }
            }
        }
    }

    private fun showEditSubjectDialog(subject: SubjectCombinationResponse) {
        val et = EditText(this).apply { setText(subject.name) }
        AlertDialog.Builder(this).setTitle("Edit Subject").setView(et)
            .setPositiveButton("Update") { _, _ -> 
                val newName = et.text.toString().trim()
                if (newName.isNotEmpty()) {
                    viewModel.updateSubjectCombination(subject.id, subject.courseId, newName)
                } else {
                    Toast.makeText(this, "Subject name cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null).show()
    }

    private fun loadSubjectCombinations() {
        viewModel.loadSubjectCombinations()
    }

    private fun showActionDialog(type: String, title: String, onAction: (String) -> Unit) {
        val options = arrayOf("Edit", "Delete")
        AlertDialog.Builder(this).setTitle("$type: $title").setItems(options) { _, which -> onAction(options[which]) }.show()
    }
}
