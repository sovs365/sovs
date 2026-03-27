package com.university.voting.data;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\tH\u00a7@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\t2\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0014\u00c0\u0006\u0003"}, d2 = {"Lcom/university/voting/data/CandidateDao;", "", "insert", "", "candidate", "Lcom/university/voting/data/Candidate;", "(Lcom/university/voting/data/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "getCandidatesForPosition", "", "positionId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCandidate", "candidateId", "getAllCandidatesWithUser", "Lcom/university/voting/data/CandidateWithUser;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCandidatesWithUserForPosition", "delete", "app_release"})
@androidx.room.Dao()
public abstract interface CandidateDao {
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.university.voting.data.Candidate candidate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.university.voting.data.Candidate candidate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM candidates WHERE positionId = :positionId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCandidatesForPosition(@org.jetbrains.annotations.NotNull()
    java.lang.String positionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.university.voting.data.Candidate>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM candidates WHERE candidateId = :candidateId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCandidate(@org.jetbrains.annotations.NotNull()
    java.lang.String candidateId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.university.voting.data.Candidate> $completion);
    
    @androidx.room.Query(value = "\n        SELECT c.*, \n               u.userId as user_userId, u.username as user_username, u.regNo as user_regNo, \n               u.fullName as user_fullName, u.faculty as user_faculty, u.course as user_course, \n               u.subjectCombination as user_subjectCombination, u.levelOfStudy as user_levelOfStudy, \n               u.yearOfStudy as user_yearOfStudy, u.gender as user_gender, u.disability as user_disability, \n               u.passwordHash as user_passwordHash, u.role as user_role, \n               u.manifesto as user_manifesto, u.cvPath as user_cvPath, \n               u.declarationConsentPath as user_declarationConsentPath, u.schoolIdPath as user_schoolIdPath, \n               u.academicClearancePath as user_academicClearancePath, u.signedCodeOfConductPath as user_signedCodeOfConductPath, \n               u.passportPhotoPath as user_passportPhotoPath,\n               u.isVerified as user_isVerified, u.createdAt as user_createdAt, \n               u.phoneNumber as user_phoneNumber, u.loginAttempts as user_loginAttempts, \n               u.isLocked as user_isLocked \n        FROM candidates c \n        JOIN users u ON c.userId = u.userId\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllCandidatesWithUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.university.voting.data.CandidateWithUser>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT c.*, \n               u.userId as user_userId, u.username as user_username, u.regNo as user_regNo, \n               u.fullName as user_fullName, u.faculty as user_faculty, u.course as user_course, \n               u.subjectCombination as user_subjectCombination, u.levelOfStudy as user_levelOfStudy, \n               u.yearOfStudy as user_yearOfStudy, u.gender as user_gender, u.disability as user_disability, \n               u.passwordHash as user_passwordHash, u.role as user_role, \n               u.manifesto as user_manifesto, u.cvPath as user_cvPath, \n               u.declarationConsentPath as user_declarationConsentPath, u.schoolIdPath as user_schoolIdPath, \n               u.academicClearancePath as user_academicClearancePath, u.signedCodeOfConductPath as user_signedCodeOfConductPath, \n               u.passportPhotoPath as user_passportPhotoPath,\n               u.isVerified as user_isVerified, u.createdAt as user_createdAt, \n               u.phoneNumber as user_phoneNumber, u.loginAttempts as user_loginAttempts, \n               u.isLocked as user_isLocked \n        FROM candidates c \n        JOIN users u ON c.userId = u.userId \n        WHERE c.positionId = :positionId\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCandidatesWithUserForPosition(@org.jetbrains.annotations.NotNull()
    java.lang.String positionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.university.voting.data.CandidateWithUser>> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.university.voting.data.Candidate candidate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}