package com.university.voting.data;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0013H&\u00a8\u0006\u0015"}, d2 = {"Lcom/university/voting/data/AppDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "userDao", "Lcom/university/voting/data/UserDao;", "electionDao", "Lcom/university/voting/data/ElectionDao;", "positionDao", "Lcom/university/voting/data/PositionDao;", "candidateDao", "Lcom/university/voting/data/CandidateDao;", "voteDao", "Lcom/university/voting/data/VoteDao;", "facultyDao", "Lcom/university/voting/data/FacultyDao;", "courseDao", "Lcom/university/voting/data/CourseDao;", "subjectCombinationDao", "Lcom/university/voting/data/SubjectCombinationDao;", "Companion", "app_release"})
@androidx.room.Database(entities = {com.university.voting.data.User.class, com.university.voting.data.Election.class, com.university.voting.data.Position.class, com.university.voting.data.Candidate.class, com.university.voting.data.Vote.class, com.university.voting.data.Faculty.class, com.university.voting.data.Course.class, com.university.voting.data.SubjectCombination.class}, version = 15, exportSchema = false)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.university.voting.data.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.university.voting.data.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.university.voting.data.UserDao userDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.university.voting.data.ElectionDao electionDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.university.voting.data.PositionDao positionDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.university.voting.data.CandidateDao candidateDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.university.voting.data.VoteDao voteDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.university.voting.data.FacultyDao facultyDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.university.voting.data.CourseDao courseDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.university.voting.data.SubjectCombinationDao subjectCombinationDao();
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/university/voting/data/AppDatabase$Companion;", "", "<init>", "()V", "INSTANCE", "Lcom/university/voting/data/AppDatabase;", "getDatabase", "context", "Landroid/content/Context;", "seedAdmin", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.university.voting.data.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        private final void seedAdmin(androidx.sqlite.db.SupportSQLiteDatabase db) {
        }
    }
}