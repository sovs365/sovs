package com.university.voting.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bH\u0002J\b\u0010\u000f\u001a\u00020\rH\u0002J\u0012\u0010\u0010\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/university/voting/ui/LoginVerifyCodeActivity;", "Lcom/university/voting/ui/BaseActivity;", "()V", "authViewModel", "Lcom/university/voting/viewmodel/AuthViewModel;", "binding", "Lcom/university/voting/databinding/ActivityLoginVerifyCodeBinding;", "userEmail", "", "extractErrorMessage", "error", "", "navigateToDashboard", "", "role", "observeViewModel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "verifyLoginCode", "app_debug"})
public final class LoginVerifyCodeActivity extends com.university.voting.ui.BaseActivity {
    private com.university.voting.databinding.ActivityLoginVerifyCodeBinding binding;
    private com.university.voting.viewmodel.AuthViewModel authViewModel;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String userEmail = "";
    
    public LoginVerifyCodeActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void observeViewModel() {
    }
    
    private final void verifyLoginCode() {
    }
    
    private final void navigateToDashboard(java.lang.String role) {
    }
    
    private final java.lang.String extractErrorMessage(java.lang.Throwable error) {
        return null;
    }
}