package com.university.voting.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u000eH\u0002J\u0012\u0010\u0013\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/university/voting/ui/RegisterVerifyCodeActivity;", "Lcom/university/voting/ui/BaseActivity;", "()V", "authViewModel", "Lcom/university/voting/viewmodel/AuthViewModel;", "binding", "Lcom/university/voting/databinding/ActivityRegisterVerifyCodeBinding;", "isCodeVerified", "", "repository", "Lcom/university/voting/repository/VotingRepository;", "userEmail", "", "completeRegistration", "", "extractErrorMessage", "error", "", "observeViewModel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "verifyCode", "app_debug"})
public final class RegisterVerifyCodeActivity extends com.university.voting.ui.BaseActivity {
    private com.university.voting.databinding.ActivityRegisterVerifyCodeBinding binding;
    private com.university.voting.viewmodel.AuthViewModel authViewModel;
    @org.jetbrains.annotations.NotNull()
    private final com.university.voting.repository.VotingRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String userEmail = "";
    private boolean isCodeVerified = false;
    
    public RegisterVerifyCodeActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void observeViewModel() {
    }
    
    private final void verifyCode() {
    }
    
    private final void completeRegistration() {
    }
    
    private final java.lang.String extractErrorMessage(java.lang.Throwable error) {
        return null;
    }
}