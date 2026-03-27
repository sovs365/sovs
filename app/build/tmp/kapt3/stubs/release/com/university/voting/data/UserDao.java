package com.university.voting.data;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010\u0010\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0015\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0017\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010\u001b\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u000e\u0010\u001f\u001a\u00020\u001dH\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010 \u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006!\u00c0\u0006\u0003"}, d2 = {"Lcom/university/voting/data/UserDao;", "", "insert", "", "user", "Lcom/university/voting/data/User;", "(Lcom/university/voting/data/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "getUser", "userId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserByRegNo", "regNo", "getUsersByRole", "", "role", "delete", "getUserByUsername", "username", "getUserByPhone", "phone", "getUserByEmail", "email", "getUnverifiedUsers", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getVotersNotVoted", "electionId", "countByGender", "", "gender", "countWithDisability", "countCandidatesByGender", "app_release"})
@androidx.room.Dao()
public abstract interface UserDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.university.voting.data.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.university.voting.data.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE userId = :userId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUser(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.university.voting.data.User> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE regNo = :regNo")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserByRegNo(@org.jetbrains.annotations.NotNull()
    java.lang.String regNo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.university.voting.data.User> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE role = :role")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUsersByRole(@org.jetbrains.annotations.NotNull()
    java.lang.String role, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.university.voting.data.User>> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.university.voting.data.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE username = :username")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserByUsername(@org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.university.voting.data.User> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE phoneNumber = :phone")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserByPhone(@org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.university.voting.data.User> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE email = :email")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserByEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.university.voting.data.User> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE isVerified = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUnverifiedUsers(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.university.voting.data.User>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE role = \'voter\' AND userId NOT IN (SELECT voterId FROM votes WHERE electionId = :electionId)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getVotersNotVoted(@org.jetbrains.annotations.NotNull()
    java.lang.String electionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.university.voting.data.User>> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM users WHERE gender = :gender")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object countByGender(@org.jetbrains.annotations.NotNull()
    java.lang.String gender, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM users WHERE disability IS NOT NULL")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object countWithDisability(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM users WHERE role = \'candidate\' AND gender = :gender")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object countCandidatesByGender(@org.jetbrains.annotations.NotNull()
    java.lang.String gender, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}