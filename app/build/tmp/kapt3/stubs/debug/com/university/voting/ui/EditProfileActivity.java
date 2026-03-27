package com.university.voting.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\b\u0010\u0010\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/university/voting/ui/EditProfileActivity;", "Lcom/university/voting/ui/BaseActivity;", "()V", "binding", "Lcom/university/voting/databinding/ActivityEditProfileBinding;", "currentUser", "Lcom/university/voting/api/UserResponse;", "repository", "Lcom/university/voting/repository/VotingRepository;", "token", "", "loadUserData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "saveProfile", "app_debug"})
public final class EditProfileActivity extends com.university.voting.ui.BaseActivity {
    private com.university.voting.databinding.ActivityEditProfileBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final com.university.voting.repository.VotingRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String token = "";
    @org.jetbrains.annotations.Nullable()
    private com.university.voting.api.UserResponse currentUser;
    
    public EditProfileActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadUserData() {
    }
    
    private final void saveProfile() {
    }
}