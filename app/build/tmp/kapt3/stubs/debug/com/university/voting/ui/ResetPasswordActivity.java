package com.university.voting.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J(\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/university/voting/ui/ResetPasswordActivity;", "Lcom/university/voting/ui/BaseActivity;", "()V", "binding", "Lcom/university/voting/databinding/ActivityResetPasswordBinding;", "repository", "Lcom/university/voting/repository/VotingRepository;", "extractErrorMessage", "", "error", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "updatePassword", "email", "code", "newPassword", "confirmPassword", "app_debug"})
public final class ResetPasswordActivity extends com.university.voting.ui.BaseActivity {
    private com.university.voting.databinding.ActivityResetPasswordBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final com.university.voting.repository.VotingRepository repository = null;
    
    public ResetPasswordActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void updatePassword(java.lang.String email, java.lang.String code, java.lang.String newPassword, java.lang.String confirmPassword) {
    }
    
    private final java.lang.String extractErrorMessage(java.lang.Throwable error) {
        return null;
    }
}