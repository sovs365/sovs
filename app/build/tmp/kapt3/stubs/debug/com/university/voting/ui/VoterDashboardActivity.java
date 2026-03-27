package com.university.voting.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001 B\u0005\u00a2\u0006\u0002\u0010\u0002JV\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00110\u00102\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00130\u00102\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00150\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0012\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0017H\u0014J\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\bH\u0002\u00a2\u0006\u0002\u0010\u001fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/university/voting/ui/VoterDashboardActivity;", "Lcom/university/voting/ui/BaseActivity;", "()V", "binding", "Lcom/university/voting/databinding/ActivityVoterDashboardBinding;", "repository", "Lcom/university/voting/repository/VotingRepository;", "token", "", "isElectionEligible", "", "user", "Lcom/university/voting/api/UserResponse;", "position", "Lcom/university/voting/api/PositionResponse;", "faculties", "", "Lcom/university/voting/api/FacultyResponse;", "courses", "Lcom/university/voting/api/CourseResponse;", "subjects", "Lcom/university/voting/api/SubjectCombinationResponse;", "loadElections", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "yearLabelToInt", "", "label", "(Ljava/lang/String;)Ljava/lang/Integer;", "ElectionAdapter", "app_debug"})
public final class VoterDashboardActivity extends com.university.voting.ui.BaseActivity {
    private com.university.voting.databinding.ActivityVoterDashboardBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final com.university.voting.repository.VotingRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String token = "";
    
    public VoterDashboardActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void loadElections() {
    }
    
    private final boolean isElectionEligible(com.university.voting.api.UserResponse user, com.university.voting.api.PositionResponse position, java.util.Map<java.lang.String, com.university.voting.api.FacultyResponse> faculties, java.util.Map<java.lang.String, com.university.voting.api.CourseResponse> courses, java.util.Map<java.lang.String, com.university.voting.api.SubjectCombinationResponse> subjects) {
        return false;
    }
    
    private final java.lang.Integer yearLabelToInt(java.lang.String label) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u0010\u0012\f\u0012\n0\u0002R\u00060\u0000R\u00020\u00030\u0001:\u0001\u0019B5\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\u0002\u0010\rJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J \u0010\u0012\u001a\u00020\f2\u000e\u0010\u0013\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0011H\u0016J \u0010\u0015\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0011H\u0016R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/university/voting/ui/VoterDashboardActivity$ElectionAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/university/voting/ui/VoterDashboardActivity$ElectionAdapter$ViewHolder;", "Lcom/university/voting/ui/VoterDashboardActivity;", "elections", "", "Lcom/university/voting/api/ElectionResponse;", "votedIds", "", "", "onVote", "Lkotlin/Function1;", "", "(Lcom/university/voting/ui/VoterDashboardActivity;Ljava/util/List;Ljava/util/Set;Lkotlin/jvm/functions/Function1;)V", "dateFormatter", "Ljava/text/SimpleDateFormat;", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_debug"})
    public final class ElectionAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.university.voting.ui.VoterDashboardActivity.ElectionAdapter.ViewHolder> {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.university.voting.api.ElectionResponse> elections = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.Set<java.lang.String> votedIds = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.university.voting.api.ElectionResponse, kotlin.Unit> onVote = null;
        @org.jetbrains.annotations.NotNull()
        private final java.text.SimpleDateFormat dateFormatter = null;
        
        public ElectionAdapter(@org.jetbrains.annotations.NotNull()
        java.util.List<com.university.voting.api.ElectionResponse> elections, @org.jetbrains.annotations.NotNull()
        java.util.Set<java.lang.String> votedIds, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.university.voting.api.ElectionResponse, kotlin.Unit> onVote) {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.university.voting.ui.VoterDashboardActivity.ElectionAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.university.voting.ui.VoterDashboardActivity.ElectionAdapter.ViewHolder holder, int position) {
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f\u00a8\u0006\u0011"}, d2 = {"Lcom/university/voting/ui/VoterDashboardActivity$ElectionAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/university/voting/ui/VoterDashboardActivity$ElectionAdapter;Landroid/view/View;)V", "btnVote", "Landroid/widget/Button;", "getBtnVote", "()Landroid/widget/Button;", "tvDate", "Landroid/widget/TextView;", "getTvDate", "()Landroid/widget/TextView;", "tvStatus", "getTvStatus", "tvTitle", "getTvTitle", "app_debug"})
        public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            @org.jetbrains.annotations.NotNull()
            private final android.widget.TextView tvTitle = null;
            @org.jetbrains.annotations.NotNull()
            private final android.widget.TextView tvDate = null;
            @org.jetbrains.annotations.NotNull()
            private final android.widget.TextView tvStatus = null;
            @org.jetbrains.annotations.NotNull()
            private final android.widget.Button btnVote = null;
            
            public ViewHolder(@org.jetbrains.annotations.NotNull()
            android.view.View view) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.TextView getTvTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.TextView getTvDate() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.TextView getTvStatus() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.Button getBtnVote() {
                return null;
            }
        }
    }
}