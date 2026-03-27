package com.university.voting.data;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class CandidateDao_Impl implements CandidateDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Candidate> __insertAdapterOfCandidate;

  private final EntityDeleteOrUpdateAdapter<Candidate> __deleteAdapterOfCandidate;

  private final EntityDeleteOrUpdateAdapter<Candidate> __updateAdapterOfCandidate;

  public CandidateDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfCandidate = new EntityInsertAdapter<Candidate>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `candidates` (`candidateId`,`positionId`,`userId`,`manifesto`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Candidate entity) {
        if (entity.getCandidateId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getCandidateId());
        }
        if (entity.getPositionId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getPositionId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getUserId());
        }
        if (entity.getManifesto() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getManifesto());
        }
      }
    };
    this.__deleteAdapterOfCandidate = new EntityDeleteOrUpdateAdapter<Candidate>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `candidates` WHERE `candidateId` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Candidate entity) {
        if (entity.getCandidateId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getCandidateId());
        }
      }
    };
    this.__updateAdapterOfCandidate = new EntityDeleteOrUpdateAdapter<Candidate>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `candidates` SET `candidateId` = ?,`positionId` = ?,`userId` = ?,`manifesto` = ? WHERE `candidateId` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Candidate entity) {
        if (entity.getCandidateId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getCandidateId());
        }
        if (entity.getPositionId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getPositionId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getUserId());
        }
        if (entity.getManifesto() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getManifesto());
        }
        if (entity.getCandidateId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getCandidateId());
        }
      }
    };
  }

  @Override
  public Object insert(final Candidate candidate, final Continuation<? super Unit> $completion) {
    if (candidate == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfCandidate.insert(_connection, candidate);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object delete(final Candidate candidate, final Continuation<? super Unit> $completion) {
    if (candidate == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfCandidate.handle(_connection, candidate);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object update(final Candidate candidate, final Continuation<? super Unit> $completion) {
    if (candidate == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfCandidate.handle(_connection, candidate);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getCandidatesForPosition(final String positionId,
      final Continuation<? super List<Candidate>> $completion) {
    final String _sql = "SELECT * FROM candidates WHERE positionId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (positionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, positionId);
        }
        final int _columnIndexOfCandidateId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidateId");
        final int _columnIndexOfPositionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionId");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfManifesto = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "manifesto");
        final List<Candidate> _result = new ArrayList<Candidate>();
        while (_stmt.step()) {
          final Candidate _item;
          final String _tmpCandidateId;
          if (_stmt.isNull(_columnIndexOfCandidateId)) {
            _tmpCandidateId = null;
          } else {
            _tmpCandidateId = _stmt.getText(_columnIndexOfCandidateId);
          }
          final String _tmpPositionId;
          if (_stmt.isNull(_columnIndexOfPositionId)) {
            _tmpPositionId = null;
          } else {
            _tmpPositionId = _stmt.getText(_columnIndexOfPositionId);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpManifesto;
          if (_stmt.isNull(_columnIndexOfManifesto)) {
            _tmpManifesto = null;
          } else {
            _tmpManifesto = _stmt.getText(_columnIndexOfManifesto);
          }
          _item = new Candidate(_tmpCandidateId,_tmpPositionId,_tmpUserId,_tmpManifesto);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getCandidate(final String candidateId,
      final Continuation<? super Candidate> $completion) {
    final String _sql = "SELECT * FROM candidates WHERE candidateId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (candidateId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, candidateId);
        }
        final int _columnIndexOfCandidateId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidateId");
        final int _columnIndexOfPositionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionId");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfManifesto = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "manifesto");
        final Candidate _result;
        if (_stmt.step()) {
          final String _tmpCandidateId;
          if (_stmt.isNull(_columnIndexOfCandidateId)) {
            _tmpCandidateId = null;
          } else {
            _tmpCandidateId = _stmt.getText(_columnIndexOfCandidateId);
          }
          final String _tmpPositionId;
          if (_stmt.isNull(_columnIndexOfPositionId)) {
            _tmpPositionId = null;
          } else {
            _tmpPositionId = _stmt.getText(_columnIndexOfPositionId);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpManifesto;
          if (_stmt.isNull(_columnIndexOfManifesto)) {
            _tmpManifesto = null;
          } else {
            _tmpManifesto = _stmt.getText(_columnIndexOfManifesto);
          }
          _result = new Candidate(_tmpCandidateId,_tmpPositionId,_tmpUserId,_tmpManifesto);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getAllCandidatesWithUser(
      final Continuation<? super List<CandidateWithUser>> $completion) {
    final String _sql = "\n"
            + "        SELECT c.*, \n"
            + "               u.userId as user_userId, u.username as user_username, u.regNo as user_regNo, \n"
            + "               u.fullName as user_fullName, u.faculty as user_faculty, u.course as user_course, \n"
            + "               u.subjectCombination as user_subjectCombination, u.levelOfStudy as user_levelOfStudy, \n"
            + "               u.yearOfStudy as user_yearOfStudy, u.gender as user_gender, u.disability as user_disability, \n"
            + "               u.passwordHash as user_passwordHash, u.role as user_role, \n"
            + "               u.manifesto as user_manifesto, u.cvPath as user_cvPath, \n"
            + "               u.declarationConsentPath as user_declarationConsentPath, u.schoolIdPath as user_schoolIdPath, \n"
            + "               u.academicClearancePath as user_academicClearancePath, u.signedCodeOfConductPath as user_signedCodeOfConductPath, \n"
            + "               u.passportPhotoPath as user_passportPhotoPath,\n"
            + "               u.isVerified as user_isVerified, u.createdAt as user_createdAt, \n"
            + "               u.phoneNumber as user_phoneNumber, u.loginAttempts as user_loginAttempts, \n"
            + "               u.isLocked as user_isLocked \n"
            + "        FROM candidates c \n"
            + "        JOIN users u ON c.userId = u.userId\n"
            + "    ";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfCandidateId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidateId");
        final int _columnIndexOfPositionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionId");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfManifesto = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "manifesto");
        final int _columnIndexOfUserId_1 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_userId");
        final int _columnIndexOfUsername = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_username");
        final int _columnIndexOfRegNo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_regNo");
        final int _columnIndexOfFullName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_fullName");
        final int _columnIndexOfFaculty = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_faculty");
        final int _columnIndexOfCourse = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_course");
        final int _columnIndexOfSubjectCombination = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_subjectCombination");
        final int _columnIndexOfLevelOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_levelOfStudy");
        final int _columnIndexOfYearOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_yearOfStudy");
        final int _columnIndexOfGender = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_gender");
        final int _columnIndexOfDisability = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_disability");
        final int _columnIndexOfPasswordHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_passwordHash");
        final int _columnIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_role");
        final int _columnIndexOfManifesto_1 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_manifesto");
        final int _columnIndexOfCvPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_cvPath");
        final int _columnIndexOfDeclarationConsentPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_declarationConsentPath");
        final int _columnIndexOfSchoolIdPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_schoolIdPath");
        final int _columnIndexOfAcademicClearancePath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_academicClearancePath");
        final int _columnIndexOfSignedCodeOfConductPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_signedCodeOfConductPath");
        final int _columnIndexOfPassportPhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_passportPhotoPath");
        final int _columnIndexOfIsVerified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_isVerified");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_createdAt");
        final int _columnIndexOfPhoneNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_phoneNumber");
        final int _columnIndexOfLoginAttempts = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_loginAttempts");
        final int _columnIndexOfIsLocked = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_isLocked");
        final List<CandidateWithUser> _result = new ArrayList<CandidateWithUser>();
        while (_stmt.step()) {
          final CandidateWithUser _item;
          final Candidate _tmpCandidate;
          final String _tmpCandidateId;
          if (_stmt.isNull(_columnIndexOfCandidateId)) {
            _tmpCandidateId = null;
          } else {
            _tmpCandidateId = _stmt.getText(_columnIndexOfCandidateId);
          }
          final String _tmpPositionId;
          if (_stmt.isNull(_columnIndexOfPositionId)) {
            _tmpPositionId = null;
          } else {
            _tmpPositionId = _stmt.getText(_columnIndexOfPositionId);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpManifesto;
          if (_stmt.isNull(_columnIndexOfManifesto)) {
            _tmpManifesto = null;
          } else {
            _tmpManifesto = _stmt.getText(_columnIndexOfManifesto);
          }
          _tmpCandidate = new Candidate(_tmpCandidateId,_tmpPositionId,_tmpUserId,_tmpManifesto);
          final User _tmpUser;
          final String _tmpUserId_1;
          if (_stmt.isNull(_columnIndexOfUserId_1)) {
            _tmpUserId_1 = null;
          } else {
            _tmpUserId_1 = _stmt.getText(_columnIndexOfUserId_1);
          }
          final String _tmpUsername;
          if (_stmt.isNull(_columnIndexOfUsername)) {
            _tmpUsername = null;
          } else {
            _tmpUsername = _stmt.getText(_columnIndexOfUsername);
          }
          final String _tmpRegNo;
          if (_stmt.isNull(_columnIndexOfRegNo)) {
            _tmpRegNo = null;
          } else {
            _tmpRegNo = _stmt.getText(_columnIndexOfRegNo);
          }
          final String _tmpFullName;
          if (_stmt.isNull(_columnIndexOfFullName)) {
            _tmpFullName = null;
          } else {
            _tmpFullName = _stmt.getText(_columnIndexOfFullName);
          }
          final String _tmpFaculty;
          if (_stmt.isNull(_columnIndexOfFaculty)) {
            _tmpFaculty = null;
          } else {
            _tmpFaculty = _stmt.getText(_columnIndexOfFaculty);
          }
          final String _tmpCourse;
          if (_stmt.isNull(_columnIndexOfCourse)) {
            _tmpCourse = null;
          } else {
            _tmpCourse = _stmt.getText(_columnIndexOfCourse);
          }
          final String _tmpSubjectCombination;
          if (_stmt.isNull(_columnIndexOfSubjectCombination)) {
            _tmpSubjectCombination = null;
          } else {
            _tmpSubjectCombination = _stmt.getText(_columnIndexOfSubjectCombination);
          }
          final String _tmpLevelOfStudy;
          if (_stmt.isNull(_columnIndexOfLevelOfStudy)) {
            _tmpLevelOfStudy = null;
          } else {
            _tmpLevelOfStudy = _stmt.getText(_columnIndexOfLevelOfStudy);
          }
          final String _tmpYearOfStudy;
          if (_stmt.isNull(_columnIndexOfYearOfStudy)) {
            _tmpYearOfStudy = null;
          } else {
            _tmpYearOfStudy = _stmt.getText(_columnIndexOfYearOfStudy);
          }
          final String _tmpGender;
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null;
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender);
          }
          final String _tmpDisability;
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null;
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability);
          }
          final String _tmpPasswordHash;
          if (_stmt.isNull(_columnIndexOfPasswordHash)) {
            _tmpPasswordHash = null;
          } else {
            _tmpPasswordHash = _stmt.getText(_columnIndexOfPasswordHash);
          }
          final String _tmpRole;
          if (_stmt.isNull(_columnIndexOfRole)) {
            _tmpRole = null;
          } else {
            _tmpRole = _stmt.getText(_columnIndexOfRole);
          }
          final String _tmpManifesto_1;
          if (_stmt.isNull(_columnIndexOfManifesto_1)) {
            _tmpManifesto_1 = null;
          } else {
            _tmpManifesto_1 = _stmt.getText(_columnIndexOfManifesto_1);
          }
          final String _tmpCvPath;
          if (_stmt.isNull(_columnIndexOfCvPath)) {
            _tmpCvPath = null;
          } else {
            _tmpCvPath = _stmt.getText(_columnIndexOfCvPath);
          }
          final String _tmpDeclarationConsentPath;
          if (_stmt.isNull(_columnIndexOfDeclarationConsentPath)) {
            _tmpDeclarationConsentPath = null;
          } else {
            _tmpDeclarationConsentPath = _stmt.getText(_columnIndexOfDeclarationConsentPath);
          }
          final String _tmpSchoolIdPath;
          if (_stmt.isNull(_columnIndexOfSchoolIdPath)) {
            _tmpSchoolIdPath = null;
          } else {
            _tmpSchoolIdPath = _stmt.getText(_columnIndexOfSchoolIdPath);
          }
          final String _tmpAcademicClearancePath;
          if (_stmt.isNull(_columnIndexOfAcademicClearancePath)) {
            _tmpAcademicClearancePath = null;
          } else {
            _tmpAcademicClearancePath = _stmt.getText(_columnIndexOfAcademicClearancePath);
          }
          final String _tmpSignedCodeOfConductPath;
          if (_stmt.isNull(_columnIndexOfSignedCodeOfConductPath)) {
            _tmpSignedCodeOfConductPath = null;
          } else {
            _tmpSignedCodeOfConductPath = _stmt.getText(_columnIndexOfSignedCodeOfConductPath);
          }
          final String _tmpPassportPhotoPath;
          if (_stmt.isNull(_columnIndexOfPassportPhotoPath)) {
            _tmpPassportPhotoPath = null;
          } else {
            _tmpPassportPhotoPath = _stmt.getText(_columnIndexOfPassportPhotoPath);
          }
          final boolean _tmpIsVerified;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsVerified));
          _tmpIsVerified = _tmp != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final String _tmpPhoneNumber;
          if (_stmt.isNull(_columnIndexOfPhoneNumber)) {
            _tmpPhoneNumber = null;
          } else {
            _tmpPhoneNumber = _stmt.getText(_columnIndexOfPhoneNumber);
          }
          final int _tmpLoginAttempts;
          _tmpLoginAttempts = (int) (_stmt.getLong(_columnIndexOfLoginAttempts));
          final boolean _tmpIsLocked;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsLocked));
          _tmpIsLocked = _tmp_1 != 0;
          _tmpUser = new User(_tmpUserId_1,_tmpUsername,_tmpRegNo,_tmpFullName,null,_tmpFaculty,_tmpCourse,_tmpSubjectCombination,_tmpLevelOfStudy,_tmpYearOfStudy,_tmpGender,_tmpDisability,_tmpPasswordHash,_tmpRole,_tmpManifesto_1,_tmpCvPath,_tmpDeclarationConsentPath,_tmpSchoolIdPath,_tmpAcademicClearancePath,_tmpSignedCodeOfConductPath,_tmpPassportPhotoPath,null,_tmpPhoneNumber,_tmpIsVerified,_tmpCreatedAt,_tmpLoginAttempts,_tmpIsLocked);
          _item = new CandidateWithUser(_tmpCandidate,_tmpUser);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getCandidatesWithUserForPosition(final String positionId,
      final Continuation<? super List<CandidateWithUser>> $completion) {
    final String _sql = "\n"
            + "        SELECT c.*, \n"
            + "               u.userId as user_userId, u.username as user_username, u.regNo as user_regNo, \n"
            + "               u.fullName as user_fullName, u.faculty as user_faculty, u.course as user_course, \n"
            + "               u.subjectCombination as user_subjectCombination, u.levelOfStudy as user_levelOfStudy, \n"
            + "               u.yearOfStudy as user_yearOfStudy, u.gender as user_gender, u.disability as user_disability, \n"
            + "               u.passwordHash as user_passwordHash, u.role as user_role, \n"
            + "               u.manifesto as user_manifesto, u.cvPath as user_cvPath, \n"
            + "               u.declarationConsentPath as user_declarationConsentPath, u.schoolIdPath as user_schoolIdPath, \n"
            + "               u.academicClearancePath as user_academicClearancePath, u.signedCodeOfConductPath as user_signedCodeOfConductPath, \n"
            + "               u.passportPhotoPath as user_passportPhotoPath,\n"
            + "               u.isVerified as user_isVerified, u.createdAt as user_createdAt, \n"
            + "               u.phoneNumber as user_phoneNumber, u.loginAttempts as user_loginAttempts, \n"
            + "               u.isLocked as user_isLocked \n"
            + "        FROM candidates c \n"
            + "        JOIN users u ON c.userId = u.userId \n"
            + "        WHERE c.positionId = ?\n"
            + "    ";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (positionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, positionId);
        }
        final int _columnIndexOfCandidateId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidateId");
        final int _columnIndexOfPositionId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionId");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfManifesto = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "manifesto");
        final int _columnIndexOfUserId_1 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_userId");
        final int _columnIndexOfUsername = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_username");
        final int _columnIndexOfRegNo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_regNo");
        final int _columnIndexOfFullName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_fullName");
        final int _columnIndexOfFaculty = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_faculty");
        final int _columnIndexOfCourse = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_course");
        final int _columnIndexOfSubjectCombination = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_subjectCombination");
        final int _columnIndexOfLevelOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_levelOfStudy");
        final int _columnIndexOfYearOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_yearOfStudy");
        final int _columnIndexOfGender = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_gender");
        final int _columnIndexOfDisability = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_disability");
        final int _columnIndexOfPasswordHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_passwordHash");
        final int _columnIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_role");
        final int _columnIndexOfManifesto_1 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_manifesto");
        final int _columnIndexOfCvPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_cvPath");
        final int _columnIndexOfDeclarationConsentPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_declarationConsentPath");
        final int _columnIndexOfSchoolIdPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_schoolIdPath");
        final int _columnIndexOfAcademicClearancePath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_academicClearancePath");
        final int _columnIndexOfSignedCodeOfConductPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_signedCodeOfConductPath");
        final int _columnIndexOfPassportPhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_passportPhotoPath");
        final int _columnIndexOfIsVerified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_isVerified");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_createdAt");
        final int _columnIndexOfPhoneNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_phoneNumber");
        final int _columnIndexOfLoginAttempts = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_loginAttempts");
        final int _columnIndexOfIsLocked = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "user_isLocked");
        final List<CandidateWithUser> _result = new ArrayList<CandidateWithUser>();
        while (_stmt.step()) {
          final CandidateWithUser _item;
          final Candidate _tmpCandidate;
          final String _tmpCandidateId;
          if (_stmt.isNull(_columnIndexOfCandidateId)) {
            _tmpCandidateId = null;
          } else {
            _tmpCandidateId = _stmt.getText(_columnIndexOfCandidateId);
          }
          final String _tmpPositionId;
          if (_stmt.isNull(_columnIndexOfPositionId)) {
            _tmpPositionId = null;
          } else {
            _tmpPositionId = _stmt.getText(_columnIndexOfPositionId);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpManifesto;
          if (_stmt.isNull(_columnIndexOfManifesto)) {
            _tmpManifesto = null;
          } else {
            _tmpManifesto = _stmt.getText(_columnIndexOfManifesto);
          }
          _tmpCandidate = new Candidate(_tmpCandidateId,_tmpPositionId,_tmpUserId,_tmpManifesto);
          final User _tmpUser;
          final String _tmpUserId_1;
          if (_stmt.isNull(_columnIndexOfUserId_1)) {
            _tmpUserId_1 = null;
          } else {
            _tmpUserId_1 = _stmt.getText(_columnIndexOfUserId_1);
          }
          final String _tmpUsername;
          if (_stmt.isNull(_columnIndexOfUsername)) {
            _tmpUsername = null;
          } else {
            _tmpUsername = _stmt.getText(_columnIndexOfUsername);
          }
          final String _tmpRegNo;
          if (_stmt.isNull(_columnIndexOfRegNo)) {
            _tmpRegNo = null;
          } else {
            _tmpRegNo = _stmt.getText(_columnIndexOfRegNo);
          }
          final String _tmpFullName;
          if (_stmt.isNull(_columnIndexOfFullName)) {
            _tmpFullName = null;
          } else {
            _tmpFullName = _stmt.getText(_columnIndexOfFullName);
          }
          final String _tmpFaculty;
          if (_stmt.isNull(_columnIndexOfFaculty)) {
            _tmpFaculty = null;
          } else {
            _tmpFaculty = _stmt.getText(_columnIndexOfFaculty);
          }
          final String _tmpCourse;
          if (_stmt.isNull(_columnIndexOfCourse)) {
            _tmpCourse = null;
          } else {
            _tmpCourse = _stmt.getText(_columnIndexOfCourse);
          }
          final String _tmpSubjectCombination;
          if (_stmt.isNull(_columnIndexOfSubjectCombination)) {
            _tmpSubjectCombination = null;
          } else {
            _tmpSubjectCombination = _stmt.getText(_columnIndexOfSubjectCombination);
          }
          final String _tmpLevelOfStudy;
          if (_stmt.isNull(_columnIndexOfLevelOfStudy)) {
            _tmpLevelOfStudy = null;
          } else {
            _tmpLevelOfStudy = _stmt.getText(_columnIndexOfLevelOfStudy);
          }
          final String _tmpYearOfStudy;
          if (_stmt.isNull(_columnIndexOfYearOfStudy)) {
            _tmpYearOfStudy = null;
          } else {
            _tmpYearOfStudy = _stmt.getText(_columnIndexOfYearOfStudy);
          }
          final String _tmpGender;
          if (_stmt.isNull(_columnIndexOfGender)) {
            _tmpGender = null;
          } else {
            _tmpGender = _stmt.getText(_columnIndexOfGender);
          }
          final String _tmpDisability;
          if (_stmt.isNull(_columnIndexOfDisability)) {
            _tmpDisability = null;
          } else {
            _tmpDisability = _stmt.getText(_columnIndexOfDisability);
          }
          final String _tmpPasswordHash;
          if (_stmt.isNull(_columnIndexOfPasswordHash)) {
            _tmpPasswordHash = null;
          } else {
            _tmpPasswordHash = _stmt.getText(_columnIndexOfPasswordHash);
          }
          final String _tmpRole;
          if (_stmt.isNull(_columnIndexOfRole)) {
            _tmpRole = null;
          } else {
            _tmpRole = _stmt.getText(_columnIndexOfRole);
          }
          final String _tmpManifesto_1;
          if (_stmt.isNull(_columnIndexOfManifesto_1)) {
            _tmpManifesto_1 = null;
          } else {
            _tmpManifesto_1 = _stmt.getText(_columnIndexOfManifesto_1);
          }
          final String _tmpCvPath;
          if (_stmt.isNull(_columnIndexOfCvPath)) {
            _tmpCvPath = null;
          } else {
            _tmpCvPath = _stmt.getText(_columnIndexOfCvPath);
          }
          final String _tmpDeclarationConsentPath;
          if (_stmt.isNull(_columnIndexOfDeclarationConsentPath)) {
            _tmpDeclarationConsentPath = null;
          } else {
            _tmpDeclarationConsentPath = _stmt.getText(_columnIndexOfDeclarationConsentPath);
          }
          final String _tmpSchoolIdPath;
          if (_stmt.isNull(_columnIndexOfSchoolIdPath)) {
            _tmpSchoolIdPath = null;
          } else {
            _tmpSchoolIdPath = _stmt.getText(_columnIndexOfSchoolIdPath);
          }
          final String _tmpAcademicClearancePath;
          if (_stmt.isNull(_columnIndexOfAcademicClearancePath)) {
            _tmpAcademicClearancePath = null;
          } else {
            _tmpAcademicClearancePath = _stmt.getText(_columnIndexOfAcademicClearancePath);
          }
          final String _tmpSignedCodeOfConductPath;
          if (_stmt.isNull(_columnIndexOfSignedCodeOfConductPath)) {
            _tmpSignedCodeOfConductPath = null;
          } else {
            _tmpSignedCodeOfConductPath = _stmt.getText(_columnIndexOfSignedCodeOfConductPath);
          }
          final String _tmpPassportPhotoPath;
          if (_stmt.isNull(_columnIndexOfPassportPhotoPath)) {
            _tmpPassportPhotoPath = null;
          } else {
            _tmpPassportPhotoPath = _stmt.getText(_columnIndexOfPassportPhotoPath);
          }
          final boolean _tmpIsVerified;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsVerified));
          _tmpIsVerified = _tmp != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final String _tmpPhoneNumber;
          if (_stmt.isNull(_columnIndexOfPhoneNumber)) {
            _tmpPhoneNumber = null;
          } else {
            _tmpPhoneNumber = _stmt.getText(_columnIndexOfPhoneNumber);
          }
          final int _tmpLoginAttempts;
          _tmpLoginAttempts = (int) (_stmt.getLong(_columnIndexOfLoginAttempts));
          final boolean _tmpIsLocked;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsLocked));
          _tmpIsLocked = _tmp_1 != 0;
          _tmpUser = new User(_tmpUserId_1,_tmpUsername,_tmpRegNo,_tmpFullName,null,_tmpFaculty,_tmpCourse,_tmpSubjectCombination,_tmpLevelOfStudy,_tmpYearOfStudy,_tmpGender,_tmpDisability,_tmpPasswordHash,_tmpRole,_tmpManifesto_1,_tmpCvPath,_tmpDeclarationConsentPath,_tmpSchoolIdPath,_tmpAcademicClearancePath,_tmpSignedCodeOfConductPath,_tmpPassportPhotoPath,null,_tmpPhoneNumber,_tmpIsVerified,_tmpCreatedAt,_tmpLoginAttempts,_tmpIsLocked);
          _item = new CandidateWithUser(_tmpCandidate,_tmpUser);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
