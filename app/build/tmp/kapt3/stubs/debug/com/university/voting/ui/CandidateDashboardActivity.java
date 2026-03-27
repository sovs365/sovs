package com.university.voting.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002JV\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001e0\u001d2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020 0\u001d2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\"0\u001dH\u0002J\b\u0010#\u001a\u00020$H\u0002J\u0012\u0010%\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0014J\b\u0010(\u001a\u00020$H\u0014J\u0019\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010\u0010H\u0002\u00a2\u0006\u0002\u0010,R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/university/voting/ui/CandidateDashboardActivity;", "Lcom/university/voting/ui/BaseActivity;", "()V", "availableElections", "", "Lcom/university/voting/api/ElectionResponse;", "ivProfileHeader", "Landroid/widget/ImageView;", "ivProfileImage", "lvElections", "Landroid/widget/ListView;", "progressBar", "Landroid/widget/ProgressBar;", "repository", "Lcom/university/voting/repository/VotingRepository;", "token", "", "tvManifesto", "Landroid/widget/TextView;", "tvPositionRunning", "tvVerificationStatus", "tvWelcome", "isElectionEligible", "", "user", "Lcom/university/voting/api/UserResponse;", "position", "Lcom/university/voting/api/PositionResponse;", "faculties", "", "Lcom/university/voting/api/FacultyResponse;", "courses", "Lcom/university/voting/api/CourseResponse;", "subjects", "Lcom/university/voting/api/SubjectCombinationResponse;", "loadDashboardData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "yearLabelToInt", "", "label", "(Ljava/lang/String;)Ljava/lang/Integer;", "app_debug"})
public final class CandidateDashboardActivity extends com.university.voting.ui.BaseActivity {
    @org.jetbrains.annotations.NotNull()
    private final com.university.voting.repository.VotingRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String token = "";
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.university.voting.api.ElectionResponse> availableElections;
    private android.widget.TextView tvWelcome;
    private android.widget.TextView tvVerificationStatus;
    private android.widget.TextView tvPositionRunning;
    private android.widget.TextView tvManifesto;
    private android.widget.ListView lvElections;
    private android.widget.ProgressBar progressBar;
    private android.widget.ImageView ivProfileHeader;
    private android.widget.ImageView ivProfileImage;
    
    public CandidateDashboardActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void loadDashboardData() {
    }
    
    private final boolean isElectionEligible(com.university.voting.api.UserResponse user, com.university.voting.api.PositionResponse position, java.util.Map<java.lang.String, com.university.voting.api.FacultyResponse> faculties, java.util.Map<java.lang.String, com.university.voting.api.CourseResponse> courses, java.util.Map<java.lang.String, com.university.voting.api.SubjectCombinationResponse> subjects) {
        return false;
    }
    
    private final java.lang.Integer yearLabelToInt(java.lang.String label) {
        return null;
    }
}