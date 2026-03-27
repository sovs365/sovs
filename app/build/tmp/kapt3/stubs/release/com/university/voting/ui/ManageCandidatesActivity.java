package com.university.voting.ui;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0015B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\u000bH\u0002J\b\u0010\u000f\u001a\u00020\u000bH\u0002J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00060\tR\u00020\u0000X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/university/voting/ui/ManageCandidatesActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "binding", "Lcom/university/voting/databinding/ActivityManageCandidatesBinding;", "db", "Lcom/university/voting/data/AppDatabase;", "adapter", "Lcom/university/voting/ui/ManageCandidatesActivity$CandidatesAdapter;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupRecyclerView", "loadCandidates", "toggleVerification", "item", "Lcom/university/voting/data/CandidateWithUser;", "confirmDelete", "showDetails", "CandidatesAdapter", "app_release"})
public final class ManageCandidatesActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.university.voting.databinding.ActivityManageCandidatesBinding binding;
    private com.university.voting.data.AppDatabase db;
    private com.university.voting.ui.ManageCandidatesActivity.CandidatesAdapter adapter;
    
    public ManageCandidatesActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void loadCandidates() {
    }
    
    private final void toggleVerification(com.university.voting.data.CandidateWithUser item) {
    }
    
    private final void confirmDelete(com.university.voting.data.CandidateWithUser item) {
    }
    
    private final void showDetails(com.university.voting.data.CandidateWithUser item) {
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0004\u0018\u00002\u0010\u0012\f\u0012\n0\u0002R\u00060\u0000R\u00020\u00030\u0001:\u0001\u0019BC\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\u000e\u001a\u00020\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\rJ \u0010\u0010\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J \u0010\u0015\u001a\u00020\u00072\u000e\u0010\u0016\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0014H\u0016J\b\u0010\u0018\u001a\u00020\u0014H\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/university/voting/ui/ManageCandidatesActivity$CandidatesAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/university/voting/ui/ManageCandidatesActivity$CandidatesAdapter$CandidateViewHolder;", "Lcom/university/voting/ui/ManageCandidatesActivity;", "onVerify", "Lkotlin/Function1;", "Lcom/university/voting/data/CandidateWithUser;", "", "onDelete", "onDetails", "<init>", "(Lcom/university/voting/ui/ManageCandidatesActivity;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "candidates", "", "setCandidates", "newCandidates", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "onBindViewHolder", "holder", "position", "getItemCount", "CandidateViewHolder", "app_release"})
    public final class CandidatesAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.university.voting.ui.ManageCandidatesActivity.CandidatesAdapter.CandidateViewHolder> {
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.university.voting.data.CandidateWithUser, kotlin.Unit> onVerify = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.university.voting.data.CandidateWithUser, kotlin.Unit> onDelete = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.university.voting.data.CandidateWithUser, kotlin.Unit> onDetails = null;
        @org.jetbrains.annotations.NotNull()
        private java.util.List<com.university.voting.data.CandidateWithUser> candidates;
        
        public CandidatesAdapter(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.university.voting.data.CandidateWithUser, kotlin.Unit> onVerify, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.university.voting.data.CandidateWithUser, kotlin.Unit> onDelete, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.university.voting.data.CandidateWithUser, kotlin.Unit> onDetails) {
            super();
        }
        
        public final void setCandidates(@org.jetbrains.annotations.NotNull()
        java.util.List<com.university.voting.data.CandidateWithUser> newCandidates) {
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.university.voting.ui.ManageCandidatesActivity.CandidatesAdapter.CandidateViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.university.voting.ui.ManageCandidatesActivity.CandidatesAdapter.CandidateViewHolder holder, int position) {
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \b*\u0004\u0018\u00010\f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \b*\u0004\u0018\u00010\f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n \b*\u0004\u0018\u00010\f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/university/voting/ui/ManageCandidatesActivity$CandidatesAdapter$CandidateViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "<init>", "(Lcom/university/voting/ui/ManageCandidatesActivity$CandidatesAdapter;Landroid/view/View;)V", "tvName", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "tvPosition", "tvStatus", "btnVerify", "Landroid/widget/Button;", "btnDelete", "btnDetails", "bind", "", "item", "Lcom/university/voting/data/CandidateWithUser;", "app_release"})
        public final class CandidateViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            private final android.widget.TextView tvName = null;
            private final android.widget.TextView tvPosition = null;
            private final android.widget.TextView tvStatus = null;
            private final android.widget.Button btnVerify = null;
            private final android.widget.Button btnDelete = null;
            private final android.widget.Button btnDetails = null;
            
            public CandidateViewHolder(@org.jetbrains.annotations.NotNull()
            android.view.View view) {
                super(null);
            }
            
            public final void bind(@org.jetbrains.annotations.NotNull()
            com.university.voting.data.CandidateWithUser item) {
            }
        }
    }
}