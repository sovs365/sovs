package com.university.voting.api;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\nH\u00c6\u0003J\t\u0010&\u001a\u00020\nH\u00c6\u0003J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u00c6\u0003Js\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\u00032\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u00c6\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010-\u001a\u00020\u0003H\u00d6\u0001J\t\u0010.\u001a\u00020/H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e\u00a8\u00060"}, d2 = {"Lcom/university/voting/api/ReportsResponse;", "", "totalVoters", "", "totalCandidates", "totalAdmins", "totalElections", "activeElections", "totalVotesCast", "genderBreakdown", "Lcom/university/voting/api/GenderBreakdown;", "candidateGenderBreakdown", "disabilityCount", "voterTurnoutPerElection", "", "Lcom/university/voting/api/ElectionTurnout;", "<init>", "(IIIIIILcom/university/voting/api/GenderBreakdown;Lcom/university/voting/api/GenderBreakdown;ILjava/util/List;)V", "getTotalVoters", "()I", "getTotalCandidates", "getTotalAdmins", "getTotalElections", "getActiveElections", "getTotalVotesCast", "getGenderBreakdown", "()Lcom/university/voting/api/GenderBreakdown;", "getCandidateGenderBreakdown", "getDisabilityCount", "getVoterTurnoutPerElection", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "", "other", "hashCode", "toString", "", "app_release"})
public final class ReportsResponse {
    private final int totalVoters = 0;
    private final int totalCandidates = 0;
    private final int totalAdmins = 0;
    private final int totalElections = 0;
    private final int activeElections = 0;
    private final int totalVotesCast = 0;
    @org.jetbrains.annotations.NotNull()
    private final com.university.voting.api.GenderBreakdown genderBreakdown = null;
    @org.jetbrains.annotations.NotNull()
    private final com.university.voting.api.GenderBreakdown candidateGenderBreakdown = null;
    private final int disabilityCount = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.university.voting.api.ElectionTurnout> voterTurnoutPerElection = null;
    
    public ReportsResponse(int totalVoters, int totalCandidates, int totalAdmins, int totalElections, int activeElections, int totalVotesCast, @org.jetbrains.annotations.NotNull()
    com.university.voting.api.GenderBreakdown genderBreakdown, @org.jetbrains.annotations.NotNull()
    com.university.voting.api.GenderBreakdown candidateGenderBreakdown, int disabilityCount, @org.jetbrains.annotations.NotNull()
    java.util.List<com.university.voting.api.ElectionTurnout> voterTurnoutPerElection) {
        super();
    }
    
    public final int getTotalVoters() {
        return 0;
    }
    
    public final int getTotalCandidates() {
        return 0;
    }
    
    public final int getTotalAdmins() {
        return 0;
    }
    
    public final int getTotalElections() {
        return 0;
    }
    
    public final int getActiveElections() {
        return 0;
    }
    
    public final int getTotalVotesCast() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.university.voting.api.GenderBreakdown getGenderBreakdown() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.university.voting.api.GenderBreakdown getCandidateGenderBreakdown() {
        return null;
    }
    
    public final int getDisabilityCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.university.voting.api.ElectionTurnout> getVoterTurnoutPerElection() {
        return null;
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.university.voting.api.ElectionTurnout> component10() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int component6() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.university.voting.api.GenderBreakdown component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.university.voting.api.GenderBreakdown component8() {
        return null;
    }
    
    public final int component9() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.university.voting.api.ReportsResponse copy(int totalVoters, int totalCandidates, int totalAdmins, int totalElections, int activeElections, int totalVotesCast, @org.jetbrains.annotations.NotNull()
    com.university.voting.api.GenderBreakdown genderBreakdown, @org.jetbrains.annotations.NotNull()
    com.university.voting.api.GenderBreakdown candidateGenderBreakdown, int disabilityCount, @org.jetbrains.annotations.NotNull()
    java.util.List<com.university.voting.api.ElectionTurnout> voterTurnoutPerElection) {
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