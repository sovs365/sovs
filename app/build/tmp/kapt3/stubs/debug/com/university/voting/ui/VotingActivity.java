package com.university.voting.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0012\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/university/voting/ui/VotingActivity;", "Lcom/university/voting/ui/BaseActivity;", "()V", "binding", "Lcom/university/voting/databinding/ActivityVotingBinding;", "electionId", "", "positionsInElection", "", "Lcom/university/voting/api/PositionResponse;", "repository", "Lcom/university/voting/repository/VotingRepository;", "selectedCandidates", "error/NonExistentClass", "Lerror/NonExistentClass;", "token", "loadElectionData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "submitVotes", "app_debug"})
public final class VotingActivity extends com.university.voting.ui.BaseActivity {
    private com.university.voting.databinding.ActivityVotingBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final com.university.voting.repository.VotingRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String electionId = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String token = "";
    @org.jetbrains.annotations.NotNull()
    private final error.NonExistentClass selectedCandidates = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.university.voting.api.PositionResponse> positionsInElection;
    
    public VotingActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadElectionData() {
    }
    
    private final void submitVotes() {
    }
}