package com.university.voting.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0014\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u000f2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u0007J.\u0010-\u001a\u00020)2\u0006\u0010.\u001a\u00020\u000f2\u0006\u0010/\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000f2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000202JB\u00104\u001a\u00020)2\u0006\u00105\u001a\u00020\u000f2\u0006\u00106\u001a\u00020\u000f2\b\u00107\u001a\u0004\u0018\u00010\u000f2\b\u00108\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u000fJ\b\u0010;\u001a\u00020\u000fH\u0002J\u0006\u0010<\u001a\u00020)J\u0006\u0010=\u001a\u00020)J\u0006\u0010>\u001a\u00020)J\u000e\u0010?\u001a\u00020)2\u0006\u00100\u001a\u00020\u000fJ\u000e\u0010@\u001a\u00020)2\u0006\u0010*\u001a\u00020\u000fJ\u0006\u0010A\u001a\u00020)J\u0016\u0010B\u001a\u00020)2\u0006\u0010*\u001a\u00020\u000f2\u0006\u0010C\u001a\u00020\u000fJ\u000e\u0010D\u001a\u00020)2\u0006\u0010E\u001a\u00020\u000fR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u001d\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00070\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018R\u0016\u0010\"\u001a\n \r*\u0004\u0018\u00010#0#X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00140\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0018\u00a8\u0006F"}, d2 = {"Lcom/university/voting/viewmodel/ElectionViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_candidates", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/university/voting/api/CandidateResponse;", "_elections", "Lcom/university/voting/api/ElectionResponse;", "_isLoading", "", "kotlin.jvm.PlatformType", "_message", "", "_openElections", "_positions", "Lcom/university/voting/api/PositionResponse;", "_results", "Lcom/university/voting/api/ElectionResultsResponse;", "candidates", "Landroidx/lifecycle/LiveData;", "getCandidates", "()Landroidx/lifecycle/LiveData;", "elections", "getElections", "isLoading", "message", "getMessage", "openElections", "getOpenElections", "positions", "getPositions", "prefs", "Landroid/content/SharedPreferences;", "repository", "Lcom/university/voting/repository/VotingRepository;", "results", "getResults", "castVote", "", "electionId", "votes", "Lcom/university/voting/api/PositionVote;", "createElection", "title", "description", "positionId", "startDate", "", "endDate", "createPosition", "name", "type", "facultyId", "courseId", "subjectCombinationId", "yearOfStudy", "getToken", "loadAllCandidates", "loadAllElections", "loadAllPositions", "loadCandidatesForPosition", "loadElectionResults", "loadOpenElections", "updateElectionStatus", "status", "verifyCandidate", "candidateId", "app_debug"})
public final class ElectionViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.university.voting.repository.VotingRepository repository = null;
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.university.voting.api.ElectionResponse>> _elections = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.ElectionResponse>> elections = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.university.voting.api.ElectionResponse>> _openElections = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.ElectionResponse>> openElections = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.university.voting.api.PositionResponse>> _positions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.PositionResponse>> positions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.university.voting.api.CandidateResponse>> _candidates = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.CandidateResponse>> candidates = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.university.voting.api.ElectionResultsResponse> _results = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.university.voting.api.ElectionResultsResponse> results = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _message = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> message = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    
    public ElectionViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.ElectionResponse>> getElections() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.ElectionResponse>> getOpenElections() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.PositionResponse>> getPositions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.CandidateResponse>> getCandidates() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.university.voting.api.ElectionResultsResponse> getResults() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading() {
        return null;
    }
    
    private final java.lang.String getToken() {
        return null;
    }
    
    public final void loadAllElections() {
    }
    
    public final void loadOpenElections() {
    }
    
    public final void loadAllPositions() {
    }
    
    public final void loadCandidatesForPosition(@org.jetbrains.annotations.NotNull()
    java.lang.String positionId) {
    }
    
    public final void loadAllCandidates() {
    }
    
    public final void createElection(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String positionId, long startDate, long endDate) {
    }
    
    public final void updateElectionStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String electionId, @org.jetbrains.annotations.NotNull()
    java.lang.String status) {
    }
    
    public final void createPosition(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.Nullable()
    java.lang.String facultyId, @org.jetbrains.annotations.Nullable()
    java.lang.String courseId, @org.jetbrains.annotations.Nullable()
    java.lang.String subjectCombinationId, @org.jetbrains.annotations.Nullable()
    java.lang.String yearOfStudy) {
    }
    
    public final void loadElectionResults(@org.jetbrains.annotations.NotNull()
    java.lang.String electionId) {
    }
    
    public final void castVote(@org.jetbrains.annotations.NotNull()
    java.lang.String electionId, @org.jetbrains.annotations.NotNull()
    java.util.List<com.university.voting.api.PositionVote> votes) {
    }
    
    public final void verifyCandidate(@org.jetbrains.annotations.NotNull()
    java.lang.String candidateId) {
    }
}