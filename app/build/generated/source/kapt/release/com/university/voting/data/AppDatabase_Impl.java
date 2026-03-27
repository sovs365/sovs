package com.university.voting.data;

import androidx.annotation.NonNull;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UserDao _userDao;

  private volatile ElectionDao _electionDao;

  private volatile PositionDao _positionDao;

  private volatile CandidateDao _candidateDao;

  private volatile VoteDao _voteDao;

  private volatile FacultyDao _facultyDao;

  private volatile CourseDao _courseDao;

  private volatile SubjectCombinationDao _subjectCombinationDao;

  @Override
  @NonNull
  protected RoomOpenDelegate createOpenDelegate() {
    final RoomOpenDelegate _openDelegate = new RoomOpenDelegate(15, "ede3059ec5d00c8c6574bbbc84f0ab62", "a6065902d478aaecc88829a5c1737bab") {
      @Override
      public void createAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `users` (`userId` TEXT NOT NULL, `username` TEXT NOT NULL, `regNo` TEXT, `fullName` TEXT NOT NULL, `email` TEXT, `faculty` TEXT, `course` TEXT, `subjectCombination` TEXT, `levelOfStudy` TEXT, `yearOfStudy` TEXT, `gender` TEXT, `disability` TEXT, `passwordHash` TEXT NOT NULL, `role` TEXT NOT NULL, `manifesto` TEXT, `cvPath` TEXT, `declarationConsentPath` TEXT, `schoolIdPath` TEXT, `academicClearancePath` TEXT, `signedCodeOfConductPath` TEXT, `passportPhotoPath` TEXT, `profilePhotoPath` TEXT, `phoneNumber` TEXT NOT NULL, `isVerified` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `loginAttempts` INTEGER NOT NULL, `isLocked` INTEGER NOT NULL, PRIMARY KEY(`userId`))");
        SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_users_username` ON `users` (`username`)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `elections` (`electionId` TEXT NOT NULL, `positionId` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `startDate` INTEGER NOT NULL, `endDate` INTEGER NOT NULL, `status` TEXT NOT NULL, PRIMARY KEY(`electionId`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `positions` (`positionId` TEXT NOT NULL, `name` TEXT NOT NULL, `type` TEXT NOT NULL, `facultyId` TEXT, `courseId` TEXT, `subjectCombinationId` TEXT, `yearOfStudy` TEXT, PRIMARY KEY(`positionId`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `candidates` (`candidateId` TEXT NOT NULL, `positionId` TEXT NOT NULL, `userId` TEXT NOT NULL, `manifesto` TEXT NOT NULL, PRIMARY KEY(`candidateId`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `votes` (`voteId` TEXT NOT NULL, `electionId` TEXT NOT NULL, `positionId` TEXT NOT NULL, `voterId` TEXT NOT NULL, `candidateId` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `hash` TEXT NOT NULL, PRIMARY KEY(`voteId`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `faculties` (`facultyId` TEXT NOT NULL, `name` TEXT NOT NULL, PRIMARY KEY(`facultyId`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `courses` (`courseId` TEXT NOT NULL, `facultyId` TEXT NOT NULL, `name` TEXT NOT NULL, PRIMARY KEY(`courseId`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `subject_combinations` (`id` TEXT NOT NULL, `courseId` TEXT NOT NULL, `name` TEXT NOT NULL, PRIMARY KEY(`id`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'ede3059ec5d00c8c6574bbbc84f0ab62')");
      }

      @Override
      public void dropAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `users`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `elections`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `positions`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `candidates`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `votes`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `faculties`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `courses`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `subject_combinations`");
      }

      @Override
      public void onCreate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      public void onOpen(@NonNull final SQLiteConnection connection) {
        internalInitInvalidationTracker(connection);
      }

      @Override
      public void onPreMigrate(@NonNull final SQLiteConnection connection) {
        DBUtil.dropFtsSyncTriggers(connection);
      }

      @Override
      public void onPostMigrate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      @NonNull
      public RoomOpenDelegate.ValidationResult onValidateSchema(
          @NonNull final SQLiteConnection connection) {
        final Map<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(27);
        _columnsUsers.put("userId", new TableInfo.Column("userId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("username", new TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("regNo", new TableInfo.Column("regNo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("fullName", new TableInfo.Column("fullName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("faculty", new TableInfo.Column("faculty", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("course", new TableInfo.Column("course", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("subjectCombination", new TableInfo.Column("subjectCombination", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("levelOfStudy", new TableInfo.Column("levelOfStudy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("yearOfStudy", new TableInfo.Column("yearOfStudy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("gender", new TableInfo.Column("gender", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("disability", new TableInfo.Column("disability", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("passwordHash", new TableInfo.Column("passwordHash", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("manifesto", new TableInfo.Column("manifesto", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("cvPath", new TableInfo.Column("cvPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("declarationConsentPath", new TableInfo.Column("declarationConsentPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("schoolIdPath", new TableInfo.Column("schoolIdPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("academicClearancePath", new TableInfo.Column("academicClearancePath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("signedCodeOfConductPath", new TableInfo.Column("signedCodeOfConductPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("passportPhotoPath", new TableInfo.Column("passportPhotoPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("profilePhotoPath", new TableInfo.Column("profilePhotoPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("phoneNumber", new TableInfo.Column("phoneNumber", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("isVerified", new TableInfo.Column("isVerified", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("loginAttempts", new TableInfo.Column("loginAttempts", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("isLocked", new TableInfo.Column("isLocked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(1);
        _indicesUsers.add(new TableInfo.Index("index_users_username", true, Arrays.asList("username"), Arrays.asList("ASC")));
        final TableInfo _infoUsers = new TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(connection, "users");
        if (!_infoUsers.equals(_existingUsers)) {
          return new RoomOpenDelegate.ValidationResult(false, "users(com.university.voting.data.User).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final Map<String, TableInfo.Column> _columnsElections = new HashMap<String, TableInfo.Column>(7);
        _columnsElections.put("electionId", new TableInfo.Column("electionId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsElections.put("positionId", new TableInfo.Column("positionId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsElections.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsElections.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsElections.put("startDate", new TableInfo.Column("startDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsElections.put("endDate", new TableInfo.Column("endDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsElections.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysElections = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesElections = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoElections = new TableInfo("elections", _columnsElections, _foreignKeysElections, _indicesElections);
        final TableInfo _existingElections = TableInfo.read(connection, "elections");
        if (!_infoElections.equals(_existingElections)) {
          return new RoomOpenDelegate.ValidationResult(false, "elections(com.university.voting.data.Election).\n"
                  + " Expected:\n" + _infoElections + "\n"
                  + " Found:\n" + _existingElections);
        }
        final Map<String, TableInfo.Column> _columnsPositions = new HashMap<String, TableInfo.Column>(7);
        _columnsPositions.put("positionId", new TableInfo.Column("positionId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPositions.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPositions.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPositions.put("facultyId", new TableInfo.Column("facultyId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPositions.put("courseId", new TableInfo.Column("courseId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPositions.put("subjectCombinationId", new TableInfo.Column("subjectCombinationId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPositions.put("yearOfStudy", new TableInfo.Column("yearOfStudy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysPositions = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesPositions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPositions = new TableInfo("positions", _columnsPositions, _foreignKeysPositions, _indicesPositions);
        final TableInfo _existingPositions = TableInfo.read(connection, "positions");
        if (!_infoPositions.equals(_existingPositions)) {
          return new RoomOpenDelegate.ValidationResult(false, "positions(com.university.voting.data.Position).\n"
                  + " Expected:\n" + _infoPositions + "\n"
                  + " Found:\n" + _existingPositions);
        }
        final Map<String, TableInfo.Column> _columnsCandidates = new HashMap<String, TableInfo.Column>(4);
        _columnsCandidates.put("candidateId", new TableInfo.Column("candidateId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCandidates.put("positionId", new TableInfo.Column("positionId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCandidates.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCandidates.put("manifesto", new TableInfo.Column("manifesto", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysCandidates = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesCandidates = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCandidates = new TableInfo("candidates", _columnsCandidates, _foreignKeysCandidates, _indicesCandidates);
        final TableInfo _existingCandidates = TableInfo.read(connection, "candidates");
        if (!_infoCandidates.equals(_existingCandidates)) {
          return new RoomOpenDelegate.ValidationResult(false, "candidates(com.university.voting.data.Candidate).\n"
                  + " Expected:\n" + _infoCandidates + "\n"
                  + " Found:\n" + _existingCandidates);
        }
        final Map<String, TableInfo.Column> _columnsVotes = new HashMap<String, TableInfo.Column>(7);
        _columnsVotes.put("voteId", new TableInfo.Column("voteId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVotes.put("electionId", new TableInfo.Column("electionId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVotes.put("positionId", new TableInfo.Column("positionId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVotes.put("voterId", new TableInfo.Column("voterId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVotes.put("candidateId", new TableInfo.Column("candidateId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVotes.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVotes.put("hash", new TableInfo.Column("hash", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysVotes = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesVotes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVotes = new TableInfo("votes", _columnsVotes, _foreignKeysVotes, _indicesVotes);
        final TableInfo _existingVotes = TableInfo.read(connection, "votes");
        if (!_infoVotes.equals(_existingVotes)) {
          return new RoomOpenDelegate.ValidationResult(false, "votes(com.university.voting.data.Vote).\n"
                  + " Expected:\n" + _infoVotes + "\n"
                  + " Found:\n" + _existingVotes);
        }
        final Map<String, TableInfo.Column> _columnsFaculties = new HashMap<String, TableInfo.Column>(2);
        _columnsFaculties.put("facultyId", new TableInfo.Column("facultyId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFaculties.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysFaculties = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesFaculties = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFaculties = new TableInfo("faculties", _columnsFaculties, _foreignKeysFaculties, _indicesFaculties);
        final TableInfo _existingFaculties = TableInfo.read(connection, "faculties");
        if (!_infoFaculties.equals(_existingFaculties)) {
          return new RoomOpenDelegate.ValidationResult(false, "faculties(com.university.voting.data.Faculty).\n"
                  + " Expected:\n" + _infoFaculties + "\n"
                  + " Found:\n" + _existingFaculties);
        }
        final Map<String, TableInfo.Column> _columnsCourses = new HashMap<String, TableInfo.Column>(3);
        _columnsCourses.put("courseId", new TableInfo.Column("courseId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("facultyId", new TableInfo.Column("facultyId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysCourses = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesCourses = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCourses = new TableInfo("courses", _columnsCourses, _foreignKeysCourses, _indicesCourses);
        final TableInfo _existingCourses = TableInfo.read(connection, "courses");
        if (!_infoCourses.equals(_existingCourses)) {
          return new RoomOpenDelegate.ValidationResult(false, "courses(com.university.voting.data.Course).\n"
                  + " Expected:\n" + _infoCourses + "\n"
                  + " Found:\n" + _existingCourses);
        }
        final Map<String, TableInfo.Column> _columnsSubjectCombinations = new HashMap<String, TableInfo.Column>(3);
        _columnsSubjectCombinations.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubjectCombinations.put("courseId", new TableInfo.Column("courseId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubjectCombinations.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysSubjectCombinations = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesSubjectCombinations = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSubjectCombinations = new TableInfo("subject_combinations", _columnsSubjectCombinations, _foreignKeysSubjectCombinations, _indicesSubjectCombinations);
        final TableInfo _existingSubjectCombinations = TableInfo.read(connection, "subject_combinations");
        if (!_infoSubjectCombinations.equals(_existingSubjectCombinations)) {
          return new RoomOpenDelegate.ValidationResult(false, "subject_combinations(com.university.voting.data.SubjectCombination).\n"
                  + " Expected:\n" + _infoSubjectCombinations + "\n"
                  + " Found:\n" + _existingSubjectCombinations);
        }
        return new RoomOpenDelegate.ValidationResult(true, null);
      }
    };
    return _openDelegate;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final Map<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final Map<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "users", "elections", "positions", "candidates", "votes", "faculties", "courses", "subject_combinations");
  }

  @Override
  public void clearAllTables() {
    super.performClear(false, "users", "elections", "positions", "candidates", "votes", "faculties", "courses", "subject_combinations");
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final Map<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ElectionDao.class, ElectionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PositionDao.class, PositionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CandidateDao.class, CandidateDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(VoteDao.class, VoteDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FacultyDao.class, FacultyDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CourseDao.class, CourseDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SubjectCombinationDao.class, SubjectCombinationDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final Set<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public ElectionDao electionDao() {
    if (_electionDao != null) {
      return _electionDao;
    } else {
      synchronized(this) {
        if(_electionDao == null) {
          _electionDao = new ElectionDao_Impl(this);
        }
        return _electionDao;
      }
    }
  }

  @Override
  public PositionDao positionDao() {
    if (_positionDao != null) {
      return _positionDao;
    } else {
      synchronized(this) {
        if(_positionDao == null) {
          _positionDao = new PositionDao_Impl(this);
        }
        return _positionDao;
      }
    }
  }

  @Override
  public CandidateDao candidateDao() {
    if (_candidateDao != null) {
      return _candidateDao;
    } else {
      synchronized(this) {
        if(_candidateDao == null) {
          _candidateDao = new CandidateDao_Impl(this);
        }
        return _candidateDao;
      }
    }
  }

  @Override
  public VoteDao voteDao() {
    if (_voteDao != null) {
      return _voteDao;
    } else {
      synchronized(this) {
        if(_voteDao == null) {
          _voteDao = new VoteDao_Impl(this);
        }
        return _voteDao;
      }
    }
  }

  @Override
  public FacultyDao facultyDao() {
    if (_facultyDao != null) {
      return _facultyDao;
    } else {
      synchronized(this) {
        if(_facultyDao == null) {
          _facultyDao = new FacultyDao_Impl(this);
        }
        return _facultyDao;
      }
    }
  }

  @Override
  public CourseDao courseDao() {
    if (_courseDao != null) {
      return _courseDao;
    } else {
      synchronized(this) {
        if(_courseDao == null) {
          _courseDao = new CourseDao_Impl(this);
        }
        return _courseDao;
      }
    }
  }

  @Override
  public SubjectCombinationDao subjectCombinationDao() {
    if (_subjectCombinationDao != null) {
      return _subjectCombinationDao;
    } else {
      synchronized(this) {
        if(_subjectCombinationDao == null) {
          _subjectCombinationDao = new SubjectCombinationDao_Impl(this);
        }
        return _subjectCombinationDao;
      }
    }
  }
}
