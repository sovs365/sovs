package com.university.voting.ui;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001#B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0010H\u0002J\u000e\u0010\u0017\u001a\u00020\u0018H\u0082@\u00a2\u0006\u0002\u0010\u0019J \u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\rH\u0002J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\rH\u0002J\u0018\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\rH\u0002J\u0012\u0010\"\u001a\u0004\u0018\u00010\r2\u0006\u0010!\u001a\u00020\u001fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/university/voting/ui/RegisterVerifyCodeActivity;", "Lcom/university/voting/ui/BaseActivity;", "<init>", "()V", "binding", "Lcom/university/voting/databinding/ActivityRegisterVerifyCodeBinding;", "db", "Lcom/university/voting/data/AppDatabase;", "emailService", "Lcom/university/voting/services/VerificationCodeService;", "authViewModel", "Lcom/university/voting/viewmodel/AuthViewModel;", "serverCode", "", "userEmail", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "observeViewModel", "sendVerificationEmail", "verifyCode", "completeRegistration", "saveCandidateDocuments", "Lcom/university/voting/ui/RegisterVerifyCodeActivity$CandidateDocumentPaths;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveIntentUriToInternalPath", "key", "targetFileName", "label", "getIntentUriOrThrow", "Landroid/net/Uri;", "saveUriToInternalPath", "uri", "getFileExtension", "CandidateDocumentPaths", "app_release"})
public final class RegisterVerifyCodeActivity extends com.university.voting.ui.BaseActivity {
    private com.university.voting.databinding.ActivityRegisterVerifyCodeBinding binding;
    private com.university.voting.data.AppDatabase db;
    private com.university.voting.services.VerificationCodeService emailService;
    private com.university.voting.viewmodel.AuthViewModel authViewModel;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String serverCode = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String userEmail = "";
    
    public RegisterVerifyCodeActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void observeViewModel() {
    }
    
    private final void sendVerificationEmail() {
    }
    
    private final void verifyCode() {
    }
    
    private final void completeRegistration() {
    }
    
    private final java.lang.Object saveCandidateDocuments(kotlin.coroutines.Continuation<? super com.university.voting.ui.RegisterVerifyCodeActivity.CandidateDocumentPaths> $completion) {
        return null;
    }
    
    private final java.lang.String saveIntentUriToInternalPath(java.lang.String key, java.lang.String targetFileName, java.lang.String label) {
        return null;
    }
    
    private final android.net.Uri getIntentUriOrThrow(java.lang.String key, java.lang.String label) {
        return null;
    }
    
    private final java.lang.String saveUriToInternalPath(android.net.Uri uri, java.lang.String targetFileName) {
        return null;
    }
    
    private final java.lang.String getFileExtension(android.net.Uri uri) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003JE\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f\u00a8\u0006\u001f"}, d2 = {"Lcom/university/voting/ui/RegisterVerifyCodeActivity$CandidateDocumentPaths;", "", "cvPath", "", "declarationPath", "schoolIdPath", "academicClearancePath", "codeOfConductPath", "passportPath", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCvPath", "()Ljava/lang/String;", "getDeclarationPath", "getSchoolIdPath", "getAcademicClearancePath", "getCodeOfConductPath", "getPassportPath", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"})
    static final class CandidateDocumentPaths {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String cvPath = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String declarationPath = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String schoolIdPath = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String academicClearancePath = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String codeOfConductPath = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String passportPath = null;
        
        public CandidateDocumentPaths(@org.jetbrains.annotations.NotNull()
        java.lang.String cvPath, @org.jetbrains.annotations.NotNull()
        java.lang.String declarationPath, @org.jetbrains.annotations.NotNull()
        java.lang.String schoolIdPath, @org.jetbrains.annotations.NotNull()
        java.lang.String academicClearancePath, @org.jetbrains.annotations.NotNull()
        java.lang.String codeOfConductPath, @org.jetbrains.annotations.NotNull()
        java.lang.String passportPath) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getCvPath() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDeclarationPath() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSchoolIdPath() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAcademicClearancePath() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getCodeOfConductPath() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPassportPath() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.university.voting.ui.RegisterVerifyCodeActivity.CandidateDocumentPaths copy(@org.jetbrains.annotations.NotNull()
        java.lang.String cvPath, @org.jetbrains.annotations.NotNull()
        java.lang.String declarationPath, @org.jetbrains.annotations.NotNull()
        java.lang.String schoolIdPath, @org.jetbrains.annotations.NotNull()
        java.lang.String academicClearancePath, @org.jetbrains.annotations.NotNull()
        java.lang.String codeOfConductPath, @org.jetbrains.annotations.NotNull()
        java.lang.String passportPath) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}