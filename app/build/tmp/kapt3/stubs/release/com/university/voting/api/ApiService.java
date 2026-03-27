package com.university.voting.api;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u00e4\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0018H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u001dJ\u001a\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u001dJ\u001e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00032\b\b\u0001\u0010 \u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"J\u001e\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00032\b\b\u0001\u0010 \u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"J(\u0010%\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010\u0005\u001a\u00020\'H\u00a7@\u00a2\u0006\u0002\u0010(J2\u0010)\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010 \u001a\u00020!2\b\b\u0001\u0010\u0005\u001a\u00020*H\u00a7@\u00a2\u0006\u0002\u0010+J\u001a\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u001b0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u001dJ$\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u001b0\u00032\b\b\u0001\u0010 \u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"J\u001e\u0010/\u001a\b\u0012\u0004\u0012\u00020-0\u00032\b\b\u0001\u00100\u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"J(\u00101\u001a\b\u0012\u0004\u0012\u00020-0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010\u0005\u001a\u000202H\u00a7@\u00a2\u0006\u0002\u00103J\u001a\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002050\u001b0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u001dJ$\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002050\u001b0\u00032\b\b\u0001\u00100\u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"J\u001e\u00107\u001a\b\u0012\u0004\u0012\u0002050\u00032\b\b\u0001\u00108\u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"J2\u00109\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u00108\u001a\u00020!2\b\b\u0001\u0010\u0005\u001a\u00020:H\u00a7@\u00a2\u0006\u0002\u0010;J(\u0010<\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u00108\u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010=J(\u0010>\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010\u0005\u001a\u00020?H\u00a7@\u00a2\u0006\u0002\u0010@J$\u0010A\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020B0\u001b0\u00032\b\b\u0001\u0010&\u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"J(\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010 \u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010=J\u001a\u0010E\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020F0\u001b0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u001dJ(\u0010G\u001a\b\u0012\u0004\u0012\u00020F0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010\u0005\u001a\u00020HH\u00a7@\u00a2\u0006\u0002\u0010IJ2\u0010J\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010K\u001a\u00020!2\b\b\u0001\u0010\u0005\u001a\u00020HH\u00a7@\u00a2\u0006\u0002\u0010LJ(\u0010M\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010N\u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010=J\u001a\u0010O\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020P0\u001b0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u001dJ$\u0010Q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020P0\u001b0\u00032\b\b\u0001\u0010N\u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"J(\u0010R\u001a\b\u0012\u0004\u0012\u00020P0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010\u0005\u001a\u00020SH\u00a7@\u00a2\u0006\u0002\u0010TJ2\u0010U\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010K\u001a\u00020!2\b\b\u0001\u0010\u0005\u001a\u00020SH\u00a7@\u00a2\u0006\u0002\u0010VJ(\u0010W\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010X\u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010=J\u001a\u0010Y\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Z0\u001b0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u001dJ$\u0010[\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Z0\u001b0\u00032\b\b\u0001\u0010X\u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"J(\u0010\\\u001a\b\u0012\u0004\u0012\u00020Z0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010\u0005\u001a\u00020]H\u00a7@\u00a2\u0006\u0002\u0010^J2\u0010_\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010K\u001a\u00020!2\b\b\u0001\u0010\u0005\u001a\u00020]H\u00a7@\u00a2\u0006\u0002\u0010`J(\u0010a\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010b\u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010=J$\u0010c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020d0\u001b0\u00032\b\b\u0001\u0010&\u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"J2\u0010e\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010f\u001a\u00020!2\b\b\u0001\u0010\u0005\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010gJ2\u0010h\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010f\u001a\u00020!2\b\b\u0001\u0010\u0005\u001a\u00020:H\u00a7@\u00a2\u0006\u0002\u0010;J(\u0010i\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010f\u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010=J(\u0010j\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010&\u001a\u00020!2\b\b\u0001\u0010f\u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010=J\u001e\u0010k\u001a\b\u0012\u0004\u0012\u00020l0\u00032\b\b\u0001\u0010&\u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"\u00a8\u0006m\u00c0\u0006\u0003"}, d2 = {"Lcom/university/voting/api/ApiService;", "", "sendRegistrationCode", "Lretrofit2/Response;", "Lcom/university/voting/api/SendCodeResponse;", "request", "Lcom/university/voting/api/SendCodeRequest;", "(Lcom/university/voting/api/SendCodeRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyRegistrationCode", "Lcom/university/voting/api/MessageResponse;", "Lcom/university/voting/api/VerifyCodeRequest;", "(Lcom/university/voting/api/VerifyCodeRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "Lcom/university/voting/api/AuthResponse;", "Lcom/university/voting/api/RegisterRequest;", "(Lcom/university/voting/api/RegisterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "Lcom/university/voting/api/LoginCodeResponse;", "Lcom/university/voting/api/LoginRequest;", "(Lcom/university/voting/api/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyLoginCode", "sendPasswordResetCode", "verifyPasswordResetCode", "resetPassword", "Lcom/university/voting/api/ResetPasswordRequest;", "(Lcom/university/voting/api/ResetPasswordRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllElections", "", "Lcom/university/voting/api/ElectionResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOpenElections", "getElection", "electionId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getElectionResults", "Lcom/university/voting/api/ElectionResultsResponse;", "createElection", "token", "Lcom/university/voting/api/CreateElectionRequest;", "(Ljava/lang/String;Lcom/university/voting/api/CreateElectionRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateElectionStatus", "Lcom/university/voting/api/UpdateElectionStatusRequest;", "(Ljava/lang/String;Ljava/lang/String;Lcom/university/voting/api/UpdateElectionStatusRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllPositions", "Lcom/university/voting/api/PositionResponse;", "getPositionsForElection", "getPosition", "positionId", "createPosition", "Lcom/university/voting/api/CreatePositionRequest;", "(Ljava/lang/String;Lcom/university/voting/api/CreatePositionRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllCandidates", "Lcom/university/voting/api/CandidateResponse;", "getCandidatesForPosition", "getCandidate", "candidateId", "verifyCandidate", "Lcom/university/voting/api/VerifyUserRequest;", "(Ljava/lang/String;Ljava/lang/String;Lcom/university/voting/api/VerifyUserRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCandidate", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "castVote", "Lcom/university/voting/api/CastVoteRequest;", "(Ljava/lang/String;Lcom/university/voting/api/CastVoteRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getVoteReceipts", "Lcom/university/voting/api/VoteReceiptResponse;", "checkVoted", "Lcom/university/voting/api/HasVotedResponse;", "getFaculties", "Lcom/university/voting/api/FacultyResponse;", "createFaculty", "Lcom/university/voting/api/CreateFacultyRequest;", "(Ljava/lang/String;Lcom/university/voting/api/CreateFacultyRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateFaculty", "id", "(Ljava/lang/String;Ljava/lang/String;Lcom/university/voting/api/CreateFacultyRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteFaculty", "facultyId", "getCourses", "Lcom/university/voting/api/CourseResponse;", "getCoursesByFaculty", "createCourse", "Lcom/university/voting/api/CreateCourseRequest;", "(Ljava/lang/String;Lcom/university/voting/api/CreateCourseRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCourse", "(Ljava/lang/String;Ljava/lang/String;Lcom/university/voting/api/CreateCourseRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCourse", "courseId", "getSubjectCombinations", "Lcom/university/voting/api/SubjectCombinationResponse;", "getSubjectsByCourse", "createSubjectCombination", "Lcom/university/voting/api/CreateSubjectCombinationRequest;", "(Ljava/lang/String;Lcom/university/voting/api/CreateSubjectCombinationRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSubject", "(Ljava/lang/String;Ljava/lang/String;Lcom/university/voting/api/CreateSubjectCombinationRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSubjectCombination", "subjectId", "getAllUsers", "Lcom/university/voting/api/UserResponse;", "updateUser", "userId", "(Ljava/lang/String;Ljava/lang/String;Lcom/university/voting/api/RegisterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyUser", "unlockUser", "deleteUser", "getReports", "Lcom/university/voting/api/ReportsResponse;", "app_release"})
public abstract interface ApiService {
    
    @retrofit2.http.POST(value = "api/auth/send-registration-code")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendRegistrationCode(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.SendCodeRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.SendCodeResponse>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/verify-registration-code")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object verifyRegistrationCode(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.VerifyCodeRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/register")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object register(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.RegisterRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.AuthResponse>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/login")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.LoginRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.LoginCodeResponse>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/verify-login-code")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object verifyLoginCode(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.VerifyCodeRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.AuthResponse>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/send-password-reset-code")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendPasswordResetCode(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.SendCodeRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.SendCodeResponse>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/verify-password-reset-code")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object verifyPasswordResetCode(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.VerifyCodeRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/reset-password")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object resetPassword(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.ResetPasswordRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.GET(value = "api/elections")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllElections(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.university.voting.api.ElectionResponse>>> $completion);
    
    @retrofit2.http.GET(value = "api/elections/open")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getOpenElections(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.university.voting.api.ElectionResponse>>> $completion);
    
    @retrofit2.http.GET(value = "api/elections/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getElection(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String electionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.ElectionResponse>> $completion);
    
    @retrofit2.http.GET(value = "api/elections/{id}/results")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getElectionResults(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String electionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.ElectionResultsResponse>> $completion);
    
    @retrofit2.http.POST(value = "api/elections")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createElection(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.CreateElectionRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.PUT(value = "api/elections/{id}/status")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateElectionStatus(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String electionId, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.UpdateElectionStatusRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.GET(value = "api/positions")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllPositions(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.university.voting.api.PositionResponse>>> $completion);
    
    @retrofit2.http.GET(value = "api/positions/election/{electionId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPositionsForElection(@retrofit2.http.Path(value = "electionId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String electionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.university.voting.api.PositionResponse>>> $completion);
    
    @retrofit2.http.GET(value = "api/positions/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPosition(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String positionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.PositionResponse>> $completion);
    
    @retrofit2.http.POST(value = "api/positions")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createPosition(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.CreatePositionRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.PositionResponse>> $completion);
    
    @retrofit2.http.GET(value = "api/candidates")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllCandidates(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.university.voting.api.CandidateResponse>>> $completion);
    
    @retrofit2.http.GET(value = "api/candidates/position/{positionId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCandidatesForPosition(@retrofit2.http.Path(value = "positionId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String positionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.university.voting.api.CandidateResponse>>> $completion);
    
    @retrofit2.http.GET(value = "api/candidates/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCandidate(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String candidateId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.CandidateResponse>> $completion);
    
    @retrofit2.http.PUT(value = "api/candidates/{id}/verify")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object verifyCandidate(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String candidateId, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.VerifyUserRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.DELETE(value = "api/candidates/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteCandidate(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String candidateId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.POST(value = "api/votes")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object castVote(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.CastVoteRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.GET(value = "api/votes/receipts")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getVoteReceipts(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.university.voting.api.VoteReceiptResponse>>> $completion);
    
    @retrofit2.http.GET(value = "api/votes/check/{electionId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object checkVoted(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "electionId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String electionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.HasVotedResponse>> $completion);
    
    @retrofit2.http.GET(value = "api/data/faculties")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFaculties(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.university.voting.api.FacultyResponse>>> $completion);
    
    @retrofit2.http.POST(value = "api/data/faculties")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createFaculty(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.CreateFacultyRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.FacultyResponse>> $completion);
    
    @retrofit2.http.PUT(value = "api/data/faculties/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateFaculty(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.CreateFacultyRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.DELETE(value = "api/data/faculties/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteFaculty(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String facultyId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.GET(value = "api/data/courses")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCourses(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.university.voting.api.CourseResponse>>> $completion);
    
    @retrofit2.http.GET(value = "api/data/courses/faculty/{facultyId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCoursesByFaculty(@retrofit2.http.Path(value = "facultyId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String facultyId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.university.voting.api.CourseResponse>>> $completion);
    
    @retrofit2.http.POST(value = "api/data/courses")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createCourse(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.CreateCourseRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.CourseResponse>> $completion);
    
    @retrofit2.http.PUT(value = "api/data/courses/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateCourse(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.CreateCourseRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.DELETE(value = "api/data/courses/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteCourse(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String courseId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.GET(value = "api/data/subjects")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSubjectCombinations(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.university.voting.api.SubjectCombinationResponse>>> $completion);
    
    @retrofit2.http.GET(value = "api/data/subjects/course/{courseId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSubjectsByCourse(@retrofit2.http.Path(value = "courseId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String courseId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.university.voting.api.SubjectCombinationResponse>>> $completion);
    
    @retrofit2.http.POST(value = "api/data/subjects")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createSubjectCombination(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.CreateSubjectCombinationRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.SubjectCombinationResponse>> $completion);
    
    @retrofit2.http.PUT(value = "api/data/subjects/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateSubject(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.CreateSubjectCombinationRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.DELETE(value = "api/data/subjects/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteSubjectCombination(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String subjectId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.GET(value = "api/admin/users")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllUsers(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.university.voting.api.UserResponse>>> $completion);
    
    @retrofit2.http.PUT(value = "api/admin/users/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateUser(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.RegisterRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.PUT(value = "api/admin/users/{id}/verify")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object verifyUser(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.university.voting.api.VerifyUserRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.PUT(value = "api/admin/users/{id}/unlock")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object unlockUser(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.DELETE(value = "api/admin/users/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteUser(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.MessageResponse>> $completion);
    
    @retrofit2.http.GET(value = "api/reports")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getReports(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.university.voting.api.ReportsResponse>> $completion);
}