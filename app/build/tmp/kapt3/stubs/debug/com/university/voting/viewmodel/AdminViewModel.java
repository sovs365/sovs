package com.university.voting.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u001a\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000fJ\u000e\u00101\u001a\u00020.2\u0006\u00100\u001a\u00020\u000fJ\u0016\u00102\u001a\u00020.2\u0006\u00103\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000fJ\u000e\u00104\u001a\u00020.2\u0006\u00103\u001a\u00020\u000fJ\u000e\u00105\u001a\u00020.2\u0006\u0010/\u001a\u00020\u000fJ\u000e\u00106\u001a\u00020.2\u0006\u00107\u001a\u00020\u000fJ\u000e\u00108\u001a\u00020.2\u0006\u00109\u001a\u00020\u000fJ\b\u0010:\u001a\u00020\u000fH\u0002J\u0006\u0010;\u001a\u00020.J\u0006\u0010<\u001a\u00020.J\u0006\u0010=\u001a\u00020.J\u000e\u0010>\u001a\u00020.2\u0006\u0010/\u001a\u00020\u000fJ\u0006\u0010?\u001a\u00020.J\u0006\u0010@\u001a\u00020.J\u0006\u0010A\u001a\u00020.J\u000e\u0010B\u001a\u00020.2\u0006\u00109\u001a\u00020\u000fJ\u001e\u0010C\u001a\u00020.2\u0006\u00103\u001a\u00020\u000f2\u0006\u0010/\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000fJ\u0016\u0010D\u001a\u00020.2\u0006\u0010/\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000fJ\u001e\u0010E\u001a\u00020.2\u0006\u00107\u001a\u00020\u000f2\u0006\u00103\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000fJ\u0016\u0010F\u001a\u00020.2\u0006\u00109\u001a\u00020\u000f2\u0006\u0010G\u001a\u00020\fR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001bR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001bR\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00070\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u0016\u0010#\u001a\n \r*\u0004\u0018\u00010$0$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00130\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001bR\u000e\u0010\'\u001a\u00020(X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00070\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001bR\u001d\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00070\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001b\u00a8\u0006H"}, d2 = {"Lcom/university/voting/viewmodel/AdminViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_courses", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/university/voting/api/CourseResponse;", "_faculties", "Lcom/university/voting/api/FacultyResponse;", "_isLoading", "", "kotlin.jvm.PlatformType", "_message", "", "_positions", "Lcom/university/voting/api/PositionResponse;", "_reports", "Lcom/university/voting/api/ReportsResponse;", "_subjectCombinations", "Lcom/university/voting/api/SubjectCombinationResponse;", "_users", "Lcom/university/voting/api/UserResponse;", "courses", "Landroidx/lifecycle/LiveData;", "getCourses", "()Landroidx/lifecycle/LiveData;", "faculties", "getFaculties", "isLoading", "message", "getMessage", "positions", "getPositions", "prefs", "Landroid/content/SharedPreferences;", "reports", "getReports", "repository", "Lcom/university/voting/repository/VotingRepository;", "subjectCombinations", "getSubjectCombinations", "users", "getUsers", "createCourse", "", "facultyId", "name", "createFaculty", "createSubjectCombination", "courseId", "deleteCourse", "deleteFaculty", "deleteSubjectCombination", "subjectId", "deleteUser", "userId", "getToken", "loadAllPositions", "loadAllUsers", "loadCourses", "loadCoursesByFaculty", "loadFaculties", "loadReports", "loadSubjectCombinations", "unlockUser", "updateCourse", "updateFaculty", "updateSubjectCombination", "verifyUser", "verified", "app_debug"})
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