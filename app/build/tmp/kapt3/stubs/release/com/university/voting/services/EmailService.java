package com.university.voting.services;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0007H\u0002\u00a8\u0006\u000e"}, d2 = {"Lcom/university/voting/services/EmailService;", "", "<init>", "()V", "sendVerificationCode", "", "recipientEmail", "", "code", "type", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isInvalidSmtpValue", "value", "Companion", "app_release"})
public final class EmailService {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "EmailService";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REGISTRATION = "registration";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN = "login";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PASSWORD_RESET = "password_reset";
    @org.jetbrains.annotations.NotNull()
    public static final com.university.voting.services.EmailService.Companion Companion = null;
    
    public EmailService() {
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
    
    private final boolean isInvalidSmtpValue(java.lang.String value) {
        return false;
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/university/voting/services/EmailService$Companion;", "", "<init>", "()V", "TAG", "", "REGISTRATION", "LOGIN", "PASSWORD_RESET", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}