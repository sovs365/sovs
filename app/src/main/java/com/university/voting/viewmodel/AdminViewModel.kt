package com.university.voting.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.university.voting.api.*
import com.university.voting.repository.VotingRepository
import kotlinx.coroutines.launch

class AdminViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = VotingRepository()
    private val prefs = application.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    private val _users = MutableLiveData<List<UserResponse>>()
    val users: LiveData<List<UserResponse>> = _users

    private val _faculties = MutableLiveData<List<FacultyResponse>>()
    val faculties: LiveData<List<FacultyResponse>> = _faculties

    private val _courses = MutableLiveData<List<CourseResponse>>()
    val courses: LiveData<List<CourseResponse>> = _courses

    private val _subjectCombinations = MutableLiveData<List<SubjectCombinationResponse>>()
    val subjectCombinations: LiveData<List<SubjectCombinationResponse>> = _subjectCombinations

    private val _positions = MutableLiveData<List<PositionResponse>>()
    val positions: LiveData<List<PositionResponse>> = _positions

    private val _reports = MutableLiveData<ReportsResponse>()
    val reports: LiveData<ReportsResponse> = _reports

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private fun getToken() = prefs.getString("token", "") ?: ""

    // ==================== USERS ====================

    fun loadAllUsers() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            repository.getAllUsers(getToken()).onSuccess {
                _users.postValue(it)
            }.onFailure {
                _message.postValue("Failed to load users: ${it.message}")
            }
            _isLoading.postValue(false)
        }
    }

    fun verifyUser(userId: String, verified: Boolean) {
        viewModelScope.launch {
            repository.verifyUser(getToken(), userId, verified).onSuccess {
                _message.postValue("User verification updated")
                loadAllUsers()
            }.onFailure {
                _message.postValue("Failed: ${it.message}")
            }
        }
    }

    fun deleteUser(userId: String) {
        viewModelScope.launch {
            repository.deleteUser(getToken(), userId).onSuccess {
                _message.postValue("User deleted")
                loadAllUsers()
            }.onFailure {
                _message.postValue("Failed: ${it.message}")
            }
        }
    }

    fun unlockUser(userId: String) {
        viewModelScope.launch {
            repository.unlockUser(getToken(), userId).onSuccess {
                _message.postValue("User account unlocked")
                loadAllUsers()
            }.onFailure {
                _message.postValue("Failed: ${it.message}")
            }
        }
    }

    // ==================== FACULTIES ====================

    fun loadFaculties() {
        viewModelScope.launch {
            repository.getFaculties().onSuccess {
                _faculties.postValue(it)
            }
        }
    }

    fun createFaculty(name: String) {
        viewModelScope.launch {
            repository.createFaculty(getToken(), name).onSuccess {
                _message.postValue("Faculty added")
                loadFaculties()
            }.onFailure {
                _message.postValue("Failed: ${it.message}")
            }
        }
    }

    // ==================== COURSES ====================

    fun loadCourses() {
        viewModelScope.launch {
            repository.getCourses().onSuccess {
                _courses.postValue(it)
            }
        }
    }

    fun loadCoursesByFaculty(facultyId: String) {
        viewModelScope.launch {
            repository.getCoursesByFaculty(facultyId).onSuccess {
                _courses.postValue(it)
            }
        }
    }

    fun createCourse(facultyId: String, name: String) {
        viewModelScope.launch {
            repository.createCourse(getToken(), facultyId, name).onSuccess {
                _message.postValue("Course added")
                loadCourses()
            }.onFailure {
                _message.postValue("Failed: ${it.message}")
            }
        }
    }

    // ==================== SUBJECT COMBINATIONS ====================

    fun loadSubjectCombinations() {
        viewModelScope.launch {
            repository.getSubjectCombinations().onSuccess {
                _subjectCombinations.postValue(it)
            }
        }
    }

    fun createSubjectCombination(courseId: String, name: String) {
        viewModelScope.launch {
            repository.createSubjectCombination(getToken(), courseId, name).onSuccess {
                _message.postValue("Subject combination added")
                loadSubjectCombinations()
            }.onFailure {
                _message.postValue("Failed: ${it.message}")
            }
        }
    }

    // ==================== FACULTY UPDATE/DELETE ====================

    fun updateFaculty(facultyId: String, name: String) {
        viewModelScope.launch {
            repository.updateFaculty(getToken(), facultyId, name).onSuccess {
                _message.postValue("Faculty updated")
                loadFaculties()
            }.onFailure {
                _message.postValue("Failed: ${it.message}")
            }
        }
    }

    fun deleteFaculty(facultyId: String) {
        viewModelScope.launch {
            repository.deleteFaculty(getToken(), facultyId).onSuccess {
                _message.postValue("Faculty deleted")
                loadFaculties()
            }.onFailure {
                _message.postValue("Failed: ${it.message}")
            }
        }
    }

    // ==================== COURSE UPDATE/DELETE ====================

    fun updateCourse(courseId: String, facultyId: String, name: String) {
        viewModelScope.launch {
            repository.updateCourse(getToken(), courseId, facultyId, name).onSuccess {
                _message.postValue("Course updated")
                loadCourses()
            }.onFailure {
                _message.postValue("Failed: ${it.message}")
            }
        }
    }

    fun deleteCourse(courseId: String) {
        viewModelScope.launch {
            repository.deleteCourse(getToken(), courseId).onSuccess {
                _message.postValue("Course deleted")
                loadCourses()
            }.onFailure {
                _message.postValue("Failed: ${it.message}")
            }
        }
    }

    // ==================== SUBJECT UPDATE/DELETE ====================

    fun updateSubjectCombination(subjectId: String, courseId: String, name: String) {
        viewModelScope.launch {
            repository.updateSubjectCombination(getToken(), subjectId, courseId, name).onSuccess {
                _message.postValue("Subject combination updated")
                loadSubjectCombinations()
            }.onFailure {
                _message.postValue("Failed: ${it.message}")
            }
        }
    }

    fun deleteSubjectCombination(subjectId: String) {
        viewModelScope.launch {
            repository.deleteSubjectCombination(getToken(), subjectId).onSuccess {
                _message.postValue("Subject combination deleted")
                loadSubjectCombinations()
            }.onFailure {
                _message.postValue("Failed: ${it.message}")
            }
        }
    }

    // ==================== POSITIONS ====================

    fun loadAllPositions() {
        viewModelScope.launch {
            repository.getAllPositions().onSuccess {
                _positions.postValue(it)
            }
        }
    }

    // ==================== REPORTS ====================

    fun loadReports() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            repository.getReports(getToken()).onSuccess {
                _reports.postValue(it)
            }.onFailure {
                _message.postValue("Failed to load reports: ${it.message}")
            }
            _isLoading.postValue(false)
        }
    }
}
