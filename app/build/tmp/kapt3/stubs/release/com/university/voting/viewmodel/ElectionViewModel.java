package com.university.voting.viewmodel;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\b\u0010)\u001a\u00020#H\u0002J\u0006\u0010*\u001a\u00020+J\u0006\u0010,\u001a\u00020+J\u0006\u0010-\u001a\u00020+J\u000e\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u00020#J\u0006\u00100\u001a\u00020+J.\u00101\u001a\u00020+2\u0006\u00102\u001a\u00020#2\u0006\u00103\u001a\u00020#2\u0006\u0010/\u001a\u00020#2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000205J\u0016\u00107\u001a\u00020+2\u0006\u00108\u001a\u00020#2\u0006\u00109\u001a\u00020#J*\u0010:\u001a\u00020+2\u0006\u0010;\u001a\u00020#2\u0006\u0010<\u001a\u00020#2\b\u0010=\u001a\u0004\u0018\u00010#2\b\u0010>\u001a\u0004\u0018\u00010#J\u000e\u0010?\u001a\u00020+2\u0006\u00108\u001a\u00020#J\u001c\u0010@\u001a\u00020+2\u0006\u00108\u001a\u00020#2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\rJ\u000e\u0010C\u001a\u00020+2\u0006\u0010D\u001a\u00020#R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\r0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\r0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0012R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0012R\u001c\u0010&\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\'0\'0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\'0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0012\u00a8\u0006E"}, d2 = {"Lcom/university/voting/viewmodel/ElectionViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "<init>", "(Landroid/app/Application;)V", "repository", "Lcom/university/voting/repository/VotingRepository;", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "_elections", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/university/voting/api/ElectionResponse;", "elections", "Landroidx/lifecycle/LiveData;", "getElections", "()Landroidx/lifecycle/LiveData;", "_openElections", "openElections", "getOpenElections", "_positions", "Lcom/university/voting/api/PositionResponse;", "positions", "getPositions", "_candidates", "Lcom/university/voting/api/CandidateResponse;", "candidates", "getCandidates", "_results", "Lcom/university/voting/api/ElectionResultsResponse;", "results", "getResults", "_message", "", "message", "getMessage", "_isLoading", "", "isLoading", "getToken", "loadAllElections", "", "loadOpenElections", "loadAllPositions", "loadCandidatesForPosition", "positionId", "loadAllCandidates", "createElection", "title", "description", "startDate", "", "endDate", "updateElectionStatus", "electionId", "status", "createPosition", "name", "type", "facultyId", "courseId", "loadElectionResults", "castVote", "votes", "Lcom/university/voting/api/PositionVote;", "verifyCandidate", "candidateId", "app_release"})
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
    java.lang.String courseId) {
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