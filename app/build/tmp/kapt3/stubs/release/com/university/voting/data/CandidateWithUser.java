package com.university.voting.data;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lcom/university/voting/data/CandidateWithUser;", "", "candidate", "Lcom/university/voting/data/Candidate;", "user", "Lcom/university/voting/data/User;", "<init>", "(Lcom/university/voting/data/Candidate;Lcom/university/voting/data/User;)V", "getCandidate", "()Lcom/university/voting/data/Candidate;", "getUser", "()Lcom/university/voting/data/User;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"})
public final class CandidateWithUser {
    @androidx.room.Embedded()
    @org.jetbrains.annotations.NotNull()
    private final com.university.voting.data.Candidate candidate = null;
    @androidx.room.Embedded(prefix = "user_")
    @org.jetbrains.annotations.NotNull()
    private final com.university.voting.data.User user = null;
    
    public CandidateWithUser(@org.jetbrains.annotations.NotNull()
    com.university.voting.data.Candidate candidate, @org.jetbrains.annotations.NotNull()
    com.university.voting.data.User user) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.university.voting.data.Candidate getCandidate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.university.voting.data.User getUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.university.voting.data.Candidate component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.university.voting.data.User component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.university.voting.data.CandidateWithUser copy(@org.jetbrains.annotations.NotNull()
    com.university.voting.data.Candidate candidate, @org.jetbrains.annotations.NotNull()
    com.university.voting.data.User user) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}