package com.university.voting.ui;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u000fH\u0002J\b\u0010\u0013\u001a\u00020\u000fH\u0002J\b\u0010\u0014\u001a\u00020\u000fH\u0002J\u0010\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/university/voting/ui/LoginVerifyCodeActivity;", "Lcom/university/voting/ui/BaseActivity;", "<init>", "()V", "binding", "Lcom/university/voting/databinding/ActivityLoginVerifyCodeBinding;", "db", "Lcom/university/voting/data/AppDatabase;", "emailService", "Lcom/university/voting/services/VerificationCodeService;", "serverCode", "", "userId", "userEmail", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "sendLoginVerificationEmail", "verifyLoginCode", "completeLogin", "resolveVerificationEmail", "user", "Lcom/university/voting/data/User;", "navigateToDashboard", "role", "Companion", "app_release"})
public final class LoginVerifyCodeActivity extends com.university.voting.ui.BaseActivity {
    private com.university.voting.databinding.ActivityLoginVerifyCodeBinding binding;
    private com.university.voting.data.AppDatabase db;
    private com.university.voting.services.VerificationCodeService emailService;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String serverCode = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String userId = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String userEmail = "";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String LEGACY_ADMIN_OTP_EMAIL = "sovs.ac.ke@gmail.com";
    @org.jetbrains.annotations.NotNull()
    public static final com.university.voting.ui.LoginVerifyCodeActivity.Companion Companion = null;
    
    public LoginVerifyCodeActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void sendLoginVerificationEmail() {
    }
    
    private final void verifyLoginCode() {
    }
    
    private final void completeLogin() {
    }
    
    private final java.lang.String resolveVerificationEmail(com.university.voting.data.User user) {
        return null;
    }
    
    private final void navigateToDashboard(java.lang.String role) {
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/university/voting/ui/LoginVerifyCodeActivity$Companion;", "", "<init>", "()V", "LEGACY_ADMIN_OTP_EMAIL", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}