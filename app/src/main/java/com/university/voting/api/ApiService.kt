package com.university.voting.api

import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // ==================== AUTH ====================

    @POST("api/auth/send-registration-code")
    suspend fun sendRegistrationCode(@Body request: SendCodeRequest): Response<SendCodeResponse>

    @POST("api/auth/verify-registration-code")
    suspend fun verifyRegistrationCode(@Body request: VerifyCodeRequest): Response<MessageResponse>

    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginCodeResponse>

    @POST("api/auth/verify-login-code")
    suspend fun verifyLoginCode(@Body request: VerifyCodeRequest): Response<AuthResponse>

    @GET("api/auth/me")
    suspend fun getCurrentUser(
        @Header("Authorization") token: String
    ): Response<CurrentUserResponse>

    @PUT("api/auth/me")
    suspend fun updateCurrentUser(
        @Header("Authorization") token: String,
        @Body request: UpdateProfileRequest
    ): Response<ProfileUpdateResponse>

    @POST("api/auth/send-password-reset-code")
    suspend fun sendPasswordResetCode(@Body request: SendCodeRequest): Response<SendCodeResponse>

    @POST("api/auth/verify-password-reset-code")
    suspend fun verifyPasswordResetCode(@Body request: VerifyCodeRequest): Response<MessageResponse>

    @POST("api/auth/reset-password")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): Response<MessageResponse>

    // ==================== ELECTIONS ====================

    @GET("api/elections")
    suspend fun getAllElections(): Response<List<ElectionResponse>>

    @GET("api/elections/open")
    suspend fun getOpenElections(): Response<List<ElectionResponse>>

    @GET("api/elections/{id}")
    suspend fun getElection(@Path("id") electionId: String): Response<ElectionResponse>

    @GET("api/elections/{id}/results")
    suspend fun getElectionResults(@Path("id") electionId: String): Response<ElectionResultsResponse>

    @POST("api/elections")
    suspend fun createElection(
        @Header("Authorization") token: String,
        @Body request: CreateElectionRequest
    ): Response<MessageResponse>

    @PUT("api/elections/{id}/status")
    suspend fun updateElectionStatus(
        @Header("Authorization") token: String,
        @Path("id") electionId: String,
        @Body request: UpdateElectionStatusRequest
    ): Response<MessageResponse>

    // ==================== POSITIONS ====================

    @GET("api/positions")
    suspend fun getAllPositions(): Response<List<PositionResponse>>

    @GET("api/positions/election/{electionId}")
    suspend fun getPositionsForElection(@Path("electionId") electionId: String): Response<List<PositionResponse>>

    @GET("api/positions/{id}")
    suspend fun getPosition(@Path("id") positionId: String): Response<PositionResponse>

    @POST("api/positions")
    suspend fun createPosition(
        @Header("Authorization") token: String,
        @Body request: CreatePositionRequest
    ): Response<PositionResponse>

    // ==================== CANDIDATES ====================

    @GET("api/candidates")
    suspend fun getAllCandidates(): Response<List<CandidateResponse>>

    @GET("api/candidates/position/{positionId}")
    suspend fun getCandidatesForPosition(@Path("positionId") positionId: String): Response<List<CandidateResponse>>

    @GET("api/candidates/{id}")
    suspend fun getCandidate(@Path("id") candidateId: String): Response<CandidateResponse>

    @PUT("api/candidates/{id}/verify")
    suspend fun verifyCandidate(
        @Header("Authorization") token: String,
        @Path("id") candidateId: String,
        @Body request: VerifyUserRequest
    ): Response<MessageResponse>

    @DELETE("api/candidates/{id}")
    suspend fun deleteCandidate(
        @Header("Authorization") token: String,
        @Path("id") candidateId: String
    ): Response<MessageResponse>

    // ==================== VOTES ====================

    @POST("api/votes")
    suspend fun castVote(
        @Header("Authorization") token: String,
        @Body request: CastVoteRequest
    ): Response<MessageResponse>

    @GET("api/votes/receipts")
    suspend fun getVoteReceipts(
        @Header("Authorization") token: String
    ): Response<List<VoteReceiptResponse>>

    @GET("api/votes/check/{electionId}")
    suspend fun checkVoted(
        @Header("Authorization") token: String,
        @Path("electionId") electionId: String
    ): Response<HasVotedResponse>

    // ==================== DATA (Faculties, Courses, Subjects) ====================

    @GET("api/data/faculties")
    suspend fun getFaculties(): Response<List<FacultyResponse>>

    @POST("api/data/faculties")
    suspend fun createFaculty(
        @Header("Authorization") token: String,
        @Body request: CreateFacultyRequest
    ): Response<FacultyResponse>

    @PUT("api/data/faculties/{id}")
    suspend fun updateFaculty(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body request: CreateFacultyRequest
    ): Response<MessageResponse>

    @DELETE("api/data/faculties/{id}")
    suspend fun deleteFaculty(
        @Header("Authorization") token: String,
        @Path("id") facultyId: String
    ): Response<MessageResponse>

    @GET("api/data/courses")
    suspend fun getCourses(): Response<List<CourseResponse>>

    @GET("api/data/courses/faculty/{facultyId}")
    suspend fun getCoursesByFaculty(@Path("facultyId") facultyId: String): Response<List<CourseResponse>>

    @POST("api/data/courses")
    suspend fun createCourse(
        @Header("Authorization") token: String,
        @Body request: CreateCourseRequest
    ): Response<CourseResponse>

    @PUT("api/data/courses/{id}")
    suspend fun updateCourse(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body request: CreateCourseRequest
    ): Response<MessageResponse>

    @DELETE("api/data/courses/{id}")
    suspend fun deleteCourse(
        @Header("Authorization") token: String,
        @Path("id") courseId: String
    ): Response<MessageResponse>

    @GET("api/data/subjects")
    suspend fun getSubjectCombinations(): Response<List<SubjectCombinationResponse>>

    @GET("api/data/subjects/course/{courseId}")
    suspend fun getSubjectsByCourse(@Path("courseId") courseId: String): Response<List<SubjectCombinationResponse>>

    @POST("api/data/subjects")
    suspend fun createSubjectCombination(
        @Header("Authorization") token: String,
        @Body request: CreateSubjectCombinationRequest
    ): Response<SubjectCombinationResponse>

    @PUT("api/data/subjects/{id}")
    suspend fun updateSubject(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body request: CreateSubjectCombinationRequest
    ): Response<MessageResponse>

    @DELETE("api/data/subjects/{id}")
    suspend fun deleteSubjectCombination(
        @Header("Authorization") token: String,
        @Path("id") subjectId: String
    ): Response<MessageResponse>

    // ==================== ADMIN ====================

    @GET("api/admin/users")
    suspend fun getAllUsers(@Header("Authorization") token: String): Response<List<UserResponse>>

    @PUT("api/admin/users/{id}")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Path("id") userId: String,
        @Body request: UpdateUserRequest
    ): Response<MessageResponse>

    @PUT("api/admin/users/{id}/verify")
    suspend fun verifyUser(
        @Header("Authorization") token: String,
        @Path("id") userId: String,
        @Body request: VerifyUserRequest
    ): Response<MessageResponse>

    @PUT("api/admin/users/{id}/unlock")
    suspend fun unlockUser(
        @Header("Authorization") token: String,
        @Path("id") userId: String
    ): Response<MessageResponse>

    @DELETE("api/admin/users/{id}")
    suspend fun deleteUser(
        @Header("Authorization") token: String,
        @Path("id") userId: String
    ): Response<MessageResponse>

    // ==================== REPORTS ====================

    @GET("api/reports")
    suspend fun getReports(@Header("Authorization") token: String): Response<ReportsResponse>
}
