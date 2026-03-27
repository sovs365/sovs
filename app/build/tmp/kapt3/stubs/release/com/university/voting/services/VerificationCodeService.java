package com.university.voting.services;

/**
 * EmailService for handling verification code emails
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u0010J \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0002J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rJ\u0016\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rJ\u0010\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\rH\u0002J\b\u0010\u0017\u001a\u00020\u000bH\u0002J\u0010\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/university/voting/services/VerificationCodeService;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "sharedPref", "Landroid/content/SharedPreferences;", "emailService", "Lcom/university/voting/services/EmailService;", "sendVerificationCode", "", "recipientEmail", "", "code", "type", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "storeCodeLocally", "", "email", "getStoredCode", "clearStoredCode", "normalizeEmailKey", "isSmtpConfigured", "isInvalidSmtpValue", "value", "Companion", "app_release"})
public final class VerificationCodeService {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences sharedPref = null;
    @org.jetbrains.annotations.NotNull()
    private final com.university.voting.services.EmailService emailService = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REGISTRATION = "registration";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN = "login";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PASSWORD_RESET = "password_reset";
    @org.jetbrains.annotations.NotNull()
    public static final com.university.voting.services.VerificationCodeService.Companion Companion = null;
    
    public VerificationCodeService(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sendVerificationCode(@org.jetbrains.annotations.NotNull()
    java.lang.String recipientEmail, @org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    private final void storeCodeLocally(java.lang.String email, java.lang.String code, java.lang.String type) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getStoredCode(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String type) {
        return null;
    }
    
    public final void clearStoredCode(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String type) {
    }
    
    private final java.lang.String normalizeEmailKey(java.lang.String email) {
        return null;
    }
    
    private final boolean isSmtpConfigured() {
        return false;
    }
    
    private final boolean isInvalidSmtpValue(java.lang.String value) {
        return false;
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/university/voting/services/VerificationCodeService$Companion;", "", "<init>", "()V", "REGISTRATION", "", "LOGIN", "PASSWORD_RESET", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}