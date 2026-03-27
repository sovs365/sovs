package com.university.voting.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\b\u0010\u0011\u001a\u00020\u000fH\u0002J\u0012\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\u0010\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\u0010\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\u0010\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/university/voting/ui/ManageUsersActivity;", "Lcom/university/voting/ui/BaseActivity;", "()V", "listView", "Landroid/widget/ListView;", "progressBar", "Landroid/widget/ProgressBar;", "repository", "Lcom/university/voting/repository/VotingRepository;", "token", "", "users", "", "Lcom/university/voting/api/UserResponse;", "confirmDelete", "", "user", "loadUsers", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showEditUserDialog", "showUserActions", "showUserDetails", "toggleVerification", "unlockUser", "app_debug"})
public final class ManageUsersActivity extends com.university.voting.ui.BaseActivity {
    private android.widget.ListView listView;
    private android.widget.ProgressBar progressBar;
    @org.jetbrains.annotations.NotNull()
    private final com.university.voting.repository.VotingRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.university.voting.api.UserResponse> users;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String token = "";
    
    public ManageUsersActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadUsers() {
    }
    
    private final void showUserActions(com.university.voting.api.UserResponse user) {
    }
    
    private final void showUserDetails(com.university.voting.api.UserResponse user) {
    }
    
    private final void showEditUserDialog(com.university.voting.api.UserResponse user) {
    }
    
    private final void toggleVerification(com.university.voting.api.UserResponse user) {
    }
    
    private final void unlockUser(com.university.voting.api.UserResponse user) {
    }
    
    private final void confirmDelete(com.university.voting.api.UserResponse user) {
    }
}