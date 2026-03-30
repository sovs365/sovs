package com.university.voting.repository

import com.university.voting.api.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VotingRepository {
    private val api = RetrofitClient.apiService

    private fun extractErrorMessage(raw: String?, fallback: String): String {
        val body = raw?.trim().orEmpty()
        if (body.isBlank()) return fallback
        val errorRegex = Regex("\"error\"\\s*:\\s*\"([^\"]+)\"")
        val messageRegex = Regex("\"message\"\\s*:\\s*\"([^\"]+)\"")
        return errorRegex.find(body)?.groupValues?.getOrNull(1)
            ?: messageRegex.find(body)?.groupValues?.getOrNull(1)
            ?: body
    }

    // ==================== AUTH ====================

    suspend fun register(request: RegisterRequest): Result<AuthResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.register(request)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                val errorBody = response.errorBody()?.string() ?: "Registration failed"
                Result.failure(Exception(errorBody))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(request: LoginRequest): Result<LoginCodeResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.login(request)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                val errorBody = response.errorBody()?.string() ?: "Login failed"
                Result.failure(Exception(errorBody))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCurrentUser(token: String): Result<UserResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.getCurrentUser(RetrofitClient.getAuthHeader(token))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.user)
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Failed to fetch current user"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateCurrentUser(
        token: String,
        fullName: String?,
        email: String?,
        phoneNumber: String?,
        manifesto: String?,
        profilePhotoPath: String?
    ): Result<UserResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.updateCurrentUser(
                RetrofitClient.getAuthHeader(token),
                UpdateProfileRequest(
                    fullName = fullName,
                    email = email,
                    phoneNumber = phoneNumber,
                    manifesto = manifesto,
                    profilePhotoPath = profilePhotoPath,
                    profilePhotoBase64 = profilePhotoPath,
                    profileImageBase64 = profilePhotoPath,
                    profileImage = profilePhotoPath
                )
            )
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.user)
            } else {
                Result.failure(Exception(extractErrorMessage(response.errorBody()?.string(), "Failed to update profile")))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun sendPasswordResetCode(email: String): Result<SendCodeResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.sendPasswordResetCode(SendCodeRequest(email))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun resetPassword(email: String, code: String, newPassword: String, confirmPassword: String): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.resetPassword(ResetPasswordRequest(email, code, newPassword, confirmPassword))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ==================== EMAIL VERIFICATION CODES ====================

    suspend fun sendRegistrationCode(email: String): Result<SendCodeResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.sendRegistrationCode(SendCodeRequest(email))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Failed to send code"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun verifyRegistrationCode(email: String, code: String): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.verifyRegistrationCode(VerifyCodeRequest(email, code))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Invalid code"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun verifyLoginCode(email: String, code: String): Result<AuthResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.verifyLoginCode(VerifyCodeRequest(email, code))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Invalid code"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun verifyPasswordResetCode(email: String, code: String): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.verifyPasswordResetCode(VerifyCodeRequest(email, code))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Invalid code"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun resetPasswordWithCode(email: String, code: String, newPassword: String, confirmPassword: String): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.resetPassword(ResetPasswordRequest(email, code, newPassword, confirmPassword))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Failed to reset password"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ==================== ELECTIONS ====================

    suspend fun getAllElections(): Result<List<ElectionResponse>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getAllElections()
            if (response.isSuccessful) Result.success(response.body() ?: emptyList())
            else Result.failure(Exception("Failed to fetch elections"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getOpenElections(): Result<List<ElectionResponse>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getOpenElections()
            if (response.isSuccessful) Result.success(response.body() ?: emptyList())
            else Result.failure(Exception("Failed to fetch open elections"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getElection(electionId: String): Result<ElectionResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.getElection(electionId)
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Election not found"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getElectionResults(electionId: String): Result<ElectionResultsResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.getElectionResults(electionId)
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Results not found"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createElection(token: String, request: CreateElectionRequest): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.createElection(RetrofitClient.getAuthHeader(token), request)
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception(response.errorBody()?.string() ?: "Failed to create election"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateElectionStatus(token: String, electionId: String, status: String): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.updateElectionStatus(RetrofitClient.getAuthHeader(token), electionId, UpdateElectionStatusRequest(status))
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Failed to update election"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ==================== POSITIONS ====================

    suspend fun getAllPositions(): Result<List<PositionResponse>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getAllPositions()
            if (response.isSuccessful) Result.success(response.body() ?: emptyList())
            else Result.failure(Exception("Failed to fetch positions"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPositionsForElection(electionId: String): Result<List<PositionResponse>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getPositionsForElection(electionId)
            if (response.isSuccessful) Result.success(response.body() ?: emptyList())
            else Result.failure(Exception("Failed to fetch positions"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPosition(positionId: String): Result<PositionResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.getPosition(positionId)
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception(response.errorBody()?.string() ?: "Position not found"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createPosition(token: String, request: CreatePositionRequest): Result<PositionResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.createPosition(RetrofitClient.getAuthHeader(token), request)
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Failed to create position"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ==================== CANDIDATES ====================

    suspend fun getAllCandidates(): Result<List<CandidateResponse>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getAllCandidates()
            if (response.isSuccessful) Result.success(response.body() ?: emptyList())
            else Result.failure(Exception("Failed to fetch candidates"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCandidatesForPosition(positionId: String): Result<List<CandidateResponse>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getCandidatesForPosition(positionId)
            if (response.isSuccessful) Result.success(response.body() ?: emptyList())
            else Result.failure(Exception("Failed to fetch candidates"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun verifyCandidate(token: String, candidateId: String, verified: Boolean): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.verifyCandidate(RetrofitClient.getAuthHeader(token), candidateId, VerifyUserRequest(candidateId, verified))
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Failed to verify candidate"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteCandidate(token: String, candidateId: String): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.deleteCandidate(RetrofitClient.getAuthHeader(token), candidateId)
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception(response.errorBody()?.string() ?: "Failed to delete candidate"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ==================== VOTES ====================

    suspend fun castVote(token: String, request: CastVoteRequest): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.castVote(RetrofitClient.getAuthHeader(token), request)
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception(response.errorBody()?.string() ?: "Voting failed"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getVoteReceipts(token: String): Result<List<VoteReceiptResponse>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getVoteReceipts(RetrofitClient.getAuthHeader(token))
            if (response.isSuccessful) Result.success(response.body() ?: emptyList())
            else Result.failure(Exception("Failed to fetch receipts"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun checkVoted(token: String, electionId: String): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            val response = api.checkVoted(RetrofitClient.getAuthHeader(token), electionId)
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!.hasVoted)
            else Result.success(false)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ==================== DATA ====================

    suspend fun getFaculties(): Result<List<FacultyResponse>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getFaculties()
            if (response.isSuccessful) Result.success(response.body() ?: emptyList())
            else Result.failure(Exception("Failed to fetch faculties"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createFaculty(token: String, name: String): Result<FacultyResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.createFaculty(RetrofitClient.getAuthHeader(token), CreateFacultyRequest(name))
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Failed to create faculty"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCourses(): Result<List<CourseResponse>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getCourses()
            if (response.isSuccessful) Result.success(response.body() ?: emptyList())
            else Result.failure(Exception("Failed to fetch courses"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCoursesByFaculty(facultyId: String): Result<List<CourseResponse>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getCoursesByFaculty(facultyId)
            if (response.isSuccessful) Result.success(response.body() ?: emptyList())
            else Result.failure(Exception("Failed to fetch courses"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createCourse(token: String, facultyId: String, name: String): Result<CourseResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.createCourse(RetrofitClient.getAuthHeader(token), CreateCourseRequest(facultyId, name))
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Failed to create course"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getSubjectCombinations(): Result<List<SubjectCombinationResponse>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getSubjectCombinations()
            if (response.isSuccessful) Result.success(response.body() ?: emptyList())
            else Result.failure(Exception("Failed to fetch subjects"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getSubjectsByCourse(courseId: String): Result<List<SubjectCombinationResponse>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getSubjectsByCourse(courseId)
            if (response.isSuccessful) Result.success(response.body() ?: emptyList())
            else Result.failure(Exception("Failed to fetch subjects"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createSubjectCombination(token: String, courseId: String, name: String): Result<SubjectCombinationResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.createSubjectCombination(RetrofitClient.getAuthHeader(token), CreateSubjectCombinationRequest(courseId, name))
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Failed to create subject"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateFaculty(token: String, facultyId: String, name: String): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.updateFaculty(RetrofitClient.getAuthHeader(token), facultyId, CreateFacultyRequest(name))
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Failed to update faculty"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteFaculty(token: String, facultyId: String): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.deleteFaculty(RetrofitClient.getAuthHeader(token), facultyId)
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Failed to delete faculty"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateCourse(token: String, courseId: String, facultyId: String, name: String): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.updateCourse(RetrofitClient.getAuthHeader(token), courseId, CreateCourseRequest(facultyId, name))
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Failed to update course"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteCourse(token: String, courseId: String): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.deleteCourse(RetrofitClient.getAuthHeader(token), courseId)
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Failed to delete course"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateSubjectCombination(token: String, subjectId: String, courseId: String, name: String): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.updateSubject(RetrofitClient.getAuthHeader(token), subjectId, CreateSubjectCombinationRequest(courseId, name))
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Failed to update subject"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteSubjectCombination(token: String, subjectId: String): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.deleteSubjectCombination(RetrofitClient.getAuthHeader(token), subjectId)
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Failed to delete subject"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ==================== ADMIN ====================

    suspend fun getAllUsers(token: String): Result<List<UserResponse>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getAllUsers(RetrofitClient.getAuthHeader(token))
            if (response.isSuccessful) Result.success(response.body() ?: emptyList())
            else Result.failure(Exception("Failed to fetch users"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateUser(
        token: String,
        userId: String,
        request: UpdateUserRequest
    ): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.updateUser(RetrofitClient.getAuthHeader(token), userId, request)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Failed to update user"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun verifyUser(token: String, userId: String, verified: Boolean): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.verifyUser(RetrofitClient.getAuthHeader(token), userId, VerifyUserRequest(userId, verified))
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Failed to update user"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteUser(token: String, userId: String): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.deleteUser(RetrofitClient.getAuthHeader(token), userId)
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Failed to delete user"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun unlockUser(token: String, userId: String): Result<MessageResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.unlockUser(RetrofitClient.getAuthHeader(token), userId)
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Failed to unlock user"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ==================== REPORTS ====================

    suspend fun getReports(token: String): Result<ReportsResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.getReports(RetrofitClient.getAuthHeader(token))
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Failed to fetch reports"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
