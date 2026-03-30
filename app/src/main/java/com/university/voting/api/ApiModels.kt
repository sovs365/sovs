package com.university.voting.api

import com.google.gson.annotations.SerializedName

// ==================== REQUEST DTOs ====================

data class RegisterRequest(
    val username: String,
    val fullName: String,
    val email: String,
    val password: String,
    val role: String,
    val phoneNumber: String,
    val regNo: String? = null,
    val faculty: String? = null,
    val course: String? = null,
    val subjectCombination: String? = null,
    val levelOfStudy: String? = null,
    val yearOfStudy: Int? = null,
    val gender: String? = null,
    val disability: String? = null,
    val manifesto: String? = null,
    val positionId: String? = null,
    val profilePhotoPath: String? = null
)

data class LoginRequest(
    val username: String,
    val password: String
)

data class ResetPasswordRequest(
    val email: String,
    val code: String,
    val newPassword: String,
    val confirmPassword: String
)

data class CreateElectionRequest(
    val title: String,
    val description: String,
    val positionId: String,
    val startDate: Long,
    val endDate: Long
)

data class UpdateElectionStatusRequest(
    val status: String
)

data class CreatePositionRequest(
    val name: String,
    val type: String = "general",
    val facultyId: String? = null,
    val courseId: String? = null,
    val subjectCombinationId: String? = null,
    val yearOfStudy: String? = null
)

data class CreateFacultyRequest(val name: String)

data class CreateCourseRequest(
    val facultyId: String,
    val name: String
)

data class CreateSubjectCombinationRequest(
    val courseId: String,
    val name: String
)

data class CastVoteRequest(
    val electionId: String,
    val votes: List<PositionVote>
)

data class PositionVote(
    val positionId: String,
    val candidateId: String
)

data class VerifyUserRequest(
    val userId: String,
    val verified: Boolean
)

data class ForgotPasswordRequest(
    val phoneNumber: String
)

data class SendCodeRequest(
    @SerializedName("email")
    val email: String
)

data class VerifyCodeRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("code")
    val code: String
)

data class UpdateProfileRequest(
    val fullName: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val manifesto: String? = null,
    val profilePhotoPath: String? = null
)

data class UpdateUserRequest(
    val fullName: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val regNo: String? = null,
    val faculty: String? = null,
    val course: String? = null,
    val subjectCombination: String? = null,
    val levelOfStudy: String? = null,
    val yearOfStudy: Int? = null,
    val gender: String? = null,
    val disability: String? = null,
    val manifesto: String? = null,
    val role: String? = null
)

// ==================== RESPONSE DTOs ====================

data class AuthResponse(
    val token: String,
    val user: UserResponse
)

data class LoginCodeResponse(
    val message: String,
    val userId: String? = null,
    val email: String? = null,
    val token: String? = null,
    val verificationCode: String? = null,
    val user: UserResponse
)

data class UserResponse(
    val userId: String,
    val username: String,
    val fullName: String,
    val email: String? = null,
    val role: String,
    val regNo: String? = null,
    val faculty: String? = null,
    val course: String? = null,
    val subjectCombination: String? = null,
    val levelOfStudy: String? = null,
    val yearOfStudy: Int? = null,
    val gender: String? = null,
    val disability: String? = null,
    val phoneNumber: String = "",
    val isVerified: Boolean = false,
    val createdAt: Long = 0,
    val manifesto: String? = null,
    val isLocked: Boolean = false,
    val profilePhotoPath: String? = null
)

data class CurrentUserResponse(
    val user: UserResponse
)

data class ProfileUpdateResponse(
    val message: String,
    val user: UserResponse
)

data class ElectionResponse(
    val electionId: String,
    val positionId: String,
    val title: String,
    val description: String,
    val startDate: Long,
    val endDate: Long,
    val status: String,
    val positionName: String? = null
)

data class PositionResponse(
    val positionId: String,
    val name: String,
    val type: String,
    val facultyId: String? = null,
    val courseId: String? = null,
    val subjectCombinationId: String? = null,
    val yearOfStudy: String? = null
)

data class CandidateResponse(
    val candidateId: String,
    val positionId: String,
    val userId: String,
    val manifesto: String,
    val fullName: String,
    val regNo: String? = null,
    val faculty: String? = null,
    val course: String? = null,
    val profilePhotoPath: String? = null,
    val isVerified: Boolean = false,
    val positionName: String? = null
)

data class ElectionResultsResponse(
    val electionId: String,
    val electionTitle: String,
    val positions: List<PositionResultResponse>
)

data class PositionResultResponse(
    val positionId: String,
    val positionName: String,
    val candidates: List<CandidateVoteResult>,
    val totalVotes: Int
)

data class CandidateVoteResult(
    val candidateId: String,
    val candidateName: String,
    val votes: Int,
    val percentage: Double
)

data class FacultyResponse(
    val facultyId: String,
    val name: String
)

data class CourseResponse(
    val courseId: String,
    val facultyId: String,
    val name: String
)

data class SubjectCombinationResponse(
    val id: String,
    val courseId: String,
    val name: String
)

data class VoteReceiptResponse(
    val voteId: String,
    val electionTitle: String,
    val positionName: String,
    val candidateName: String,
    val timestamp: Long,
    val hash: String
)

data class ReportsResponse(
    val totalVoters: Int,
    val totalCandidates: Int,
    val totalAdmins: Int,
    val totalElections: Int,
    val activeElections: Int,
    val totalVotesCast: Int,
    val genderBreakdown: GenderBreakdown,
    val candidateGenderBreakdown: GenderBreakdown,
    val disabilityCount: Int,
    val voterTurnoutPerElection: List<ElectionTurnout>
)

data class GenderBreakdown(
    val male: Int,
    val female: Int
)

data class ElectionTurnout(
    val electionId: String,
    val electionTitle: String,
    val totalEligible: Int,
    val totalVoted: Int,
    val turnoutPercentage: Double
)

data class MessageResponse(val message: String)

data class ErrorResponse(val error: String)

data class SendCodeResponse(
    val message: String,
    val email: String? = null,
    val code: String? = null
)

data class HasVotedResponse(
    val hasVoted: Boolean
)
