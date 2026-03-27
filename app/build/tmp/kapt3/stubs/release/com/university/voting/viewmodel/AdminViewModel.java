package com.university.voting.viewmodel;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0019\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\b\u0010.\u001a\u00020(H\u0002J\u0006\u0010/\u001a\u000200J\u0016\u00101\u001a\u0002002\u0006\u00102\u001a\u00020(2\u0006\u00103\u001a\u00020,J\u000e\u00104\u001a\u0002002\u0006\u00102\u001a\u00020(J\u000e\u00105\u001a\u0002002\u0006\u00102\u001a\u00020(J\u0006\u00106\u001a\u000200J\u000e\u00107\u001a\u0002002\u0006\u00108\u001a\u00020(J\u0006\u00109\u001a\u000200J\u000e\u0010:\u001a\u0002002\u0006\u0010;\u001a\u00020(J\u0016\u0010<\u001a\u0002002\u0006\u0010;\u001a\u00020(2\u0006\u00108\u001a\u00020(J\u0006\u0010=\u001a\u000200J\u0016\u0010>\u001a\u0002002\u0006\u0010?\u001a\u00020(2\u0006\u00108\u001a\u00020(J\u0016\u0010@\u001a\u0002002\u0006\u0010;\u001a\u00020(2\u0006\u00108\u001a\u00020(J\u000e\u0010A\u001a\u0002002\u0006\u0010;\u001a\u00020(J\u001e\u0010B\u001a\u0002002\u0006\u0010?\u001a\u00020(2\u0006\u0010;\u001a\u00020(2\u0006\u00108\u001a\u00020(J\u000e\u0010C\u001a\u0002002\u0006\u0010?\u001a\u00020(J\u001e\u0010D\u001a\u0002002\u0006\u0010E\u001a\u00020(2\u0006\u0010?\u001a\u00020(2\u0006\u00108\u001a\u00020(J\u000e\u0010F\u001a\u0002002\u0006\u0010E\u001a\u00020(J\u0006\u0010G\u001a\u000200J\u0006\u0010H\u001a\u000200R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\r0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\r0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u001a\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\r0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012R\u001a\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\r0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0012R\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020$0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0012R\u0014\u0010\'\u001a\b\u0012\u0004\u0012\u00020(0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020(0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0012R\u001c\u0010+\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010,0,0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020,0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0012\u00a8\u0006I"}, d2 = {"Lcom/university/voting/viewmodel/AdminViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "<init>", "(Landroid/app/Application;)V", "repository", "Lcom/university/voting/repository/VotingRepository;", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "_users", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/university/voting/api/UserResponse;", "users", "Landroidx/lifecycle/LiveData;", "getUsers", "()Landroidx/lifecycle/LiveData;", "_faculties", "Lcom/university/voting/api/FacultyResponse;", "faculties", "getFaculties", "_courses", "Lcom/university/voting/api/CourseResponse;", "courses", "getCourses", "_subjectCombinations", "Lcom/university/voting/api/SubjectCombinationResponse;", "subjectCombinations", "getSubjectCombinations", "_positions", "Lcom/university/voting/api/PositionResponse;", "positions", "getPositions", "_reports", "Lcom/university/voting/api/ReportsResponse;", "reports", "getReports", "_message", "", "message", "getMessage", "_isLoading", "", "isLoading", "getToken", "loadAllUsers", "", "verifyUser", "userId", "verified", "deleteUser", "unlockUser", "loadFaculties", "createFaculty", "name", "loadCourses", "loadCoursesByFaculty", "facultyId", "createCourse", "loadSubjectCombinations", "createSubjectCombination", "courseId", "updateFaculty", "deleteFaculty", "updateCourse", "deleteCourse", "updateSubjectCombination", "subjectId", "deleteSubjectCombination", "loadAllPositions", "loadReports", "app_release"})
public final class AdminViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.university.voting.repository.VotingRepository repository = null;
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.university.voting.api.UserResponse>> _users = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.UserResponse>> users = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.university.voting.api.FacultyResponse>> _faculties = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.FacultyResponse>> faculties = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.university.voting.api.CourseResponse>> _courses = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.CourseResponse>> courses = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.university.voting.api.SubjectCombinationResponse>> _subjectCombinations = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.SubjectCombinationResponse>> subjectCombinations = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.university.voting.api.PositionResponse>> _positions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.PositionResponse>> positions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.university.voting.api.ReportsResponse> _reports = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.university.voting.api.ReportsResponse> reports = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _message = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> message = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    
    public AdminViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.UserResponse>> getUsers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.FacultyResponse>> getFaculties() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.CourseResponse>> getCourses() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.SubjectCombinationResponse>> getSubjectCombinations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.university.voting.api.PositionResponse>> getPositions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.university.voting.api.ReportsResponse> getReports() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading() {
        return null;
    }
    
    private final java.lang.String getToken() {
        return null;
    }
    
    public final void loadAllUsers() {
    }
    
    public final void verifyUser(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, boolean verified) {
    }
    
    public final void deleteUser(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
    }
    
    public final void unlockUser(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
    }
    
    public final void loadFaculties() {
    }
    
    public final void createFaculty(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void loadCourses() {
    }
    
    public final void loadCoursesByFaculty(@org.jetbrains.annotations.NotNull()
    java.lang.String facultyId) {
    }
    
    public final void createCourse(@org.jetbrains.annotations.NotNull()
    java.lang.String facultyId, @org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void loadSubjectCombinations() {
    }
    
    public final void createSubjectCombination(@org.jetbrains.annotations.NotNull()
    java.lang.String courseId, @org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void updateFaculty(@org.jetbrains.annotations.NotNull()
    java.lang.String facultyId, @org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void deleteFaculty(@org.jetbrains.annotations.NotNull()
    java.lang.String facultyId) {
    }
    
    public final void updateCourse(@org.jetbrains.annotations.NotNull()
    java.lang.String courseId, @org.jetbrains.annotations.NotNull()
    java.lang.String facultyId, @org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void deleteCourse(@org.jetbrains.annotations.NotNull()
    java.lang.String courseId) {
    }
    
    public final void updateSubjectCombination(@org.jetbrains.annotations.NotNull()
    java.lang.String subjectId, @org.jetbrains.annotations.NotNull()
    java.lang.String courseId, @org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void deleteSubjectCombination(@org.jetbrains.annotations.NotNull()
    java.lang.String subjectId) {
    }
    
    public final void loadAllPositions() {
    }
    
    public final void loadReports() {
    }
}