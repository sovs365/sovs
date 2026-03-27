package com.university.voting.ui;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0010H\u0002J\b\u0010\u0017\u001a\u00020\u0010H\u0002J\b\u0010\u0018\u001a\u00020\u0010H\u0002J\u0010\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\nH\u0002J\b\u0010\u001b\u001a\u00020\u0010H\u0002J\b\u0010\u001c\u001a\u00020\u0010H\u0002J\u0010\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\fH\u0002J\b\u0010\u001f\u001a\u00020\u0010H\u0002J\b\u0010 \u001a\u00020\u0010H\u0002J\u0010\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u000eH\u0002J\b\u0010#\u001a\u00020\u0010H\u0002J,\u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020&2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00100)H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/university/voting/ui/AdminDataManagementActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "binding", "Lcom/university/voting/databinding/ActivityAdminDataManagementBinding;", "viewModel", "Lcom/university/voting/viewmodel/AdminViewModel;", "faculties", "", "Lcom/university/voting/api/FacultyResponse;", "courses", "Lcom/university/voting/api/CourseResponse;", "subjects", "Lcom/university/voting/api/SubjectCombinationResponse;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "loadAllData", "observeViewModel", "updateFacultyUI", "updateCourseUI", "updateSubjectUI", "setupFacultyManagement", "showEditFacultyDialog", "faculty", "loadFaculties", "setupCourseManagement", "showEditCourseDialog", "course", "loadCourses", "setupSubjectCombinationManagement", "showEditSubjectDialog", "subject", "loadSubjectCombinations", "showActionDialog", "type", "", "title", "onAction", "Lkotlin/Function1;", "app_release"})
public final class AdminDataManagementActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.university.voting.databinding.ActivityAdminDataManagementBinding binding;
    private com.university.voting.viewmodel.AdminViewModel viewModel;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.university.voting.api.FacultyResponse> faculties;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.university.voting.api.CourseResponse> courses;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.university.voting.api.SubjectCombinationResponse> subjects;
    
    public AdminDataManagementActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadAllData() {
    }
    
    private final void observeViewModel() {
    }
    
    private final void updateFacultyUI() {
    }
    
    private final void updateCourseUI() {
    }
    
    private final void updateSubjectUI() {
    }
    
    private final void setupFacultyManagement() {
    }
    
    private final void showEditFacultyDialog(com.university.voting.api.FacultyResponse faculty) {
    }
    
    private final void loadFaculties() {
    }
    
    private final void setupCourseManagement() {
    }
    
    private final void showEditCourseDialog(com.university.voting.api.CourseResponse course) {
    }
    
    private final void loadCourses() {
    }
    
    private final void setupSubjectCombinationManagement() {
    }
    
    private final void showEditSubjectDialog(com.university.voting.api.SubjectCombinationResponse subject) {
    }
    
    private final void loadSubjectCombinations() {
    }
    
    private final void showActionDialog(java.lang.String type, java.lang.String title, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onAction) {
    }
}