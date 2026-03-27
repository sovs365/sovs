package com.university.voting.ui;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u000eH\u0002J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\fH\u0002J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\fH\u0002J\u0010\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\fH\u0002J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\fH\u0002J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\fH\u0002J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/university/voting/ui/ManageUsersActivity;", "Lcom/university/voting/ui/BaseActivity;", "<init>", "()V", "listView", "Landroid/widget/ListView;", "progressBar", "Landroid/widget/ProgressBar;", "db", "Lcom/university/voting/data/AppDatabase;", "users", "", "Lcom/university/voting/data/User;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "loadUsers", "showUserActions", "user", "showUserDetails", "showEditUserDialog", "toggleVerification", "unlockUser", "confirmDelete", "app_release"})
public final class ManageUsersActivity extends com.university.voting.ui.BaseActivity {
    private android.widget.ListView listView;
    private android.widget.ProgressBar progressBar;
    private com.university.voting.data.AppDatabase db;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.university.voting.data.User> users;
    
    public ManageUsersActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadUsers() {
    }
    
    private final void showUserActions(com.university.voting.data.User user) {
    }
    
    private final void showUserDetails(com.university.voting.data.User user) {
    }
    
    private final void showEditUserDialog(com.university.voting.data.User user) {
    }
    
    private final void toggleVerification(com.university.voting.data.User user) {
    }
    
    private final void unlockUser(com.university.voting.data.User user) {
    }
    
    private final void confirmDelete(com.university.voting.data.User user) {
    }
}