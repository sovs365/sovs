package com.university.voting.data;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import java.lang.Class;
import java.lang.Integer;
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
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<User> __insertAdapterOfUser;

  private final EntityDeleteOrUpdateAdapter<User> __deleteAdapterOfUser;

  private final EntityDeleteOrUpdateAdapter<User> __updateAdapterOfUser;

  public UserDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfUser = new EntityInsertAdapter<User>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `users` (`userId`,`username`,`regNo`,`fullName`,`email`,`faculty`,`course`,`subjectCombination`,`levelOfStudy`,`yearOfStudy`,`gender`,`disability`,`passwordHash`,`role`,`manifesto`,`cvPath`,`declarationConsentPath`,`schoolIdPath`,`academicClearancePath`,`signedCodeOfConductPath`,`passportPhotoPath`,`profilePhotoPath`,`phoneNumber`,`isVerified`,`createdAt`,`loginAttempts`,`isLocked`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final User entity) {
        if (entity.getUserId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getUserId());
        }
        if (entity.getUsername() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getUsername());
        }
        if (entity.getRegNo() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getRegNo());
        }
        if (entity.getFullName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getFullName());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getEmail());
        }
        if (entity.getFaculty() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getFaculty());
        }
        if (entity.getCourse() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getCourse());
        }
        if (entity.getSubjectCombination() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getSubjectCombination());
        }
        if (entity.getLevelOfStudy() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getLevelOfStudy());
        }
        if (entity.getYearOfStudy() == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, entity.getYearOfStudy());
        }
        if (entity.getGender() == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, entity.getGender());
        }
        if (entity.getDisability() == null) {
          statement.bindNull(12);
        } else {
          statement.bindText(12, entity.getDisability());
        }
        if (entity.getPasswordHash() == null) {
          statement.bindNull(13);
        } else {
          statement.bindText(13, entity.getPasswordHash());
        }
        if (entity.getRole() == null) {
          statement.bindNull(14);
        } else {
          statement.bindText(14, entity.getRole());
        }
        if (entity.getManifesto() == null) {
          statement.bindNull(15);
        } else {
          statement.bindText(15, entity.getManifesto());
        }
        if (entity.getCvPath() == null) {
          statement.bindNull(16);
        } else {
          statement.bindText(16, entity.getCvPath());
        }
        if (entity.getDeclarationConsentPath() == null) {
          statement.bindNull(17);
        } else {
          statement.bindText(17, entity.getDeclarationConsentPath());
        }
        if (entity.getSchoolIdPath() == null) {
          statement.bindNull(18);
        } else {
          statement.bindText(18, entity.getSchoolIdPath());
        }
        if (entity.getAcademicClearancePath() == null) {
          statement.bindNull(19);
        } else {
          statement.bindText(19, entity.getAcademicClearancePath());
        }
        if (entity.getSignedCodeOfConductPath() == null) {
          statement.bindNull(20);
        } else {
          statement.bindText(20, entity.getSignedCodeOfConductPath());
        }
        if (entity.getPassportPhotoPath() == null) {
          statement.bindNull(21);
        } else {
          statement.bindText(21, entity.getPassportPhotoPath());
        }
        if (entity.getProfilePhotoPath() == null) {
          statement.bindNull(22);
        } else {
          statement.bindText(22, entity.getProfilePhotoPath());
        }
        if (entity.getPhoneNumber() == null) {
          statement.bindNull(23);
        } else {
          statement.bindText(23, entity.getPhoneNumber());
        }
        final int _tmp = entity.isVerified() ? 1 : 0;
        statement.bindLong(24, _tmp);
        statement.bindLong(25, entity.getCreatedAt());
        statement.bindLong(26, entity.getLoginAttempts());
        final int _tmp_1 = entity.isLocked() ? 1 : 0;
        statement.bindLong(27, _tmp_1);
      }
    };
    this.__deleteAdapterOfUser = new EntityDeleteOrUpdateAdapter<User>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `users` WHERE `userId` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final User entity) {
        if (entity.getUserId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getUserId());
        }
      }
    };
    this.__updateAdapterOfUser = new EntityDeleteOrUpdateAdapter<User>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `users` SET `userId` = ?,`username` = ?,`regNo` = ?,`fullName` = ?,`email` = ?,`faculty` = ?,`course` = ?,`subjectCombination` = ?,`levelOfStudy` = ?,`yearOfStudy` = ?,`gender` = ?,`disability` = ?,`passwordHash` = ?,`role` = ?,`manifesto` = ?,`cvPath` = ?,`declarationConsentPath` = ?,`schoolIdPath` = ?,`academicClearancePath` = ?,`signedCodeOfConductPath` = ?,`passportPhotoPath` = ?,`profilePhotoPath` = ?,`phoneNumber` = ?,`isVerified` = ?,`createdAt` = ?,`loginAttempts` = ?,`isLocked` = ? WHERE `userId` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final User entity) {
        if (entity.getUserId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getUserId());
        }
        if (entity.getUsername() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getUsername());
        }
        if (entity.getRegNo() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getRegNo());
        }
        if (entity.getFullName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getFullName());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getEmail());
        }
        if (entity.getFaculty() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getFaculty());
        }
        if (entity.getCourse() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getCourse());
        }
        if (entity.getSubjectCombination() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getSubjectCombination());
        }
        if (entity.getLevelOfStudy() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getLevelOfStudy());
        }
        if (entity.getYearOfStudy() == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, entity.getYearOfStudy());
        }
        if (entity.getGender() == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, entity.getGender());
        }
        if (entity.getDisability() == null) {
          statement.bindNull(12);
        } else {
          statement.bindText(12, entity.getDisability());
        }
        if (entity.getPasswordHash() == null) {
          statement.bindNull(13);
        } else {
          statement.bindText(13, entity.getPasswordHash());
        }
        if (entity.getRole() == null) {
          statement.bindNull(14);
        } else {
          statement.bindText(14, entity.getRole());
        }
        if (entity.getManifesto() == null) {
          statement.bindNull(15);
        } else {
          statement.bindText(15, entity.getManifesto());
        }
        if (entity.getCvPath() == null) {
          statement.bindNull(16);
        } else {
          statement.bindText(16, entity.getCvPath());
        }
        if (entity.getDeclarationConsentPath() == null) {
          statement.bindNull(17);
        } else {
          statement.bindText(17, entity.getDeclarationConsentPath());
        }
        if (entity.getSchoolIdPath() == null) {
          statement.bindNull(18);
        } else {
          statement.bindText(18, entity.getSchoolIdPath());
        }
        if (entity.getAcademicClearancePath() == null) {
          statement.bindNull(19);
        } else {
          statement.bindText(19, entity.getAcademicClearancePath());
        }
        if (entity.getSignedCodeOfConductPath() == null) {
          statement.bindNull(20);
        } else {
          statement.bindText(20, entity.getSignedCodeOfConductPath());
        }
        if (entity.getPassportPhotoPath() == null) {
          statement.bindNull(21);
        } else {
          statement.bindText(21, entity.getPassportPhotoPath());
        }
        if (entity.getProfilePhotoPath() == null) {
          statement.bindNull(22);
        } else {
          statement.bindText(22, entity.getProfilePhotoPath());
        }
        if (entity.getPhoneNumber() == null) {
          statement.bindNull(23);
        } else {
          statement.bindText(23, entity.getPhoneNumber());
        }
        final int _tmp = entity.isVerified() ? 1 : 0;
        statement.bindLong(24, _tmp);
        statement.bindLong(25, entity.getCreatedAt());
        statement.bindLong(26, entity.getLoginAttempts());
        final int _tmp_1 = entity.isLocked() ? 1 : 0;
        statement.bindLong(27, _tmp_1);
        if (entity.getUserId() == null) {
          statement.bindNull(28);
        } else {
          statement.bindText(28, entity.getUserId());
        }
      }
    };
  }

  @Override
  public Object insert(final User user, final Continuation<? super Unit> $completion) {
    if (user == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfUser.insert(_connection, user);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object delete(final User user, final Continuation<? super Unit> $completion) {
    if (user == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfUser.handle(_connection, user);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object update(final User user, final Continuation<? super Unit> $completion) {
    if (user == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfUser.handle(_connection, user);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getUser(final String userId, final Continuation<? super User> $completion) {
    final String _sql = "SELECT * FROM users WHERE userId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfUsername = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "username");
        final int _columnIndexOfRegNo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "regNo");
        final int _columnIndexOfFullName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "fullName");
        final int _columnIndexOfEmail = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "email");
        final int _columnIndexOfFaculty = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "faculty");
        final int _columnIndexOfCourse = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "course");
        final int _columnIndexOfSubjectCombination = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subjectCombination");
        final int _columnIndexOfLevelOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "levelOfStudy");
        final int _columnIndexOfYearOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "yearOfStudy");
        final int _columnIndexOfGender = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "gender");
        final int _columnIndexOfDisability = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "disability");
        final int _columnIndexOfPasswordHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "passwordHash");
        final int _columnIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "role");
        final int _columnIndexOfManifesto = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "manifesto");
        final int _columnIndexOfCvPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cvPath");
        final int _columnIndexOfDeclarationConsentPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "declarationConsentPath");
        final int _columnIndexOfSchoolIdPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "schoolIdPath");
        final int _columnIndexOfAcademicClearancePath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "academicClearancePath");
        final int _columnIndexOfSignedCodeOfConductPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "signedCodeOfConductPath");
        final int _columnIndexOfPassportPhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "passportPhotoPath");
        final int _columnIndexOfProfilePhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profilePhotoPath");
        final int _columnIndexOfPhoneNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phoneNumber");
        final int _columnIndexOfIsVerified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isVerified");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfLoginAttempts = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "loginAttempts");
        final int _columnIndexOfIsLocked = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isLocked");
        final User _result;
        if (_stmt.step()) {
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
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
          final String _tmpEmail;
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null;
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail);
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
          final String _tmpManifesto;
          if (_stmt.isNull(_columnIndexOfManifesto)) {
            _tmpManifesto = null;
          } else {
            _tmpManifesto = _stmt.getText(_columnIndexOfManifesto);
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
          final String _tmpProfilePhotoPath;
          if (_stmt.isNull(_columnIndexOfProfilePhotoPath)) {
            _tmpProfilePhotoPath = null;
          } else {
            _tmpProfilePhotoPath = _stmt.getText(_columnIndexOfProfilePhotoPath);
          }
          final String _tmpPhoneNumber;
          if (_stmt.isNull(_columnIndexOfPhoneNumber)) {
            _tmpPhoneNumber = null;
          } else {
            _tmpPhoneNumber = _stmt.getText(_columnIndexOfPhoneNumber);
          }
          final boolean _tmpIsVerified;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsVerified));
          _tmpIsVerified = _tmp != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final int _tmpLoginAttempts;
          _tmpLoginAttempts = (int) (_stmt.getLong(_columnIndexOfLoginAttempts));
          final boolean _tmpIsLocked;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsLocked));
          _tmpIsLocked = _tmp_1 != 0;
          _result = new User(_tmpUserId,_tmpUsername,_tmpRegNo,_tmpFullName,_tmpEmail,_tmpFaculty,_tmpCourse,_tmpSubjectCombination,_tmpLevelOfStudy,_tmpYearOfStudy,_tmpGender,_tmpDisability,_tmpPasswordHash,_tmpRole,_tmpManifesto,_tmpCvPath,_tmpDeclarationConsentPath,_tmpSchoolIdPath,_tmpAcademicClearancePath,_tmpSignedCodeOfConductPath,_tmpPassportPhotoPath,_tmpProfilePhotoPath,_tmpPhoneNumber,_tmpIsVerified,_tmpCreatedAt,_tmpLoginAttempts,_tmpIsLocked);
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
  public Object getUserByRegNo(final String regNo, final Continuation<? super User> $completion) {
    final String _sql = "SELECT * FROM users WHERE regNo = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (regNo == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, regNo);
        }
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfUsername = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "username");
        final int _columnIndexOfRegNo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "regNo");
        final int _columnIndexOfFullName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "fullName");
        final int _columnIndexOfEmail = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "email");
        final int _columnIndexOfFaculty = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "faculty");
        final int _columnIndexOfCourse = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "course");
        final int _columnIndexOfSubjectCombination = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subjectCombination");
        final int _columnIndexOfLevelOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "levelOfStudy");
        final int _columnIndexOfYearOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "yearOfStudy");
        final int _columnIndexOfGender = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "gender");
        final int _columnIndexOfDisability = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "disability");
        final int _columnIndexOfPasswordHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "passwordHash");
        final int _columnIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "role");
        final int _columnIndexOfManifesto = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "manifesto");
        final int _columnIndexOfCvPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cvPath");
        final int _columnIndexOfDeclarationConsentPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "declarationConsentPath");
        final int _columnIndexOfSchoolIdPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "schoolIdPath");
        final int _columnIndexOfAcademicClearancePath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "academicClearancePath");
        final int _columnIndexOfSignedCodeOfConductPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "signedCodeOfConductPath");
        final int _columnIndexOfPassportPhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "passportPhotoPath");
        final int _columnIndexOfProfilePhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profilePhotoPath");
        final int _columnIndexOfPhoneNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phoneNumber");
        final int _columnIndexOfIsVerified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isVerified");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfLoginAttempts = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "loginAttempts");
        final int _columnIndexOfIsLocked = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isLocked");
        final User _result;
        if (_stmt.step()) {
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
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
          final String _tmpEmail;
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null;
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail);
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
          final String _tmpManifesto;
          if (_stmt.isNull(_columnIndexOfManifesto)) {
            _tmpManifesto = null;
          } else {
            _tmpManifesto = _stmt.getText(_columnIndexOfManifesto);
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
          final String _tmpProfilePhotoPath;
          if (_stmt.isNull(_columnIndexOfProfilePhotoPath)) {
            _tmpProfilePhotoPath = null;
          } else {
            _tmpProfilePhotoPath = _stmt.getText(_columnIndexOfProfilePhotoPath);
          }
          final String _tmpPhoneNumber;
          if (_stmt.isNull(_columnIndexOfPhoneNumber)) {
            _tmpPhoneNumber = null;
          } else {
            _tmpPhoneNumber = _stmt.getText(_columnIndexOfPhoneNumber);
          }
          final boolean _tmpIsVerified;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsVerified));
          _tmpIsVerified = _tmp != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final int _tmpLoginAttempts;
          _tmpLoginAttempts = (int) (_stmt.getLong(_columnIndexOfLoginAttempts));
          final boolean _tmpIsLocked;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsLocked));
          _tmpIsLocked = _tmp_1 != 0;
          _result = new User(_tmpUserId,_tmpUsername,_tmpRegNo,_tmpFullName,_tmpEmail,_tmpFaculty,_tmpCourse,_tmpSubjectCombination,_tmpLevelOfStudy,_tmpYearOfStudy,_tmpGender,_tmpDisability,_tmpPasswordHash,_tmpRole,_tmpManifesto,_tmpCvPath,_tmpDeclarationConsentPath,_tmpSchoolIdPath,_tmpAcademicClearancePath,_tmpSignedCodeOfConductPath,_tmpPassportPhotoPath,_tmpProfilePhotoPath,_tmpPhoneNumber,_tmpIsVerified,_tmpCreatedAt,_tmpLoginAttempts,_tmpIsLocked);
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
  public Object getUsersByRole(final String role,
      final Continuation<? super List<User>> $completion) {
    final String _sql = "SELECT * FROM users WHERE role = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (role == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, role);
        }
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfUsername = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "username");
        final int _columnIndexOfRegNo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "regNo");
        final int _columnIndexOfFullName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "fullName");
        final int _columnIndexOfEmail = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "email");
        final int _columnIndexOfFaculty = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "faculty");
        final int _columnIndexOfCourse = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "course");
        final int _columnIndexOfSubjectCombination = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subjectCombination");
        final int _columnIndexOfLevelOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "levelOfStudy");
        final int _columnIndexOfYearOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "yearOfStudy");
        final int _columnIndexOfGender = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "gender");
        final int _columnIndexOfDisability = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "disability");
        final int _columnIndexOfPasswordHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "passwordHash");
        final int _columnIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "role");
        final int _columnIndexOfManifesto = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "manifesto");
        final int _columnIndexOfCvPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cvPath");
        final int _columnIndexOfDeclarationConsentPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "declarationConsentPath");
        final int _columnIndexOfSchoolIdPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "schoolIdPath");
        final int _columnIndexOfAcademicClearancePath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "academicClearancePath");
        final int _columnIndexOfSignedCodeOfConductPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "signedCodeOfConductPath");
        final int _columnIndexOfPassportPhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "passportPhotoPath");
        final int _columnIndexOfProfilePhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profilePhotoPath");
        final int _columnIndexOfPhoneNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phoneNumber");
        final int _columnIndexOfIsVerified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isVerified");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfLoginAttempts = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "loginAttempts");
        final int _columnIndexOfIsLocked = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isLocked");
        final List<User> _result = new ArrayList<User>();
        while (_stmt.step()) {
          final User _item;
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
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
          final String _tmpEmail;
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null;
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail);
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
          final String _tmpManifesto;
          if (_stmt.isNull(_columnIndexOfManifesto)) {
            _tmpManifesto = null;
          } else {
            _tmpManifesto = _stmt.getText(_columnIndexOfManifesto);
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
          final String _tmpProfilePhotoPath;
          if (_stmt.isNull(_columnIndexOfProfilePhotoPath)) {
            _tmpProfilePhotoPath = null;
          } else {
            _tmpProfilePhotoPath = _stmt.getText(_columnIndexOfProfilePhotoPath);
          }
          final String _tmpPhoneNumber;
          if (_stmt.isNull(_columnIndexOfPhoneNumber)) {
            _tmpPhoneNumber = null;
          } else {
            _tmpPhoneNumber = _stmt.getText(_columnIndexOfPhoneNumber);
          }
          final boolean _tmpIsVerified;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsVerified));
          _tmpIsVerified = _tmp != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final int _tmpLoginAttempts;
          _tmpLoginAttempts = (int) (_stmt.getLong(_columnIndexOfLoginAttempts));
          final boolean _tmpIsLocked;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsLocked));
          _tmpIsLocked = _tmp_1 != 0;
          _item = new User(_tmpUserId,_tmpUsername,_tmpRegNo,_tmpFullName,_tmpEmail,_tmpFaculty,_tmpCourse,_tmpSubjectCombination,_tmpLevelOfStudy,_tmpYearOfStudy,_tmpGender,_tmpDisability,_tmpPasswordHash,_tmpRole,_tmpManifesto,_tmpCvPath,_tmpDeclarationConsentPath,_tmpSchoolIdPath,_tmpAcademicClearancePath,_tmpSignedCodeOfConductPath,_tmpPassportPhotoPath,_tmpProfilePhotoPath,_tmpPhoneNumber,_tmpIsVerified,_tmpCreatedAt,_tmpLoginAttempts,_tmpIsLocked);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getUserByUsername(final String username,
      final Continuation<? super User> $completion) {
    final String _sql = "SELECT * FROM users WHERE username = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (username == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, username);
        }
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfUsername = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "username");
        final int _columnIndexOfRegNo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "regNo");
        final int _columnIndexOfFullName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "fullName");
        final int _columnIndexOfEmail = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "email");
        final int _columnIndexOfFaculty = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "faculty");
        final int _columnIndexOfCourse = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "course");
        final int _columnIndexOfSubjectCombination = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subjectCombination");
        final int _columnIndexOfLevelOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "levelOfStudy");
        final int _columnIndexOfYearOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "yearOfStudy");
        final int _columnIndexOfGender = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "gender");
        final int _columnIndexOfDisability = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "disability");
        final int _columnIndexOfPasswordHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "passwordHash");
        final int _columnIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "role");
        final int _columnIndexOfManifesto = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "manifesto");
        final int _columnIndexOfCvPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cvPath");
        final int _columnIndexOfDeclarationConsentPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "declarationConsentPath");
        final int _columnIndexOfSchoolIdPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "schoolIdPath");
        final int _columnIndexOfAcademicClearancePath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "academicClearancePath");
        final int _columnIndexOfSignedCodeOfConductPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "signedCodeOfConductPath");
        final int _columnIndexOfPassportPhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "passportPhotoPath");
        final int _columnIndexOfProfilePhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profilePhotoPath");
        final int _columnIndexOfPhoneNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phoneNumber");
        final int _columnIndexOfIsVerified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isVerified");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfLoginAttempts = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "loginAttempts");
        final int _columnIndexOfIsLocked = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isLocked");
        final User _result;
        if (_stmt.step()) {
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
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
          final String _tmpEmail;
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null;
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail);
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
          final String _tmpManifesto;
          if (_stmt.isNull(_columnIndexOfManifesto)) {
            _tmpManifesto = null;
          } else {
            _tmpManifesto = _stmt.getText(_columnIndexOfManifesto);
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
          final String _tmpProfilePhotoPath;
          if (_stmt.isNull(_columnIndexOfProfilePhotoPath)) {
            _tmpProfilePhotoPath = null;
          } else {
            _tmpProfilePhotoPath = _stmt.getText(_columnIndexOfProfilePhotoPath);
          }
          final String _tmpPhoneNumber;
          if (_stmt.isNull(_columnIndexOfPhoneNumber)) {
            _tmpPhoneNumber = null;
          } else {
            _tmpPhoneNumber = _stmt.getText(_columnIndexOfPhoneNumber);
          }
          final boolean _tmpIsVerified;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsVerified));
          _tmpIsVerified = _tmp != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final int _tmpLoginAttempts;
          _tmpLoginAttempts = (int) (_stmt.getLong(_columnIndexOfLoginAttempts));
          final boolean _tmpIsLocked;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsLocked));
          _tmpIsLocked = _tmp_1 != 0;
          _result = new User(_tmpUserId,_tmpUsername,_tmpRegNo,_tmpFullName,_tmpEmail,_tmpFaculty,_tmpCourse,_tmpSubjectCombination,_tmpLevelOfStudy,_tmpYearOfStudy,_tmpGender,_tmpDisability,_tmpPasswordHash,_tmpRole,_tmpManifesto,_tmpCvPath,_tmpDeclarationConsentPath,_tmpSchoolIdPath,_tmpAcademicClearancePath,_tmpSignedCodeOfConductPath,_tmpPassportPhotoPath,_tmpProfilePhotoPath,_tmpPhoneNumber,_tmpIsVerified,_tmpCreatedAt,_tmpLoginAttempts,_tmpIsLocked);
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
  public Object getUserByPhone(final String phone, final Continuation<? super User> $completion) {
    final String _sql = "SELECT * FROM users WHERE phoneNumber = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (phone == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, phone);
        }
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfUsername = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "username");
        final int _columnIndexOfRegNo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "regNo");
        final int _columnIndexOfFullName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "fullName");
        final int _columnIndexOfEmail = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "email");
        final int _columnIndexOfFaculty = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "faculty");
        final int _columnIndexOfCourse = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "course");
        final int _columnIndexOfSubjectCombination = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subjectCombination");
        final int _columnIndexOfLevelOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "levelOfStudy");
        final int _columnIndexOfYearOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "yearOfStudy");
        final int _columnIndexOfGender = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "gender");
        final int _columnIndexOfDisability = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "disability");
        final int _columnIndexOfPasswordHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "passwordHash");
        final int _columnIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "role");
        final int _columnIndexOfManifesto = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "manifesto");
        final int _columnIndexOfCvPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cvPath");
        final int _columnIndexOfDeclarationConsentPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "declarationConsentPath");
        final int _columnIndexOfSchoolIdPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "schoolIdPath");
        final int _columnIndexOfAcademicClearancePath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "academicClearancePath");
        final int _columnIndexOfSignedCodeOfConductPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "signedCodeOfConductPath");
        final int _columnIndexOfPassportPhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "passportPhotoPath");
        final int _columnIndexOfProfilePhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profilePhotoPath");
        final int _columnIndexOfPhoneNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phoneNumber");
        final int _columnIndexOfIsVerified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isVerified");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfLoginAttempts = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "loginAttempts");
        final int _columnIndexOfIsLocked = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isLocked");
        final User _result;
        if (_stmt.step()) {
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
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
          final String _tmpEmail;
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null;
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail);
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
          final String _tmpManifesto;
          if (_stmt.isNull(_columnIndexOfManifesto)) {
            _tmpManifesto = null;
          } else {
            _tmpManifesto = _stmt.getText(_columnIndexOfManifesto);
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
          final String _tmpProfilePhotoPath;
          if (_stmt.isNull(_columnIndexOfProfilePhotoPath)) {
            _tmpProfilePhotoPath = null;
          } else {
            _tmpProfilePhotoPath = _stmt.getText(_columnIndexOfProfilePhotoPath);
          }
          final String _tmpPhoneNumber;
          if (_stmt.isNull(_columnIndexOfPhoneNumber)) {
            _tmpPhoneNumber = null;
          } else {
            _tmpPhoneNumber = _stmt.getText(_columnIndexOfPhoneNumber);
          }
          final boolean _tmpIsVerified;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsVerified));
          _tmpIsVerified = _tmp != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final int _tmpLoginAttempts;
          _tmpLoginAttempts = (int) (_stmt.getLong(_columnIndexOfLoginAttempts));
          final boolean _tmpIsLocked;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsLocked));
          _tmpIsLocked = _tmp_1 != 0;
          _result = new User(_tmpUserId,_tmpUsername,_tmpRegNo,_tmpFullName,_tmpEmail,_tmpFaculty,_tmpCourse,_tmpSubjectCombination,_tmpLevelOfStudy,_tmpYearOfStudy,_tmpGender,_tmpDisability,_tmpPasswordHash,_tmpRole,_tmpManifesto,_tmpCvPath,_tmpDeclarationConsentPath,_tmpSchoolIdPath,_tmpAcademicClearancePath,_tmpSignedCodeOfConductPath,_tmpPassportPhotoPath,_tmpProfilePhotoPath,_tmpPhoneNumber,_tmpIsVerified,_tmpCreatedAt,_tmpLoginAttempts,_tmpIsLocked);
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
  public Object getUserByEmail(final String email, final Continuation<? super User> $completion) {
    final String _sql = "SELECT * FROM users WHERE email = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (email == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, email);
        }
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfUsername = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "username");
        final int _columnIndexOfRegNo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "regNo");
        final int _columnIndexOfFullName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "fullName");
        final int _columnIndexOfEmail = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "email");
        final int _columnIndexOfFaculty = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "faculty");
        final int _columnIndexOfCourse = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "course");
        final int _columnIndexOfSubjectCombination = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subjectCombination");
        final int _columnIndexOfLevelOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "levelOfStudy");
        final int _columnIndexOfYearOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "yearOfStudy");
        final int _columnIndexOfGender = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "gender");
        final int _columnIndexOfDisability = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "disability");
        final int _columnIndexOfPasswordHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "passwordHash");
        final int _columnIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "role");
        final int _columnIndexOfManifesto = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "manifesto");
        final int _columnIndexOfCvPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cvPath");
        final int _columnIndexOfDeclarationConsentPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "declarationConsentPath");
        final int _columnIndexOfSchoolIdPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "schoolIdPath");
        final int _columnIndexOfAcademicClearancePath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "academicClearancePath");
        final int _columnIndexOfSignedCodeOfConductPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "signedCodeOfConductPath");
        final int _columnIndexOfPassportPhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "passportPhotoPath");
        final int _columnIndexOfProfilePhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profilePhotoPath");
        final int _columnIndexOfPhoneNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phoneNumber");
        final int _columnIndexOfIsVerified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isVerified");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfLoginAttempts = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "loginAttempts");
        final int _columnIndexOfIsLocked = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isLocked");
        final User _result;
        if (_stmt.step()) {
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
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
          final String _tmpEmail;
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null;
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail);
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
          final String _tmpManifesto;
          if (_stmt.isNull(_columnIndexOfManifesto)) {
            _tmpManifesto = null;
          } else {
            _tmpManifesto = _stmt.getText(_columnIndexOfManifesto);
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
          final String _tmpProfilePhotoPath;
          if (_stmt.isNull(_columnIndexOfProfilePhotoPath)) {
            _tmpProfilePhotoPath = null;
          } else {
            _tmpProfilePhotoPath = _stmt.getText(_columnIndexOfProfilePhotoPath);
          }
          final String _tmpPhoneNumber;
          if (_stmt.isNull(_columnIndexOfPhoneNumber)) {
            _tmpPhoneNumber = null;
          } else {
            _tmpPhoneNumber = _stmt.getText(_columnIndexOfPhoneNumber);
          }
          final boolean _tmpIsVerified;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsVerified));
          _tmpIsVerified = _tmp != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final int _tmpLoginAttempts;
          _tmpLoginAttempts = (int) (_stmt.getLong(_columnIndexOfLoginAttempts));
          final boolean _tmpIsLocked;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsLocked));
          _tmpIsLocked = _tmp_1 != 0;
          _result = new User(_tmpUserId,_tmpUsername,_tmpRegNo,_tmpFullName,_tmpEmail,_tmpFaculty,_tmpCourse,_tmpSubjectCombination,_tmpLevelOfStudy,_tmpYearOfStudy,_tmpGender,_tmpDisability,_tmpPasswordHash,_tmpRole,_tmpManifesto,_tmpCvPath,_tmpDeclarationConsentPath,_tmpSchoolIdPath,_tmpAcademicClearancePath,_tmpSignedCodeOfConductPath,_tmpPassportPhotoPath,_tmpProfilePhotoPath,_tmpPhoneNumber,_tmpIsVerified,_tmpCreatedAt,_tmpLoginAttempts,_tmpIsLocked);
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
  public Object getUnverifiedUsers(final Continuation<? super List<User>> $completion) {
    final String _sql = "SELECT * FROM users WHERE isVerified = 0";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfUsername = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "username");
        final int _columnIndexOfRegNo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "regNo");
        final int _columnIndexOfFullName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "fullName");
        final int _columnIndexOfEmail = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "email");
        final int _columnIndexOfFaculty = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "faculty");
        final int _columnIndexOfCourse = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "course");
        final int _columnIndexOfSubjectCombination = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subjectCombination");
        final int _columnIndexOfLevelOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "levelOfStudy");
        final int _columnIndexOfYearOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "yearOfStudy");
        final int _columnIndexOfGender = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "gender");
        final int _columnIndexOfDisability = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "disability");
        final int _columnIndexOfPasswordHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "passwordHash");
        final int _columnIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "role");
        final int _columnIndexOfManifesto = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "manifesto");
        final int _columnIndexOfCvPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cvPath");
        final int _columnIndexOfDeclarationConsentPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "declarationConsentPath");
        final int _columnIndexOfSchoolIdPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "schoolIdPath");
        final int _columnIndexOfAcademicClearancePath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "academicClearancePath");
        final int _columnIndexOfSignedCodeOfConductPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "signedCodeOfConductPath");
        final int _columnIndexOfPassportPhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "passportPhotoPath");
        final int _columnIndexOfProfilePhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profilePhotoPath");
        final int _columnIndexOfPhoneNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phoneNumber");
        final int _columnIndexOfIsVerified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isVerified");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfLoginAttempts = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "loginAttempts");
        final int _columnIndexOfIsLocked = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isLocked");
        final List<User> _result = new ArrayList<User>();
        while (_stmt.step()) {
          final User _item;
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
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
          final String _tmpEmail;
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null;
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail);
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
          final String _tmpManifesto;
          if (_stmt.isNull(_columnIndexOfManifesto)) {
            _tmpManifesto = null;
          } else {
            _tmpManifesto = _stmt.getText(_columnIndexOfManifesto);
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
          final String _tmpProfilePhotoPath;
          if (_stmt.isNull(_columnIndexOfProfilePhotoPath)) {
            _tmpProfilePhotoPath = null;
          } else {
            _tmpProfilePhotoPath = _stmt.getText(_columnIndexOfProfilePhotoPath);
          }
          final String _tmpPhoneNumber;
          if (_stmt.isNull(_columnIndexOfPhoneNumber)) {
            _tmpPhoneNumber = null;
          } else {
            _tmpPhoneNumber = _stmt.getText(_columnIndexOfPhoneNumber);
          }
          final boolean _tmpIsVerified;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsVerified));
          _tmpIsVerified = _tmp != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final int _tmpLoginAttempts;
          _tmpLoginAttempts = (int) (_stmt.getLong(_columnIndexOfLoginAttempts));
          final boolean _tmpIsLocked;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsLocked));
          _tmpIsLocked = _tmp_1 != 0;
          _item = new User(_tmpUserId,_tmpUsername,_tmpRegNo,_tmpFullName,_tmpEmail,_tmpFaculty,_tmpCourse,_tmpSubjectCombination,_tmpLevelOfStudy,_tmpYearOfStudy,_tmpGender,_tmpDisability,_tmpPasswordHash,_tmpRole,_tmpManifesto,_tmpCvPath,_tmpDeclarationConsentPath,_tmpSchoolIdPath,_tmpAcademicClearancePath,_tmpSignedCodeOfConductPath,_tmpPassportPhotoPath,_tmpProfilePhotoPath,_tmpPhoneNumber,_tmpIsVerified,_tmpCreatedAt,_tmpLoginAttempts,_tmpIsLocked);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getVotersNotVoted(final String electionId,
      final Continuation<? super List<User>> $completion) {
    final String _sql = "SELECT * FROM users WHERE role = 'voter' AND userId NOT IN (SELECT voterId FROM votes WHERE electionId = ?)";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (electionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, electionId);
        }
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfUsername = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "username");
        final int _columnIndexOfRegNo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "regNo");
        final int _columnIndexOfFullName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "fullName");
        final int _columnIndexOfEmail = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "email");
        final int _columnIndexOfFaculty = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "faculty");
        final int _columnIndexOfCourse = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "course");
        final int _columnIndexOfSubjectCombination = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subjectCombination");
        final int _columnIndexOfLevelOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "levelOfStudy");
        final int _columnIndexOfYearOfStudy = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "yearOfStudy");
        final int _columnIndexOfGender = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "gender");
        final int _columnIndexOfDisability = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "disability");
        final int _columnIndexOfPasswordHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "passwordHash");
        final int _columnIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "role");
        final int _columnIndexOfManifesto = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "manifesto");
        final int _columnIndexOfCvPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cvPath");
        final int _columnIndexOfDeclarationConsentPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "declarationConsentPath");
        final int _columnIndexOfSchoolIdPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "schoolIdPath");
        final int _columnIndexOfAcademicClearancePath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "academicClearancePath");
        final int _columnIndexOfSignedCodeOfConductPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "signedCodeOfConductPath");
        final int _columnIndexOfPassportPhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "passportPhotoPath");
        final int _columnIndexOfProfilePhotoPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profilePhotoPath");
        final int _columnIndexOfPhoneNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phoneNumber");
        final int _columnIndexOfIsVerified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isVerified");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfLoginAttempts = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "loginAttempts");
        final int _columnIndexOfIsLocked = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isLocked");
        final List<User> _result = new ArrayList<User>();
        while (_stmt.step()) {
          final User _item;
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
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
          final String _tmpEmail;
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null;
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail);
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
          final String _tmpManifesto;
          if (_stmt.isNull(_columnIndexOfManifesto)) {
            _tmpManifesto = null;
          } else {
            _tmpManifesto = _stmt.getText(_columnIndexOfManifesto);
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
          final String _tmpProfilePhotoPath;
          if (_stmt.isNull(_columnIndexOfProfilePhotoPath)) {
            _tmpProfilePhotoPath = null;
          } else {
            _tmpProfilePhotoPath = _stmt.getText(_columnIndexOfProfilePhotoPath);
          }
          final String _tmpPhoneNumber;
          if (_stmt.isNull(_columnIndexOfPhoneNumber)) {
            _tmpPhoneNumber = null;
          } else {
            _tmpPhoneNumber = _stmt.getText(_columnIndexOfPhoneNumber);
          }
          final boolean _tmpIsVerified;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsVerified));
          _tmpIsVerified = _tmp != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final int _tmpLoginAttempts;
          _tmpLoginAttempts = (int) (_stmt.getLong(_columnIndexOfLoginAttempts));
          final boolean _tmpIsLocked;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsLocked));
          _tmpIsLocked = _tmp_1 != 0;
          _item = new User(_tmpUserId,_tmpUsername,_tmpRegNo,_tmpFullName,_tmpEmail,_tmpFaculty,_tmpCourse,_tmpSubjectCombination,_tmpLevelOfStudy,_tmpYearOfStudy,_tmpGender,_tmpDisability,_tmpPasswordHash,_tmpRole,_tmpManifesto,_tmpCvPath,_tmpDeclarationConsentPath,_tmpSchoolIdPath,_tmpAcademicClearancePath,_tmpSignedCodeOfConductPath,_tmpPassportPhotoPath,_tmpProfilePhotoPath,_tmpPhoneNumber,_tmpIsVerified,_tmpCreatedAt,_tmpLoginAttempts,_tmpIsLocked);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object countByGender(final String gender,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM users WHERE gender = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (gender == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, gender);
        }
        final Integer _result;
        if (_stmt.step()) {
          final Integer _tmp;
          if (_stmt.isNull(0)) {
            _tmp = null;
          } else {
            _tmp = (int) (_stmt.getLong(0));
          }
          _result = _tmp;
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
  public Object countWithDisability(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM users WHERE disability IS NOT NULL";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final Integer _result;
        if (_stmt.step()) {
          final Integer _tmp;
          if (_stmt.isNull(0)) {
            _tmp = null;
          } else {
            _tmp = (int) (_stmt.getLong(0));
          }
          _result = _tmp;
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
  public Object countCandidatesByGender(final String gender,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM users WHERE role = 'candidate' AND gender = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (gender == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, gender);
        }
        final Integer _result;
        if (_stmt.step()) {
          final Integer _tmp;
          if (_stmt.isNull(0)) {
            _tmp = null;
          } else {
            _tmp = (int) (_stmt.getLong(0));
          }
          _result = _tmp;
        } else {
          _result = null;
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
